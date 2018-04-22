package com.ahmadpour.formapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.ui.formList.FormListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.btn_form_list)
    public Button btnFormList;
    @BindView(R.id.btn_answer_list)
    public Button btnAnswerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);
        mPresenter.deleteTempAnswers();

        setUp();
    }

    @Override
    protected void setUp() {
    }

    @Override
    public void openFormList() {
        startActivity(new Intent(this, FormListActivity.class));
    }

    @Override
    public void openAnswerList() {
    }

    @Override
    public void openCodeList() {
    }

    @OnClick(R.id.btn_form_list)
    public void btnFormListClicked() {
        mPresenter.onFormListButtonClicked();
    }

    @OnClick(R.id.btn_answer_list)
    public void btnAnswerListClicked() {
        mPresenter.onAnswerListButtonClicked();
    }

    @OnClick(R.id.btn_code_list)
    public void btnCodeListClicked() {
        mPresenter.onCodeListButtonClicked();
    }

}
