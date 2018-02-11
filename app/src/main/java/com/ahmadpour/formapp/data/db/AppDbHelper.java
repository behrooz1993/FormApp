package com.ahmadpour.formapp.data.db;

import android.database.Cursor;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.AnswersDao;
import com.ahmadpour.formapp.data.db.models.Codes;
import com.ahmadpour.formapp.data.db.models.DaoMaster;
import com.ahmadpour.formapp.data.db.models.DaoSession;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.data.db.models.FormsDao;
import com.ahmadpour.formapp.data.db.models.Options;
import com.ahmadpour.formapp.data.db.models.OptionsDao;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.data.db.models.QuestionsDao;
import com.ahmadpour.formapp.utils.AppConstants;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by behrooz on 2/1/18.
 */

public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.openDatabase()).newSession();
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
                List<Questions> questions = mDaoSession.queryBuilder(Questions.class)
                        .where(QuestionsDao.Properties.FormId.eq(formId)).list();
                for (Questions question : questions) {
                    question.getOptions().addAll(mDaoSession.queryBuilder(Options.class)
                            .where(OptionsDao.Properties.FormId.eq(1),
                                    OptionsDao.Properties.QuestionId.eq(question.getId())).list());
                }
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

    @Override
    public Observable<Boolean> updateFormAnswers(ArrayList<Answers> answers) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Boolean result = true;
                for (Answers answer : answers) {
                    mDaoSession.update(answer);
                }
                return result;
            }
        });
    }

    @Override
    public Observable<Long> inserAnswers(ArrayList<Answers> answers) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Long id = 0l;
                for (Answers answer : answers) {
                    id = mDaoSession.insert(answer);
                }
                return id;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteFormTempAnswers(long formId) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                List<Answers> lstAnswers = mDaoSession.queryBuilder(Answers.class).where(AnswersDao.Properties.FormId.eq(formId),
                        AnswersDao.Properties.Temp.eq(1)).list();
                mDaoSession.getAnswersDao().deleteInTx(lstAnswers);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteFormAnswers(long formId) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                List<Answers> lstAnswers = mDaoSession.queryBuilder(Answers.class).where(AnswersDao.Properties.FormId.eq(formId)).list();
                mDaoSession.getAnswersDao().deleteInTx(mDaoSession.queryBuilder(Answers.class).where(AnswersDao.Properties.FormId.eq(formId)).list());
                return true;
            }
        });
    }

    @Override
    public Observable<List<Answers>> getAnswers(long formId) {
        return Observable.fromCallable(new Callable<List<Answers>>() {
            @Override
            public List<Answers> call() throws Exception {
                List<Answers> answers = mDaoSession.queryBuilder(Answers.class).where(AnswersDao.Properties.FormId.eq(formId)).list();
                for (Answers answer : answers) {
                    Questions question = mDaoSession.queryBuilder(Questions.class)
                            .where(QuestionsDao.Properties.Id.eq(answer.getQuestionId())).unique();
                    if (question.getOptions().size() == 0 && question.getType() == 2) {
                        question.getOptions().addAll(mDaoSession.queryBuilder(Options.class)
                                .where(OptionsDao.Properties.FormId.eq(1),
                                        OptionsDao.Properties.QuestionId.eq(question.getId())).list());
                    }
                    answer.setQuestion(question);
                }
                return answers;
            }
        });
    }

    @Override
    public Observable<Questions> getAnswerQuestion(long questionId) {
        return Observable.fromCallable(new Callable<Questions>() {
            @Override
            public Questions call() throws Exception {
                Questions question = mDaoSession.queryBuilder(Questions.class).where(QuestionsDao.Properties.Id.eq(questionId)).unique();
                question.getOptions().addAll(mDaoSession.queryBuilder(Options.class)
                        .where(OptionsDao.Properties.FormId.eq(1),
                                OptionsDao.Properties.QuestionId.eq(questionId)).list());
                return question;
            }
        });
    }

    @Override
    public Observable<List<Forms>> getAnsweredForms() {
        return Observable.fromCallable(new Callable<List<Forms>>() {
            @Override
            public List<Forms> call() throws Exception {
                List<Forms> forms = new ArrayList<>();
                List<Answers> answers = mDaoSession.getAnswersDao().loadAll();
                if (answers.size() > 0) {
                    forms.add(mDaoSession.queryBuilder(Forms.class).where(FormsDao.Properties.Id.eq(1)).unique());
                }
                return forms;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteTempAnswers() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                List<Answers> lstAnswers = mDaoSession.queryBuilder(Answers.class).where(
                        AnswersDao.Properties.Temp.eq(1)).list();
                mDaoSession.getAnswersDao().deleteInTx(lstAnswers);
                return true;
            }
        });
    }

//    @Override
//    public Observable<List<Answers>> getFormAnswerList(long formId) {
//        return Observable.fromCallable(new Callable<ArrayList<Answers>>() {
//            @Override
//            public List<Answers> call() throws Exception {
    ;
//            }
//        });
//    }


    @Override
    public Observable<List<String>> getFormAnswerList(long formId) {
        return Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                String SQL_DISTINCT_DATE = "SELECT DISTINCT " + AnswersDao.Properties.Date.columnName + " FROM " + AnswersDao.TABLENAME;
                ArrayList<String> result = new ArrayList<>();
                Cursor c = mDaoSession.getDatabase().rawQuery(SQL_DISTINCT_DATE, null);
                try {
                    if (c.moveToFirst()) {
                        do {
                            result.add(c.getString(0));
                        } while (c.moveToNext());
                    }
                } finally {
                    c.close();
                }
                return result;
            }
        });
    }

    @Override
    public Observable<List<Codes>> getCodes() {
        return Observable.fromCallable(new Callable<List<Codes>>() {
            @Override
            public List<Codes> call() throws Exception {
                List<Codes> codes = mDaoSession.getCodesDao().loadAll();
                for (Codes code : codes) {
                    code.setForm(mDaoSession.queryBuilder(Forms.class).where(FormsDao.Properties.Id.eq(code.getFormId())).unique());
                }
                return codes;
            }
        });
    }
}
