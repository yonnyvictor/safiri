package com.lighteye.safiri.data.source.entities.towns;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Town;

import java.util.List;

/**
 * Created by yonny on 7/16/16.
 */
public interface TownsDataSource {

    interface GetItemsCallback {
        void onItemsLoaded(List<Town> items);
        void onDataNotAvailable();
    }

    interface GetItemCallback {
        void onItemLoaded(Town item);
        void onDataNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String townKey, @NonNull GetItemCallback callback);
}
