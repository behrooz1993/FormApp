package com.ahmadpour.formapp.ui.answers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.answer.AnswerActivity;
import com.ahmadpour.formapp.ui.answer.AnswerMvpPresenter;
import com.ahmadpour.formapp.ui.answer.AnswerMvpView;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.utils.AppConstants;
import com.ahmadpour.formapp.utils.tools.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswersActivity extends BaseActivity implements AnswersMvpView {

    private AnswersAdapter mAdapter;
    private ArrayList<String> answers = new ArrayList<>();
    private long formId;
    private int type;

    @Inject
    AnswersMvpPresenter<AnswersMvpView> mPresenter;

    @BindView(R.id.lst_answers)
    public RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(AnswersActivity.this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            formId = extras.getLong(AppConstants.FORM_ID_BUNDLE);
            type = extras.getInt(AppConstants.TYPE_BUNDLE);
        }

        setUp();
        mPresenter.getAnswers(formId);
    }

    @Override
    protected void setUp() {
        listView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnswersAdapter(this.answers);
        listView.setAdapter(mAdapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            mPresenter.onListItemClicked(position);
        }));
    }

    @Override
    public void fetchAnswersList(List<String> answers) {
        this.answers.addAll(answers);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openAnswerActivity(int position) {
        startActivity(new Intent(this, AnswerActivity.class)
                .putExtra(AppConstants.FORM_ID_BUNDLE, formId)
                .putExtra(AppConstants.TYPE_BUNDLE, 2)
                .putExtra(AppConstants.DATE_BUNDLE, answers.get(position)));
    }
}
