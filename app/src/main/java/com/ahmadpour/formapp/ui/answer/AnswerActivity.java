package com.ahmadpour.formapp.ui.answer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Codes;
import com.ahmadpour.formapp.data.db.models.Options;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.ui.main.MainActivity;
import com.ahmadpour.formapp.utils.AppConstants;
import com.ahmadpour.formapp.utils.tools.Gzip;

import java.io.IOException;
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
    private String date = "";

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
            date = extras.getString(AppConstants.DATE_BUNDLE);
        }

        setUp();
        mPresenter.fetchAnswers(formId, date);
    }

    @Override
    protected void setUp() {
        if (type == 2) {
            btnSave.setVisibility(View.GONE);
        }
    }

    @Override
    public void fetchAnswers(List<Answers> answers) {
        lstAnswers.removeAll(lstAnswers);
        lstAnswers.addAll(answers);
        for (Answers answer : answers) {
            answer.setTemp(0);
        }
        createAnswers();
    }

    @Override
    public void onCodeAdded() {
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.btn_form_save)
    public void btnSaveClicked() {
        generateFollowCode();
        mPresenter.onSubmitButtonClicked(lstAnswers);
    }

    private void createAnswers() {
        for (Answers answer : lstAnswers) {
            if (answer.getOptionId() == -1) {
                createTextAnswer(answer);
            } else {
                createListAnswer(answer);
            }
        }
    }

    private void createTextAnswer(Answers answer) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_answer, linContainer, false);
        TextView lblQuestion = view.findViewById(R.id.lbl_row_answer_question);
        TextView lblAnswer = view.findViewById(R.id.lbl_row_answer);
        lblQuestion.setText(answer.getQuestionId() + " - " + answer.getQuestion().getQuestion() + " ؟");
        if (answer.getAnswer() != null) {
            if (!answer.getAnswer().equals("0")) {
                lblAnswer.setText(getString(R.string.answer) + " : " + answer.getAnswer());
            }else {
                lblAnswer.setText(getString(R.string.no_answer));
            }
        }else {
            lblAnswer.setText(getString(R.string.no_answer));
        }
        linContainer.addView(view);
    }

    private void createListAnswer(Answers answer) {
        View view = LayoutInflater.from(this).inflate(R.layout.row_answer, linContainer, false);
        TextView lblQuestion = view.findViewById(R.id.lbl_row_answer_question);
        TextView lblAnswer = view.findViewById(R.id.lbl_row_answer);
        lblQuestion.setText(answer.getQuestionId() + " - " + answer.getQuestion().getQuestion() + " ؟");
        if(answer.getOptionId() == 0) {
            lblAnswer.setText(getString(R.string.no_answer));
        }else {
            for (Options option : answer.getQuestion().getOptions()) {
                if (option.getId() == answer.getOptionId()) {
                    lblAnswer.setText(getString(R.string.answer) + " : " + option.getOption());
                }
            }
        }
        linContainer.addView(view);
    }

    private void generateFollowCode() {
        String result = "1000101";
        for (Answers answer : lstAnswers) {
            if (answer.getQuestion().getType() == 1) {
                continue;
            }
            if (answer.getOptionId() == 0) {
                result += "0";
            } else {
                for (int i = 0; i < answer.getQuestion().getOptions().size(); i++) {
                    if (answer.getQuestion().getOptions().get(i).getId() == answer.getOptionId()) {
                        result += (i + 1) + "";
                    }
                }
            }
        }
        String compress = "";
        try {
            compress = Gzip.compress(result).toString();
            Codes code = new Codes();
            code.setCode(compress);
            code.setDate(date);
            code.setFormId(formId);
            mPresenter.insertCode(code);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, getString(R.string.error_in_follow_code_generation), Toast.LENGTH_SHORT).show();
        }
    }
}
