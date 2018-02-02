package com.ahmadpour.formapp.ui.answerList;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by behrooz on 2/2/18.
 */

public interface AnswerListMvpView extends MvpView {
    void refreshForms(List<Forms> forms);
    void openAnswerActivity(int position);
}
