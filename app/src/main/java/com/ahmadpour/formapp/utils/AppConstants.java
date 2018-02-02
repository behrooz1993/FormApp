package com.ahmadpour.formapp.utils;

import android.os.Environment;

/**
 * Created by behrooz on 1/29/18.
 */

public final class AppConstants {

    public static final String FORM_ID_BUNDLE = "FormId";
    public static final String ANSWER_ARRAY_BUNDLE = "Answers";

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "formApp.db";
    public static final String DB_PATH = Environment.getExternalStorageDirectory() +
            "/data/data/com.ahmadpour.formapp/";
    public static final String PREF_NAME = "formAppPref";

    public static final long NULL_INDEX = -1L;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
