package com.ahmadpour.formapp.ui.form;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.ui.answer.AnswerActivity;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.utils.AppConstants;
import com.ahmadpour.formapp.utils.AppUtils;
import com.ahmadpour.formapp.utils.tools.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormActivity extends BaseActivity implements FormMvpView {

    private long formId = 0;
    private int counter = 0;
    private ArrayList<Questions> lstQuestions = new ArrayList<>();
    private Answers[] arrAnswers;
    private String date = "";

    @Inject
    FormMvpPresenter<FormMvpView> mPresenter;

    @BindView(R.id.lin_container)
    public LinearLayout linContainer;
    @BindView(R.id.btn_form_submit)
    public Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(FormActivity.this);
        date = AppUtils.getCurrentIranianDateString();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            formId = extras.getLong(AppConstants.FORM_ID_BUNDLE);
        }

        setUp();
        mPresenter.fetchQuestions(formId);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void refreshQuestions(List<Questions> questions) {
        this.lstQuestions.addAll(questions);
        arrAnswers = new Answers[lstQuestions.size()];
        for (int i = 0; i < arrAnswers.length; i++) {
            arrAnswers[i] = new Answers();
            if (lstQuestions.get(i).getType() == 1) {
                arrAnswers[i].setOptionId(-1l);
            } else {
                arrAnswers[i].setOptionId(0l);
            }
            arrAnswers[i].setFormId(formId);
            arrAnswers[i].setQuestionId(lstQuestions.get(i).getId());
            arrAnswers[i].setTemp(1);
            arrAnswers[i].setDate(date);
        }

        createQuestions();
    }

    @Override
    public void deleteCurrentAnswers() {
        mPresenter.addCurrentAnswers(new ArrayList<>(Arrays.asList(arrAnswers)));
    }

    @Override
    public void openAnswerPage() {
        startActivity(new Intent(this, AnswerActivity.class)
                .putExtra(AppConstants.FORM_ID_BUNDLE, formId)
                .putExtra(AppConstants.TYPE_BUNDLE, 1)
                .putExtra(AppConstants.DATE_BUNDLE, date));
//        startActivity(new Intent(this, AnswerActivity.class)
//                .putParcelableArrayListExtra(AppConstants.ANSWER_ARRAY_BUNDLE,
//                        new ArrayList<Answers>(Arrays.asList(arrAnswers))));
    }

    @OnClick(R.id.btn_form_submit)
    public void btnSubmitClicked() {
        mPresenter.onSubmitButtonClicked(formId);
    }

    private void createQuestions() {
        for (Questions question : lstQuestions) {
            if (question.getType() == 1) {
                createTextQuestion(question);
            } else {
//                createListQuestion(question);
                createSpinnerQuestion(question);
            }
        }
    }

    private void createTextQuestion(Questions question) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_question_text, linContainer, false);
        TextView lblTitle = view.findViewById(R.id.lbl_row_question_text_title);
        EditText txtQuestion = view.findViewById(R.id.txt_row_question_text);
        lblTitle.setText(question.getId() + " - " + question.getQuestion() + " ؟");
        txtQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    arrAnswers[(int) view.getTag()].setAnswer("0");
                } else {
                    arrAnswers[(int) view.getTag()].setAnswer(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        view.setTag(counter);
        linContainer.addView(view);
        counter += 1;
    }

    private void createListQuestion(Questions question) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_question_list, linContainer, false);
        TextView lblTitle = view.findViewById(R.id.lbl_row_question_list_title);
        RecyclerView listView = view.findViewById(R.id.lst_row_question_list);

        lblTitle.setText(question.getId() + " - " + question.getQuestion() + " ؟");
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        OptionAdapter adapter = new OptionAdapter(question.getOptions());
        listView.setAdapter(adapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view1, position) -> {
            adapter.setSelectedPosition(position);
            adapter.notifyDataSetChanged();
            arrAnswers[(int) view.getTag()].setOptionId(question.getOptions().get(position).getId());
        }));
        view.setTag(counter);
        linContainer.addView(view);
        counter += 1;
    }

    private void createSpinnerQuestion(Questions question) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_question_list, linContainer, false);
        TextView lblTitle = view.findViewById(R.id.lbl_row_question_list_title);
        Spinner spinner = view.findViewById(R.id.spn_row_question_list);
        lblTitle.setText(question.getId() + " - " + question.getQuestion() + " ؟");
        spinner.setAdapter(new OptionSpinnerAdapter(question.getOptions()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View itemView, int position, long l) {
                arrAnswers[(int) view.getTag()].setOptionId(question.getOptions().get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        view.setTag(counter);
        linContainer.addView(view);
        counter += 1;
    }
}
