package com.ahmadpour.formapp.ui.base;

/**
 * Created by behrooz on 2/2/18.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

//    void handleApiError(ANError error);
    void handleApiError(String error);

    void setUserAsLoggedOut();
}
