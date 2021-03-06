package com.ahmadpour.formapp.data;

import android.content.Context;
import android.database.Cursor;

import com.ahmadpour.formapp.data.db.DbHelper;
import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Codes;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.data.prefs.PreferencesHelper;
import com.ahmadpour.formapp.di.ApplicationContext;

import java.util.ArrayList;
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
    public Observable<List<Answers>> getAnswers(long formId,String date) {
        return mDbHelper.getAnswers(formId,date);
    }

    @Override
    public Observable<Questions> getAnswerQuestion(long questionId) {
        return mDbHelper.getAnswerQuestion(questionId);
    }

    @Override
    public Observable<Boolean> deleteFormTempAnswers(long formId) {
        return mDbHelper.deleteFormTempAnswers(formId);
    }

    @Override
    public Observable<Boolean> updateFormAnswers(ArrayList<Answers> answers) {
        return mDbHelper.updateFormAnswers(answers);
    }

    @Override
    public Observable<Boolean> deleteFormAnswers(long formId) {
        return mDbHelper.deleteFormAnswers(formId);
    }

    @Override
    public Observable<Long> insertAnswer(Answers answer) {
        return null;
    }

    @Override
    public Observable<Long> inserAnswers(ArrayList<Answers> answers) {
        return mDbHelper.inserAnswers(answers);
    }

    @Override
    public Observable<List<Forms>> getAnsweredForms() {
        return mDbHelper.getAnsweredForms();
    }

    @Override
    public Observable<Boolean> deleteTempAnswers() {
        return mDbHelper.deleteTempAnswers();
    }

    @Override
    public Observable<List<String>> getFormAnswerList(long formId) {
        return mDbHelper.getFormAnswerList(formId);
    }

    @Override
    public Observable<Long> insertCode(Codes code) {
        return mDbHelper.insertCode(code);
    }

    @Override
    public Observable<List<Codes>> getCodes() {
        return mDbHelper.getCodes();
    }

    @Override
    public Observable<Cursor> getCodeTable() {
        return mDbHelper.getCodeTable();
    }
}