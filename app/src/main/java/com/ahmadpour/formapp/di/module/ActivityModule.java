package com.ahmadpour.formapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.ahmadpour.formapp.di.ActivityContext;
import com.ahmadpour.formapp.di.PerActivity;
import com.ahmadpour.formapp.ui.answer.AnswerMvpPresenter;
import com.ahmadpour.formapp.ui.answer.AnswerMvpView;
import com.ahmadpour.formapp.ui.answer.AnswerPresenter;
import com.ahmadpour.formapp.ui.answerList.AnswerListMvpPresenter;
import com.ahmadpour.formapp.ui.answerList.AnswerListMvpView;
import com.ahmadpour.formapp.ui.answerList.AnswerListPresenter;
import com.ahmadpour.formapp.ui.form.FormMvpPresenter;
import com.ahmadpour.formapp.ui.form.FormMvpView;
import com.ahmadpour.formapp.ui.form.FormPresenter;
import com.ahmadpour.formapp.ui.formList.FormListMvpPresenter;
import com.ahmadpour.formapp.ui.formList.FormListMvpView;
import com.ahmadpour.formapp.ui.formList.FormListPresenter;
import com.ahmadpour.formapp.ui.main.MainMvpPresenter;
import com.ahmadpour.formapp.ui.main.MainMvpView;
import com.ahmadpour.formapp.ui.main.MainPresenter;
import com.ahmadpour.formapp.utils.rx.AppSchedulerProvider;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 1/29/18.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> providesMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    FormListMvpPresenter<FormListMvpView> providesFormListPresenter(
            FormListPresenter<FormListMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    FormMvpPresenter<FormMvpView> providesFormPresenter(
            FormPresenter<FormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AnswerMvpPresenter<AnswerMvpView> providesAnswerPresenter(
            AnswerPresenter<AnswerMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AnswerListMvpPresenter<AnswerListMvpView> providesAnswerListPresenter(
            AnswerListPresenter<AnswerListMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
