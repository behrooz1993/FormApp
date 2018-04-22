package com.ahmadpour.formapp.ui.codeList;

import com.ahmadpour.formapp.ui.base.MvpPresenter;

/**
 * Created by behrooz on 2/11/18.
 */

public interface CodeListMvpPresenter<V extends CodeListMvpView> extends MvpPresenter<V> {

    void fetchCodes();
    void btnExportClicked();
}
