package com.ahmadpour.formapp.ui.form;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.ui.formList.FormListPresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/2/18.
 */

public class FormPresenter<V extends FormMvpView> extends BasePresenter<V> implements FormMvpPresenter<V> {

    @Inject
    public FormPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchQuestions(long formId) {
        getCompositeDisposable().add(getDataManager().getFormQuestions(formId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(questions -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    if (questions != null) {
                        getMvpView().refreshQuestions(questions);
                    }
                }));
    }

    @Override
    public void onSubmitButtonClicked(long formId) {
        getCompositeDisposable().add(getDataManager().deleteFormTempAnswers(formId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(result -> {
                    if (result) {
                        getMvpView().deleteCurrentAnswers();
                    }
                }));

    }

    @Override
    public void addCurrentAnswers(ArrayList<Answers> answers) {
        getCompositeDisposable().add(getDataManager().inserAnswers(answers)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(result -> {
            if(result > 0) {
                getMvpView().openAnswerPage();
            }
        }));
    }
}
