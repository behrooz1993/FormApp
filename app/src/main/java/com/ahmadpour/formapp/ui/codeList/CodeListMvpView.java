package com.ahmadpour.formapp.ui.codeList;

import com.ahmadpour.formapp.data.db.models.Codes;
import com.ahmadpour.formapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by behrooz on 2/11/18.
 */

public interface CodeListMvpView extends MvpView {

    void onFetchCodes(List<Codes> codes);
    void onExportCompleted(boolean success);

}
