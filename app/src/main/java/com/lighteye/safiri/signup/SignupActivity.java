package com.lighteye.safiri.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import com.lighteye.safiri.BaseActivity;
import com.lighteye.safiri.R;
import com.lighteye.safiri.login.LoginActivity;
import com.lighteye.safiri.utils.Utils;

import static com.google.common.base.Preconditions.checkNotNull;

public class SignupActivity extends BaseActivity implements SignupContract.View {

    private static final String LOG_TAG = SignupActivity.class.getSimpleName();
    private View mParentView;
    private ProgressDialog mAuthProgressDialog;
    private EditText mEditTextUsernameCreate, mEditTextEmailCreate, mEditTextPasswordCreate;
    private SignupContract.Presenter mPresenter;

    private String mUserName, mUserEmail, mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializeScreen();

        mPresenter = new SignupPresenter(this);
    }


    @Override
    public void initializeScreen() {
        mParentView = (View) findViewById(R.id.linear_layout_signup_activity);
        mEditTextUsernameCreate = (EditText) findViewById(R.id.edit_text_username_create);
        mEditTextEmailCreate = (EditText) findViewById(R.id.edit_text_email_create);
        mEditTextPasswordCreate = (EditText) findViewById(R.id.edit_text_password_create);

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getResources().getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getResources().getString(R.string.progress_dialog_creating_user_with_firebase));
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void showLoginUi() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgressDialog(boolean active) {
        if(active)
            mAuthProgressDialog.show();
        else
            mAuthProgressDialog.dismiss();
    }

    @Override
    public void showSignupError() {
        showMessage("Signup Error, Try again");
    }

    @Override
    public void setPresenter(SignupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    /**
     * Open LoginActivity when user taps on "Sign in" textView
     */
    public void onSignInPressed(View view) {
        showLoginUi();
    }

    /**
     * Create new account using Firebase email/password provider
     */
    public void onCreateAccountPressed(View view) {

        mUserName = mEditTextUsernameCreate.getText().toString();
        mUserEmail = mEditTextEmailCreate.getText().toString();
        mPassword = mEditTextPasswordCreate.getText().toString();

        if (mUserName.equals("")) {
            mEditTextUsernameCreate.setError(getString(R.string.error_cannot_be_empty));
            return;
        }

        if (mUserEmail.equals("")) {
            mEditTextEmailCreate.setError(getString(R.string.error_cannot_be_empty));
            return;
        }

        if (mPassword.equals("")) {
            mEditTextPasswordCreate.setError(getString(R.string.error_cannot_be_empty));
            return;
        }

        if(mUserName != null && Utils.isEmailValid(mUserEmail) && Utils.isPasswordValid(mPassword)){
            mPresenter.createUser(mUserName, mUserEmail, mPassword);
        }

    }

    private void showMessage(String message){
        Snackbar.make(mParentView, message, Snackbar.LENGTH_LONG).show();
    }
}
