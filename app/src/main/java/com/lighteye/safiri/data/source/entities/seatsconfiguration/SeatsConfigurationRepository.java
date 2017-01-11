package com.lighteye.safiri.data.source.entities.seatsconfiguration;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsConfigurationRepository implements  SeatsConfigurationDataSource{

    private static SeatsConfigurationRepository INSTANCE = null;

    private final SeatsConfigurationDataSource mSeatsConfigurationLocalDataSource;

    private SeatsConfigurationRepository(
            SeatsConfigurationDataSource seatsConfigurationLocalDataSource) {
        this.mSeatsConfigurationLocalDataSource = seatsConfigurationLocalDataSource;
    }

    public static SeatsConfigurationRepository getInstance(
            SeatsConfigurationDataSource seatsConfigurationLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new SeatsConfigurationRepository(seatsConfigurationLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onSeatsConfigurationsLoaded(null);
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        callback.onSeatsConfigurationLoaded(null);
    }

    public void getItem(@NonNull String organizationId, @NonNull String fleetTypeId, @NonNull GetItemCallback callback) {
        callback.onSeatsConfigurationLoaded(null);
    }

    public interface LoadDataCallback{
        void onSeatsConfigurationsDataLoaded(Cursor data);
        void onSeatsConfigurationsDataEmpty();
        void onSeatsConfigurationsDataNotAvailable();
        void onSeatsConfigurationsDataReset();
    }
}
