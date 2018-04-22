package com.ahmadpour.formapp.ui.answers;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/10/18.
 */

public class AnswersPresenter<V extends AnswersMvpView> extends BasePresenter<V> implements AnswersMvpPresenter<V> {

    @Inject
    public AnswersPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getAnswers(long formId) {
        getCompositeDisposable().add(getDataManager().getFormAnswerList(formId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(answers -> {
                    getMvpView().fetchAnswersList(answers);
                }));
    }

    @Override
    public void onListItemClicked(int position) {
        getMvpView().openAnswerActivity(position);
    }
}
