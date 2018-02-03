package com.ahmadpour.formapp.di.module;

import android.app.Application;
import android.content.Context;

import com.ahmadpour.formapp.BuildConfig;
import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.AppDataManager;
import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.data.db.AppDbHelper;
import com.ahmadpour.formapp.data.db.DbHelper;
import com.ahmadpour.formapp.data.prefs.AppPreferencesHelper;
import com.ahmadpour.formapp.data.prefs.PreferencesHelper;
import com.ahmadpour.formapp.di.ApiInfo;
import com.ahmadpour.formapp.di.ApplicationContext;
import com.ahmadpour.formapp.di.DatabaseInfo;
import com.ahmadpour.formapp.di.PreferenceInfo;
import com.ahmadpour.formapp.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by behrooz on 1/29/18.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
//
    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }
//
    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
//
//    @Provides
//    @Singleton
//    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
//        return appApiHelper;
//    }
//
//    @Provides
//    @Singleton
//    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
//                                                           PreferencesHelper preferencesHelper) {
//        return new ApiHeader.ProtectedApiHeader(
//                apiKey,
//                preferencesHelper.getCurrentUserId(),
//                preferencesHelper.getAccessToken());
//    }
//
    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iransans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}