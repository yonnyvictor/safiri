package com.lighteye.safiri.login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.lighteye.safiri.data.source.sync.SafiriSyncAdapter;
import com.lighteye.safiri.utils.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/14/16.
 */
public class LoginPresenter implements LoginContract.Presenter{

    private final SharedPreferences mSharedPreferences;
    private final LoginContract.View mLoginView;
    private final FirebaseAuth mAuth;
    private final FirebaseAuth.AuthStateListener mAuthListener;

    public LoginPresenter(@NonNull LoginContract.View loginView,
                          @NonNull SharedPreferences sharedPreferences) {
        mSharedPreferences = checkNotNull(sharedPreferences, "shared preferences cannot be null!");
        mLoginView = checkNotNull(loginView, "LoginView cannot be null!");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mLoginView.setLoadingDialog(false);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //User is signed in
                    SharedPreferences.Editor spe = mSharedPreferences.edit();
                    spe.putString(Constants.KEY_UID, user.getUid()).apply();
                    spe.putString(Constants.KEY_EMAIL, user.getEmail()).apply();

                    user.getToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if(task.isSuccessful()){
                                SharedPreferences.Editor spe = mSharedPreferences.edit();
                                spe.putString(Constants.KEY_TOKEN, task.getResult().getToken()).apply();
                            }
                        }
                    });


                    mLoginView.showMainUi();
                }
            }
        };
    }

    @Override
    public void login(String email, String password) {
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);
        firebaseAuthenticate(credential);
    }

    @Override
    public void start() {
        addFirebaseAuthListener();
    }

    @Override
    public void addFirebaseAuthListener() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void removeFirebaseAuthListener() {
        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void firebaseAuthenticate(AuthCredential credential){
        Activity activity = (Activity)mLoginView;
        mAuth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Log.d(LOG_TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                if(!task.isSuccessful()){
                    //Log.w(LOG_TAG, "signInWithCredential", task.getException());
                    //showErrorToast("Sign In with credential Authentication Failed");
                    mLoginView.showLoginError();
                }
            }
        });
    }


}
