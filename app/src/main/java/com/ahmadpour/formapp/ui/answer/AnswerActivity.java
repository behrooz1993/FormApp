package com.ahmadpour.formapp.ui.answer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.ui.form.FormActivity;
import com.ahmadpour.formapp.ui.form.OptionAdapter;
import com.ahmadpour.formapp.ui.main.MainActivity;
import com.ahmadpour.formapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivity extends BaseActivity implements AnswerMvpView {

    private ArrayList<Answers> lstAnswers = new ArrayList<>();
    private long formId = 0l;
    private int type = 1;

    @Inject
    AnswerMvpPresenter<AnswerMvpView> mPresenter;

    @BindView(R.id.btn_form_save)
    public Button btnSave;
    @BindView(R.id.lin_container)
    public LinearLayout linContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(AnswerActivity.this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            formId = extras.getLong(AppConstants.FORM_ID_BUNDLE);
            type = extras.getInt(AppConstants.TYPE_BUNDLE);
        }

        setUp();
        mPresenter.fetchAnswers(formId);

    }

    @Override
    protected void setUp() {
        if(type == 2) {
            btnSave.setVisibility(View.GONE);
        }
    }

    @Override
    public void fetchAnswers(List<Answers> answers) {
        lstAnswers.removeAll(lstAnswers);
        lstAnswers.addAll(answers);
        createAnswers();
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.btn_form_save)
    public void btnSaveClicked() {
        mPresenter.onSubmitButtonClicked();
    }

    private void createAnswers() {
        for (Answers answer : lstAnswers) {
            if (answer.getQuestion().getType() == 1) {

            } else {

            }
        }


        for (Answers answer : lstAnswers) {
            if (answer.getOptionId() == -1) {
                createTextAnswer(answer);
            } else {
                createListAnswer(answer);
            }
        }
    }

    private void createTextAnswer(Answers answer) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_question_text, linContainer, false);
        TextView lblTitle = view.findViewById(R.id.lbl_row_question_text_title);
        EditText txtQuestion = view.findViewById(R.id.txt_row_question_text);
        txtQuestion.setEnabled(false);
        lblTitle.setText(answer.getQuestionId() + " - " + answer.getQuestion().getQuestion() + " ؟");
        if (answer.getAnswer() != null) {
            if (!answer.getAnswer().equals("0")) {
                txtQuestion.setText(answer.getAnswer());
            }
        }
        linContainer.addView(view);
    }

    private void createListAnswer(Answers answer) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_question_list, linContainer, false);
        TextView lblTitle = view.findViewById(R.id.lbl_row_question_list_title);
        RecyclerView listView = view.findViewById(R.id.lst_row_question_list);
        lblTitle.setText(answer.getQuestionId() + " - " + answer.getQuestion().getQuestion() + " ؟");
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        AnswerOptionAdapter adapter = new AnswerOptionAdapter(answer.getOptionId(), answer.getQuestion().getOptions());
        listView.setAdapter(adapter);
        linContainer.addView(view);
    }
}
