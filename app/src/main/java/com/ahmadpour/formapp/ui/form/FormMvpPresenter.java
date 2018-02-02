package com.ahmadpour.formapp.ui.form;

import com.ahmadpour.formapp.ui.base.MvpPresenter;
import com.ahmadpour.formapp.ui.base.MvpView;

/**
 * Created by behrooz on 2/2/18.
 */

public interface FormMvpPresenter<V extends FormMvpView> extends MvpPresenter<V> {

    void fetchQuestions(long formId);

}
