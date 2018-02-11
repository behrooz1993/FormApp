package com.ahmadpour.formapp.ui.codeList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ahmadpour.formapp.R;

import butterknife.BindView;

public class CodeListActivity extends AppCompatActivity {

    @BindView(R.id.lst_codes)
    public RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_list);
    }
}
