package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.source.entities.towns.TownsDataSource;

/**
 * Created by yonny on 7/16/16.
 */
public class TownsLocalDataSource implements TownsDataSource{

    private static TownsLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private TownsLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static TownsLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new TownsLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        //data will be loaded via Cursor loader
    }

    @Override
    public void getItem(@NonNull String townKey, @NonNull GetItemCallback callback) {
        //data will be loaded via Cursor loader
    }

}
