package com.lighteye.safiri.data.source.entities.bookingdetails;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.BookingDetail;

import java.util.List;

/**
 * Created by yonny on 7/27/16.
 */
public interface BookingDetailsDataSource {

    interface GetItemsCallback{
        void onBookingDetailsLoaded(List<BookingDetail> items);
        void onBookingDetailsNotAvailable();
    }

    interface GetItemCallback{
        void onBookingDetailLoaded(BookingDetail item);
        void onBookingDetailNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
    void saveItem(@NonNull BookingDetail bookingDetail);
    void cancelItem(@NonNull BookingDetail bookingDetail);
    void cancelBookingItems(int bookingId);
}
