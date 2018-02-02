package com.ahmadpour.formapp.ui.formList;

import com.ahmadpour.formapp.data.db.models.Forms;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by behrooz on 2/2/18.
 */

public interface FormListMvpView extends MvpView {
    void openFormActivity(int position);

    void refreshFormList(List<Forms> forms);
}
