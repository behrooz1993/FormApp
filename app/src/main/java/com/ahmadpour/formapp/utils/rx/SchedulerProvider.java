package com.ahmadpour.formapp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by behrooz on 1/29/18.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
