package com.ahmadpour.formapp.ui.formList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.ui.base.BaseActivity;
import com.ahmadpour.formapp.ui.form.FormActivity;
import com.ahmadpour.formapp.utils.AppConstants;

import java.util.ArrayList;

import javax.inject.Inject;

public class FormListActivity extends BaseActivity implements FormListMvpView {

    private ArrayList<Forms> lstForms = new ArrayList<>();

    @Inject
    FormListMvpPresenter<FormListMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_list_activity);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openFormActivity(int position) {
        startActivity(new Intent(this, FormActivity.class)
                .putExtra(AppConstants.FORM_ID_BUNDLE, lstForms.get(position).getId()));
    }
}
