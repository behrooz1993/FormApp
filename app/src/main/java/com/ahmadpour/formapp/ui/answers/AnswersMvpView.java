package com.ahmadpour.formapp.ui.answers;

import com.ahmadpour.formapp.data.db.models.Answers;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by behrooz on 2/10/18.
 */

public interface AnswersMvpView extends MvpView {

    void fetchAnswersList(List<String> answers);

}
