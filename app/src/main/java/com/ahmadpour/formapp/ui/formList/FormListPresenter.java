package com.ahmadpour.formapp.ui.formList;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/2/18.
 */

public class FormListPresenter<V extends FormListMvpView> extends BasePresenter<V> implements FormListMvpPresenter<V>{

    @Inject
    public FormListPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onListItemClicked(int position) {
        getMvpView().openFormActivity(position);
    }
}
