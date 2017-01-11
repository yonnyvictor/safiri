package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.source.entities.routes.RoutesDataSource;

/**
 * Created by yonny on 7/22/16.
 */
public class RoutesLocalDataSource implements RoutesDataSource {

    private static RoutesLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private RoutesLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static RoutesLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new RoutesLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        //loaded via cursor loader
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        //loaded via cursor loader
    }

    @Override
    public void searchItems(@NonNull String originKey, @NonNull String destinationKey,
                            @NonNull String day, @NonNull SearchItemsCallback callback) {
        //loaded via cursor loader
    }
}
