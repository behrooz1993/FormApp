package com.ahmadpour.formapp.ui.answer;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/2/18.
 */

public class AnswerPresenter<V extends AnswerMvpView> extends BasePresenter<V> implements AnswerMvpPresenter<V> {

    @Inject
    public AnswerPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchAnswers(long formId) {
        getCompositeDisposable().add(getDataManager().getAnswers(formId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(answers -> {
                    getMvpView().fetchAnswers(answers);
                }));
    }

    @Override
    public void onSubmitButtonClicked() {
        getMvpView().openMainActivity();
    }
}
