package com.ahmadpour.formapp.ui.answers;

import com.ahmadpour.formapp.ui.base.MvpPresenter;

/**
 * Created by behrooz on 2/10/18.
 */

public interface AnswersMvpPresenter<V extends AnswersMvpView> extends MvpPresenter<V> {

    void getAnswers(long formId);
    void onListItemClicked(int position);

}
