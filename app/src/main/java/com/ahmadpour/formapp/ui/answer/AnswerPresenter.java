package com.ahmadpour.formapp.ui.answer;

import android.widget.Toast;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;
import com.ahmadpour.formapp.utils.tools.Gzip;

import java.io.IOException;
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
    public void onSubmitButtonClicked(ArrayList<Answers> answers) {
        getCompositeDisposable().add(getDataManager().updateFormAnswers(answers)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(result -> {
                    if (result) {
                        getMvpView().openMainActivity();
                    }
                }));

    }

    private void generateFollowCode(ArrayList<Answers> answers) {
        String result = "1000101";
        for (Answers answer : answers) {
            if (answer.getQuestion().getType() == 1) {
                continue;
            }
            if (answer.getOptionId() == 0) {
                result += "0";
            } else {
                for (int i = 0; i < answer.getQuestion().getOptions().size(); i++) {
                    if (answer.getQuestion().getOptions().get(i).getId() == answer.getOptionId()) {
                        result += (i + 1) + "";
                    }
                }
            }
        }
        String compress = "";
        try {
            compress = Gzip.compress(result).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
