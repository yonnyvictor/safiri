package com.lighteye.safiri.data.source.entities.bookingdetails;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.BookingDetail;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingDetailsRepository implements BookingDetailsDataSource {

    private static BookingDetailsRepository INSTANCE = null;

    private final BookingDetailsDataSource mBookingDetailsLocalDataSource;

    private BookingDetailsRepository(BookingDetailsDataSource bookingDetailsLocalDataSource) {
        this.mBookingDetailsLocalDataSource = bookingDetailsLocalDataSource;
    }

    public static BookingDetailsRepository getInstance(BookingDetailsDataSource bookingDetailsLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new BookingDetailsRepository(bookingDetailsLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onBookingDetailsLoaded(null);
    }

    public void getBookingItems(String bookingId, @NonNull GetItemsCallback callback){
        callback.onBookingDetailsLoaded(null);
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        callback.onBookingDetailLoaded(null);
    }

    @Override
    public void saveItem(@NonNull BookingDetail bookingDetail) {
        checkNotNull(bookingDetail);
        mBookingDetailsLocalDataSource.saveItem(bookingDetail);
    }

    @Override
    public void cancelItem(@NonNull BookingDetail bookingDetail) {
        checkNotNull(bookingDetail);
        mBookingDetailsLocalDataSource.cancelItem(bookingDetail);
    }

    @Override
    public void cancelBookingItems(int bookingId) {
        mBookingDetailsLocalDataSource.cancelBookingItems(bookingId);
    }

    public interface LoadDataCallback{
        void onBookingDetailsDataLoaded(Cursor data);
        void onBookingDetailsDataEmpty();
        void onBookingDetailsDataNotAvailable();
        void onBookingDetailsDataReset();
    }
}
