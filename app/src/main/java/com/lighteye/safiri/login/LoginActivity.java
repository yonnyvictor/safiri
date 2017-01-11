package com.lighteye.safiri.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lighteye.safiri.BaseActivity;
import com.lighteye.safiri.R;
import com.lighteye.safiri.data.source.sync.SafiriSyncAdapter;
import com.lighteye.safiri.signup.SignupActivity;
import com.lighteye.safiri.today.TodayActivity;
import com.lighteye.safiri.utils.Utils;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    /* A dialog that is presented until the Firebase authentication finished. */
    private ProgressDialog mAuthProgressDialog;
    private EditText mEditTextEmailInput, mEditTextPasswordInput;
    private View mParentView;
    private String mUserEmail, mPassword;

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeScreen();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mPresenter = new LoginPresenter(this, sp);

        /**
         * Call signInPassword() when user taps "Done" keyboard action
         */
        mEditTextPasswordInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    signInPassword();
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.addFirebaseAuthListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.removeFirebaseAuthListener();
    }

    @Override
    public void initializeScreen() {
        mParentView = (View) findViewById(R.id.linear_layout_login_activity);
        mEditTextEmailInput = (EditText) findViewById(R.id.edit_text_email);
        mEditTextPasswordInput = (EditText) findViewById(R.id.edit_text_password);
        //LinearLayout linearLayoutLoginActivity = (LinearLayout) findViewById(R.id.linear_layout_login_activity);
        //initializeBackground(linearLayoutLoginActivity);
        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void setLoadingDialog(boolean active) {
        if(active)
            mAuthProgressDialog.show();
        else
            mAuthProgressDialog.dismiss();
    }

    @Override
    public void showLoginError() {
        showMessage("Authentication failed!");
    }

    @Override
    public void showSignUpUi() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMainUi() {
        SafiriSyncAdapter.initializeSyncAdapter(this);
        Bundle bundle = Utils.activityTransition(this);
        Intent intent = new Intent(LoginActivity.this, TodayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, bundle);
        finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    /**
     * Sign in with Password provider (used when user taps "Done" action on keyboard)
     */
    public void signInPassword() {
        mUserEmail = mEditTextEmailInput.getText().toString();
        mPassword = mEditTextPasswordInput.getText().toString();
        boolean validEmail = false, validPassword = false;

        if(Utils.isEmailValid(mUserEmail)) {
            validEmail = true;
        }else {
            mEditTextEmailInput.setError(getString(R.string.error_invalid_email_not_valid));
        }

        if(Utils.isPasswordValid(mPassword)) {
            validPassword = true;
        }else {
            mEditTextPasswordInput.setError(getString(R.string.error_cannot_be_empty));
        }


        if(validEmail && validPassword){
            setLoadingDialog(true);
            mPresenter.login(mUserEmail, mPassword);
        }
    }

    /**
     * Sign in with Password provider when user clicks sign in button
     */
    public void onSignInPressed(View view) {
        signInPassword();
    }

    /**
     * Open SignupActivity when user taps on "Sign up" TextView
     */
    public void onSignUpPressed(View view) {
        showSignUpUi();
    }


    private void showMessage(String message){
        Snackbar.make(mParentView, message, Snackbar.LENGTH_LONG).show();
    }

    private void firstRunSetup(){

    }
}
