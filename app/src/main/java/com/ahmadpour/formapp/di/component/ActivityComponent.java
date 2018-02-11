package com.ahmadpour.formapp.di.component;

import com.ahmadpour.formapp.di.PerActivity;
import com.ahmadpour.formapp.di.module.ActivityModule;
import com.ahmadpour.formapp.ui.answer.AnswerActivity;
import com.ahmadpour.formapp.ui.answerList.AnswerListActivity;
import com.ahmadpour.formapp.ui.answers.AnswersActivity;
import com.ahmadpour.formapp.ui.form.FormActivity;
import com.ahmadpour.formapp.ui.formList.FormListActivity;
import com.ahmadpour.formapp.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by behrooz on 1/29/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(FormListActivity activity);
    void inject(FormActivity activity);
    void inject(AnswerActivity activity);
    void inject(AnswerListActivity activity);
    void inject(AnswersActivity activity);
}
