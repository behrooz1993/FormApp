package com.ahmadpour.formapp.ui.answerList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.ui.answer.AnswerActivity;
import com.ahmadpour.formapp.ui.answers.AnswersActivity;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.utils.AppConstants;
import com.ahmadpour.formapp.utils.tools.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerListActivity extends BaseActivity implements AnswerListMvpView {

    private ArrayList<Forms> lstForms = new ArrayList<>();
    private AnswerListAdapter mAdapter;

    @Inject
    AnswerListPresenter<AnswerListMvpView> mPresenter;

    @BindView(R.id.lst_answers)
    public RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_list);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(AnswerListActivity.this);

        setUp();
        mPresenter.fetchForms();
    }

    @Override
    protected void setUp() {
        listView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnswerListAdapter(lstForms);
        listView.setAdapter(mAdapter);

        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            mPresenter.onListItemClicked(position);
        }));
    }

    @Override
    public void refreshForms(List<Forms> forms) {
        lstForms.addAll(forms);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openAnswerActivity(int position) {
//        startActivity(new Intent(this, AnswerActivity.class)
//                .putExtra(AppConstants.FORM_ID_BUNDLE, lstForms.get(position).getId())
//                .putExtra(AppConstants.TYPE_BUNDLE, 2));
        startActivity(new Intent(this, AnswersActivity.class)
                .putExtra(AppConstants.FORM_ID_BUNDLE, lstForms.get(position).getId())
                .putExtra(AppConstants.TYPE_BUNDLE, 2));
    }
}
