package com.ahmadpour.formapp.ui.form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.ui.formList.FormListActivity;
import com.ahmadpour.formapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormActivity extends BaseActivity implements FormMvpView{

    private long formId = 0;
    private ArrayList<Questions> lstQuestions = new ArrayList<>();
    private Answers[] arrAnswers;

    @Inject
    FormMvpPresenter<FormMvpView> mPresenter;

    @BindView(R.id.lin_container)
    public LinearLayout linContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(FormActivity.this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
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
    }
}
