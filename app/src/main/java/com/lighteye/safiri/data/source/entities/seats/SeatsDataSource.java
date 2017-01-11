package com.lighteye.safiri.data.source.entities.seats;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Seat;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface SeatsDataSource {
    interface GetItemsCallback{
        void onSeatsLoaded(List<Seat> items);
        void onSeatsNotAvailable();
    }

    interface GetItemCallback{
        void onSeatLoaded(Seat item);
        void onSeatNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
}
