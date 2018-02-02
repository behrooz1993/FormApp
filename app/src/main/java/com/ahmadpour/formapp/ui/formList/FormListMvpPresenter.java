package com.ahmadpour.formapp.ui.formList;

import com.ahmadpour.formapp.di.PerActivity;
import com.ahmadpour.formapp.ui.base.MvpPresenter;

/**
 * Created by behrooz on 2/2/18.
 */

@PerActivity
public interface FormListMvpPresenter<V extends FormListMvpView> extends MvpPresenter<V>{

    void onListItemClicked(int position);

}
