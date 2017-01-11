package com.lighteye.safiri.data.source.entities.routes;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/20/16.
 */
public class RoutesRepository implements RoutesDataSource {

    private static RoutesRepository INSTANCE = null;

    private final RoutesDataSource mRoutesLocalDataSource;

    private RoutesRepository(RoutesDataSource routesLocalDataSource) {
        this.mRoutesLocalDataSource = routesLocalDataSource;
    }

    public static RoutesRepository getInstance(RoutesDataSource routesLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new RoutesRepository(routesLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onRoutesLoaded(null);
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        callback.onRouteLoaded(null);
    }

    @Override
    public void searchItems(@NonNull String originKey, @NonNull String destinationKey,
                            @NonNull String day, @NonNull SearchItemsCallback callback) {
        callback.onSearchRoutesLoaded(null);
    }

    public interface LoadDataCallback{
        void onRoutesDataLoaded(Cursor data);
        void onRoutesDataEmpty();
        void onSearchRoutesDataLoaded(Cursor data);
        void onSearchRoutesDataEmpty();
        void onRoutesDataNotAvailable();
        void onRoutesDataReset();
        void onSearchRoutesDataReset();
    }
}
