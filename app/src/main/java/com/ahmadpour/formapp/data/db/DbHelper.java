package com.ahmadpour.formapp.data.db;


import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.data.db.models.Questions;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by behrooz on 2/1/18.
 */

public interface DbHelper {

    Observable<List<Forms>> getForms();

    Observable<List<Questions>> getFormQuestions(long formId);

    Observable<Long> insertAnswer(Answers answer);

}
