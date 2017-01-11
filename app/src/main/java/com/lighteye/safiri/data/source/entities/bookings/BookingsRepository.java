package com.lighteye.safiri.data.source.entities.bookings;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.source.remote.BookingsRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/20/16.
 */
public class BookingsRepository implements BookingsDataSource {

    private static BookingsRepository INSTANCE = null;

    private final BookingsDataSource mBookingsLocalDataSource;
    private final BookingsRemoteDataSource mBookingsRemoteDataSource;

    private BookingsRepository(BookingsDataSource bookingsLocalDataSource) {
        mBookingsLocalDataSource = bookingsLocalDataSource;
        mBookingsRemoteDataSource = new BookingsRemoteDataSource();
    }

    public static BookingsRepository getInstance(BookingsDataSource bookingsLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new BookingsRepository(bookingsLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onBookingsLoaded(null);
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        callback.onBookingLoaded(null);
    }

    @Override
    public int saveItem(@NonNull Booking booking) {
        checkNotNull(booking);
        return mBookingsLocalDataSource.saveItem(booking);
    }

    @Override
    public void cancelItem(@NonNull Booking booking) {
        mBookingsLocalDataSource.cancelItem(booking);
    }

    public void upstreamSync(Booking booking, BookingDetail bookingDetail){
        mBookingsRemoteDataSource.saveBooking(booking, bookingDetail);
    }

    public interface LoadDataCallback{
        void onBookingsDataLoaded(Cursor data);
        void onBookingsDataEmpty();
        void onBookingsDataNotAvailable();
        void onBookingsDataReset();
    }
}
