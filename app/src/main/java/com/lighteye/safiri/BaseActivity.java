package com.lighteye.safiri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.lighteye.safiri.login.LoginActivity;
import com.lighteye.safiri.utils.Constants;

/**
 * Created by yonny on 7/14/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String mUserName, mEmail, mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        mUid = sp.getString(Constants.KEY_UID, null);
        mEmail = sp.getString(Constants.KEY_EMAIL, null);
        mUserName = sp.getString(Constants.KEY_USERNAME, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        if(id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void logout(){
        FirebaseAuth.getInstance().signOut();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(Constants.KEY_UID, null);
        spe.putString(Constants.KEY_EMAIL, null);
        spe.putString(Constants.KEY_USERNAME, null);

        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    protected void setupWindowAnimations() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Fade fade = new Fade();
//            fade.setDuration(1000);
//            //getWindow().setEnterTransition(fade);
//            //getWindow().setExitTransition(fade);
//            //getWindow().setReturnTransition(fade);
//            //getWindow().setReenterTransition(fade);
//        }

    }
}
