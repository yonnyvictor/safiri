package com.lighteye.safiri.data.source.entities.seats;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsRepository implements SeatsDataSource {

    private static SeatsRepository INSTANCE = null;

    private final SeatsDataSource mSeatsLocalDataSource;

    private SeatsRepository(SeatsDataSource seatsLocalDataSource) {
        this.mSeatsLocalDataSource = seatsLocalDataSource;
    }

    public static SeatsRepository getInstance(SeatsDataSource seatsLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new SeatsRepository(seatsLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onSeatsLoaded(null);
    }

    public void getItems(@NonNull String seatConfigurationId, @NonNull GetItemsCallback callback) {
        callback.onSeatsLoaded(null);
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        callback.onSeatLoaded(null);
    }

    public interface LoadDataCallback{
        void onSeatsDataLoaded(Cursor data);
        void onSeatsDataEmpty();
        void onSeatsDataNotAvailable();
        void onSeatsDataReset();
    }
}
