package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationDataSource;

/**
 * Created by yonny on 7/27/16.
 */
public class SeatsConfigurationLocalDataSource implements SeatsConfigurationDataSource {

    private static SeatsConfigurationLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private SeatsConfigurationLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static SeatsConfigurationLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new SeatsConfigurationLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {

    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
