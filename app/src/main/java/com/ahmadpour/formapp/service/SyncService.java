package com.ahmadpour.formapp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ahmadpour.formapp.FormApp;
import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.di.component.DaggerServiceComponent;
import com.ahmadpour.formapp.di.component.ServiceComponent;
import com.ahmadpour.formapp.utils.AppLogger;

import javax.inject.Inject;

/**
 * Created by behrooz on 1/29/18.
 */

public class SyncService extends Service {

    private static final String TAG = SyncService.class.getSimpleName();

    @Inject
    DataManager mDataManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SyncService.class);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((FormApp) getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d(TAG, "SyncService started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AppLogger.d(TAG, "SyncService stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}