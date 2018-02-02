package com.ahmadpour.formapp.data;

import com.ahmadpour.formapp.data.db.DbHelper;
import com.ahmadpour.formapp.data.prefs.PreferencesHelper;

import io.reactivex.Observable;

/**
 * Created by behrooz on 1/29/18.
 */

public interface DataManager extends DbHelper, PreferencesHelper {

}