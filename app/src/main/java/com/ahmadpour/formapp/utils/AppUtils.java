package com.ahmadpour.formapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.utils.tools.DateHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by behrooz on 1/29/18.
 */

public final class AppUtils {

    //This class is not publicly instantiable
    private AppUtils() {

    }

    public static String getCurrentIranianDateString() {
        Calendar calendar = Calendar.getInstance();
        DateHelper dateHelper = new DateHelper(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1
                , calendar.get(Calendar.DAY_OF_MONTH));

        return dateHelper.getIranianYear() + "/" + dateHelper.getIranianMonth() + "/" +
                dateHelper.getIranianDay() + " - " +  calendar.get(Calendar.HOUR_OF_DAY) + " : " +
                calendar.get(Calendar.MINUTE);
    }

}