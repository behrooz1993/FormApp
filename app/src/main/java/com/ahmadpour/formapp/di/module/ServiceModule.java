package com.ahmadpour.formapp.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by behrooz on 1/29/18.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}