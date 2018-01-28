package com.ahmadpour.formapp.di.component;

import com.ahmadpour.formapp.di.PerService;
import com.ahmadpour.formapp.di.module.ServiceModule;
import com.ahmadpour.formapp.service.SyncService;

import dagger.Component;

/**
 * Created by behrooz on 1/29/18.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}