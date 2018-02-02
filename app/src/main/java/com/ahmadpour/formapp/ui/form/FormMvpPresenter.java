package com.ahmadpour.formapp.ui.form;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.MvpPresenter;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by behrooz on 2/2/18.
 */

public interface FormMvpPresenter<V extends FormMvpView> extends MvpPresenter<V> {

    void fetchQuestions(long formId);
    void onSubmitButtonClicked(long formId);
    void addCurrentAnswers(ArrayList<Answers> answers);

}
