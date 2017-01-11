package com.lighteye.safiri.data.source.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by yonny on 7/16/16.
 */
public class SafiriAuthenticatorService extends Service {

    private SafiriAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        mAuthenticator = new SafiriAuthenticator(this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
