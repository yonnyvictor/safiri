package com.lighteye.safiri.signup;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;

/**
 * Created by yonny on 7/15/16.
 */
public interface SignupContract {
    interface View extends BaseView<Presenter> {
        void initializeScreen();
        void showLoginUi();
        void showProgressDialog(boolean active);
        void showSignupError();
    }

    interface Presenter extends BasePresenter {
        void createUser(String userName, String email, String password);
    }
}
