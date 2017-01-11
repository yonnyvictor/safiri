package com.lighteye.safiri.data.source.entities.seatsconfiguration;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.SeatsConfiguration;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface SeatsConfigurationDataSource {
    interface GetItemsCallback{
        void onSeatsConfigurationsLoaded(List<SeatsConfiguration> items);
        void onSeatsConfigurationsNotAvailable();
    }

    interface GetItemCallback{
        void onSeatsConfigurationLoaded(SeatsConfiguration item);
        void onSeatsConfigurationNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
}
