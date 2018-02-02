package com.ahmadpour.formapp.ui.form;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Questions;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by behrooz on 2/2/18.
 */

public interface FormMvpView extends MvpView{

    void refreshQuestions(List<Questions> questions);
    void deleteCurrentAnswers();
    void openAnswerPage();

}
