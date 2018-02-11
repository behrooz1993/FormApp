package com.ahmadpour.formapp.data.db;


import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Codes;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.data.db.models.Questions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by behrooz on 2/1/18.
 */

public interface DbHelper {

    Observable<List<Forms>> getForms();

    Observable<List<Questions>> getFormQuestions(long formId);

    Observable<List<Answers>> getAnswers(long formId);

    Observable<Questions> getAnswerQuestion(long questionId);

    Observable<Boolean> deleteFormTempAnswers(long formId);

    Observable<Boolean> deleteFormAnswers(long formId);

    Observable<Boolean> updateFormAnswers(ArrayList<Answers> answers);

    Observable<Long> insertAnswer(Answers answer);

    Observable<Long> inserAnswers(ArrayList<Answers> answers);

    Observable<List<Forms>> getAnsweredForms();

    Observable<Boolean> deleteTempAnswers();

    Observable<List<String>> getFormAnswerList(long formId);

    Observable<Long> insertCode(Codes code);

    Observable<List<Codes>> getCodes();

}
