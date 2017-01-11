package com.lighteye.safiri.data.source.entities.bookings;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Booking;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface BookingsDataSource {
    interface GetItemsCallback{
        void onBookingsLoaded(List<Booking> items);
        void onBookingsNotAvailable();
    }

    interface GetItemCallback{
        void onBookingLoaded(Booking item);
        void onBookingNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
    int saveItem(@NonNull Booking booking);
    void cancelItem(@NonNull Booking booking);
}
