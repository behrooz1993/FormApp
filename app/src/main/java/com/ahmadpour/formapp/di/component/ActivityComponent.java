package com.ahmadpour.formapp.di.component;

import com.ahmadpour.formapp.di.PerActivity;
import com.ahmadpour.formapp.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by behrooz on 1/29/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
