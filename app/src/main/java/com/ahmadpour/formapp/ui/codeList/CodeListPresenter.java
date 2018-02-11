package com.ahmadpour.formapp.ui.codeList;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/11/18.
 */

public class CodeListPresenter<V extends CodeListMvpView> extends BasePresenter<V> implements CodeListMvpPresenter<V> {

    @Inject
    public CodeListPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager,schedulerProvider,compositeDisposable);
    }

    @Override
    public void fetchCodes() {

    }
}
