package com.ahmadpour.formapp.ui.codeList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Codes;
import com.ahmadpour.formapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CodeListActivity extends BaseActivity implements CodeListMvpView{

    private CodeListAdapter mAdapter;
    private ArrayList<Codes> codes = new ArrayList<>();

    @BindView(R.id.lst_codes)
    public RecyclerView listView;

    @Inject
    CodeListPresenter<CodeListMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_list);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(CodeListActivity.this);

        setUp();
        mPresenter.fetchCodes();
    }

    @Override
    protected void setUp() {
        listView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CodeListAdapter(codes);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onFetchCodes(List<Codes> codes) {
        this.codes.addAll(codes);
        mAdapter.notifyDataSetChanged();
    }
}
