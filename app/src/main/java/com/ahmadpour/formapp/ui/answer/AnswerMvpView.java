package com.ahmadpour.formapp.ui.answer;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by behrooz on 2/2/18.
 */

public interface AnswerMvpView extends MvpView{

    void fetchAnswers(List<Answers> answers);
    void openMainActivity();

}
