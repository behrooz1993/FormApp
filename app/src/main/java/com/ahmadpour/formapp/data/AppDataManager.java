package com.ahmadpour.formapp.data;

import android.content.Context;

import com.ahmadpour.formapp.data.db.DbHelper;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.data.prefs.PreferencesHelper;
import com.ahmadpour.formapp.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by behrooz on 1/29/18.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<List<Forms>> getForms() {
        return mDbHelper.getForms();
    }

    @Override
    public Observable<List<Questions>> getFormQuestions(long formId) {
        return mDbHelper.getFormQuestions(formId);
    }

    @Override
    public Observable<Long> insertAnswer(Answers answer) {
        return null;
    }
}