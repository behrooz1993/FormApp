package com.ahmadpour.formapp.ui.formList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.ui.form.FormActivity;
import com.ahmadpour.formapp.utils.AppConstants;
import com.ahmadpour.formapp.utils.tools.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormListActivity extends BaseActivity implements FormListMvpView {

    private ArrayList<Forms> lstForms = new ArrayList<>();
    private FormListAdapter mAdapter;

    @Inject
    FormListMvpPresenter<FormListMvpView> mPresenter;

    @BindView(R.id.lst_forms)
    public RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_list);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(FormListActivity.this);

        setUp();
        mPresenter.fetchForms();
    }

    @Override
    protected void setUp() {
        listView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FormListAdapter(lstForms);
        listView.setAdapter(mAdapter);
        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            mPresenter.onListItemClicked(position);
        }));
    }

    @Override
    public void refreshFormList(List<Forms> forms) {
        this.lstForms.addAll(forms);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openFormActivity(int position) {
        startActivity(new Intent(this, FormActivity.class)
                .putExtra(AppConstants.FORM_ID_BUNDLE, lstForms.get(position).getId()));
    }
}
