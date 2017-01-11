package com.lighteye.safiri.signup;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.lighteye.safiri.data.User;
import com.lighteye.safiri.utils.Constants;

import java.util.HashMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/15/16.
 */
public class SignupPresenter implements SignupContract.Presenter {

    private FirebaseAuth mAuth;
    private String mUserName, mUserEmail;
    private final SignupContract.View mSignupView;

    public SignupPresenter(@NonNull SignupContract.View signupView) {
        mAuth = FirebaseAuth.getInstance();
        mSignupView = checkNotNull(signupView, "View cannot be null");
    }

    @Override
    public void createUser(String userName, String email, String password) {
        mUserName = userName;
        mUserEmail = email;

        Activity activity = (Activity)mSignupView;
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mSignupView.showProgressDialog(false);
                if(!task.isSuccessful()){
                    mSignupView.showSignupError();
                }else{
                    String uid = task.getResult().getUser().getUid();
                    createUserInFirebaseHelper(uid);
                }
            }
        });
    }

    @Override
    public void start() {

    }

    /**
     * Creates a new user in Firebase from the Java POJO
     */
    private void createUserInFirebaseHelper(String uid) {
        HashMap<String, Object> timestampMap = new HashMap<>();
        timestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        User user = new User(mUserEmail, mUserName, timestampMap);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_LOCATION_USERS)
                .child(uid);
        ref.setValue(user);
    }
}
