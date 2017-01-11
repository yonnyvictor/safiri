package com.lighteye.safiri.data.source.entities.fleettypes;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.FleetType;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface FleetTypesDataSource {
    interface GetItemsCallback{
        void onItemsLoaded(List<FleetType> items);
        void onDataNotAvailable();
    }

    interface GetItemCallback{
        void onItemLoaded(FleetType item);
        void onDataNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
}
