package com.lighteye.safiri.data.source.entities.towns;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/16/16.
 */
public class TownsRepository implements TownsDataSource {

    private static TownsRepository INSTANCE = null;

    private final TownsDataSource mTownsLocalDataSource;

    private TownsRepository(TownsDataSource townsLocalDataSource) {
        this.mTownsLocalDataSource = townsLocalDataSource;
    }

    public static TownsRepository getInstance(TownsDataSource townsLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new TownsRepository(townsLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onItemsLoaded(null);
    }

    @Override
    public void getItem(@NonNull String townKey, @NonNull GetItemCallback callback) {
        callback.onItemLoaded(null);
    }

    public interface LoadDataCallback{
        void onDataLoaded(Cursor data);
        void onDataEmpty();
        void onDataNotAvailable();
        void onDataReset();
    }
}
