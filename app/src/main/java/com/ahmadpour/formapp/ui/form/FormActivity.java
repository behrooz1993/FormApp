package com.ahmadpour.formapp.ui.form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Questions;

import java.util.ArrayList;

import butterknife.BindView;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.lin_container)
    public LinearLayout linContainer;

    private ArrayList<Questions> lstQuestions = new ArrayList<>();
    private Answers[] arrAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }
}
