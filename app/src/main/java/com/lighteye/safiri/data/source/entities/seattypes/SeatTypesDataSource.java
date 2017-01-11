package com.lighteye.safiri.data.source.entities.seattypes;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.SeatType;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface SeatTypesDataSource {
    interface GetItemsCallback{
        void onItemsLoaded(List<SeatType> items);
        void onDataNotAvailable();
    }

    interface GetItemCallback{
        void onItemLoaded(SeatType item);
        void onDataNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
}
