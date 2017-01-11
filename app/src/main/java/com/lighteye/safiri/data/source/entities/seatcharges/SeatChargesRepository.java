package com.lighteye.safiri.data.source.entities.seatcharges;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatChargesRepository implements SeatChargesDataSource {

    private static SeatChargesRepository INSTANCE = null;

    private final SeatChargesDataSource mSeatChargesLocalDataSource;

    private SeatChargesRepository(SeatChargesDataSource seatChargesLocalDataSource) {
        this.mSeatChargesLocalDataSource = seatChargesLocalDataSource;
    }

    public static SeatChargesRepository getInstance(SeatChargesDataSource seatChargesLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new SeatChargesRepository(seatChargesLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onSeatChargesLoaded(null);
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        callback.onSeatChargeLoaded(null);
    }

    public interface LoadDataCallback{
        void onDataLoaded(Cursor data);
        void onDataEmpty();
        void onDataNotAvailable();
        void onDataReset();
    }
}
