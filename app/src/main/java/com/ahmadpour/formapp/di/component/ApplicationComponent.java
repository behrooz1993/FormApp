package com.ahmadpour.formapp.di.component;

import android.app.Application;
import android.content.Context;

import com.ahmadpour.formapp.FormApp;
import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.di.ApplicationContext;
import com.ahmadpour.formapp.di.module.ApplicationModule;
import com.ahmadpour.formapp.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by behrooz on 1/29/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(FormApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}