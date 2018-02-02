package com.ahmadpour.formapp.ui.main;

import com.ahmadpour.formapp.di.PerActivity;
import com.ahmadpour.formapp.ui.base.MvpPresenter;

/**
 * Created by behrooz on 2/2/18.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onFormListButtonClicked();
    void onAnswerListButtonClicked();

}
