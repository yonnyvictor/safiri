package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesDataSource;

/**
 * Created by yonny on 7/27/16.
 */
public class SeatChargesLocalDataSource implements SeatChargesDataSource {

    private static SeatChargesLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private SeatChargesLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static SeatChargesLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new SeatChargesLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {

    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
