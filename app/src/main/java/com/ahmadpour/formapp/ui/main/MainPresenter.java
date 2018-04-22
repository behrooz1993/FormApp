package com.ahmadpour.formapp.ui.main;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/2/18.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onFormListButtonClicked() {
        getMvpView().openFormList();
    }

    @Override
    public void onAnswerListButtonClicked() {
        getMvpView().openAnswerList();
    }

    @Override
    public void onCodeListButtonClicked() {
        getMvpView().openCodeList();
    }

    @Override
    public void deleteTempAnswers() {
        getCompositeDisposable().add(getDataManager().deleteTempAnswers()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(result -> {

                }));
    }
}
