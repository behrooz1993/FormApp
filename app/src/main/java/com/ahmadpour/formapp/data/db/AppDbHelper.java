package com.ahmadpour.formapp.data.db;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.DaoMaster;
import com.ahmadpour.formapp.data.db.models.DaoSession;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.data.db.models.QuestionsDao;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by behrooz on 2/1/18.
 */

public class AppDbHelper implements DbHelper{

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Forms>> getForms() {
        return Observable.fromCallable(new Callable<List<Forms>>() {
            @Override
            public List<Forms> call() throws Exception {
                return mDaoSession.getFormsDao().loadAll();
            }
        });
    }

    @Override
    public Observable<List<Questions>> getFormQuestions(final long formId) {
        return Observable.fromCallable(new Callable<List<Questions>>() {
            @Override
            public List<Questions> call() throws Exception {
                return mDaoSession.queryBuilder(Questions.class).where(QuestionsDao.Properties.FormId.eq(formId)).list();
            }
        });
    }

    @Override
    public Observable<Long> insertAnswer(final Answers answer) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getAnswersDao().insert(answer);
            }
        });
    }
}
