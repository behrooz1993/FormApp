package com.ahmadpour.formapp.ui.answer;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.MvpPresenter;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by behrooz on 2/2/18.
 */
public interface AnswerMvpPresenter<V extends AnswerMvpView> extends MvpPresenter<V> {

    void fetchAnswers(long formId);
    void onSubmitButtonClicked(ArrayList<Answers> answers);

}
