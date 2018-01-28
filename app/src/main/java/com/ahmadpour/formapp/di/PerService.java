package com.ahmadpour.formapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by behrooz on 1/29/18.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
