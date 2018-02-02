package com.ahmadpour.formapp;

import android.app.Application;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.di.component.ApplicationComponent;
import com.ahmadpour.formapp.di.component.DaggerApplicationComponent;
import com.ahmadpour.formapp.di.module.ApplicationModule;
import com.ahmadpour.formapp.utils.AppLogger;

import java.util.logging.Level;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by behrooz on 2/1/18.
 */

public class FormApp extends Application{

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

//        AndroidNetworking.initialize(getApplicationContext());
//        if (BuildConfig.DEBUG) {
//            AndroidNetworking.enableLogging(Level.BODY);
//        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
