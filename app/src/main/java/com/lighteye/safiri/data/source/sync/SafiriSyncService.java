package com.lighteye.safiri.data.source.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by yonny on 7/16/16.
 */
public class SafiriSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static SafiriSyncAdapter sSafiriSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("SafiriSyncService", "onCreate - SafiriSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSafiriSyncAdapter == null) {
                sSafiriSyncAdapter = new SafiriSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSafiriSyncAdapter.getSyncAdapterBinder();
    }


}
