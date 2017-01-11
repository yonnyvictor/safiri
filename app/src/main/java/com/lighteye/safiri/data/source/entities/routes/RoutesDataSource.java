package com.lighteye.safiri.data.source.entities.routes;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Route;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface RoutesDataSource {
    interface GetItemsCallback{
        void onRoutesLoaded(List<Route> items);
        void onRoutesNotAvailable();
    }

    interface GetItemCallback{
        void onRouteLoaded(Route item);
        void onRouteNotAvailable();
    }

    interface SearchItemsCallback{
        void onSearchRoutesLoaded(List<Route> items);
        void onSearchRoutesNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
    void searchItems(@NonNull String originKey, @NonNull String destinationKey,
                     @NonNull String day, @NonNull SearchItemsCallback callback);
}
