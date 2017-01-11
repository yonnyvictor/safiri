package com.lighteye.safiri.login;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;

/**
 * Created by yonny on 7/14/16.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void initializeScreen();
        void setLoadingDialog(boolean active);
        void showLoginError();
        void showSignUpUi();
        void showMainUi();
    }

    interface Presenter extends BasePresenter {
        void addFirebaseAuthListener();
        void removeFirebaseAuthListener();
        void login(String email, String password);
    }
}
