package com.ahmadpour.formapp.ui.answerList;

import com.ahmadpour.formapp.ui.base.MvpPresenter;

/**
 * Created by behrooz on 2/2/18.
 */

public interface AnswerListMvpPresenter<V extends AnswerListMvpView> extends MvpPresenter<V>{

    void fetchForms();
    void onListItemClicked(int position);

}
