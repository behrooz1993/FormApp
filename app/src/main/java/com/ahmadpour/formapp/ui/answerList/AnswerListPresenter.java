package com.ahmadpour.formapp.ui.answerList;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/2/18.
 */

public class AnswerListPresenter<V extends AnswerListMvpView> extends BasePresenter<V> implements AnswerListMvpPresenter<V> {

    @Inject
    public AnswerListPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchForms() {
        getCompositeDisposable().add(getDataManager().getAnsweredForms()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(forms -> {
                    if(!isViewAttached()) {
                        return;
                    }

                    if(forms != null) {
                        getMvpView().refreshForms(forms);
                    }
                }));
    }

    @Override
    public void onListItemClicked(int position) {
        getMvpView().openAnswerActivity(position);
    }
}
