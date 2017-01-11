package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.source.entities.bookings.BookingValues;
import com.lighteye.safiri.data.source.entities.bookings.BookingsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingsLocalDataSource implements BookingsDataSource {

    private final static String BOOKING_CANCEL_STATUS = "Cancelled";
    private static BookingsLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private BookingsLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static BookingsLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new BookingsLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        //loaded via cursor loader
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {
        //loaded via cursor loader
    }

    @Override
    public int saveItem(@NonNull Booking booking) {
        checkNotNull(booking);

        ContentValues values = BookingValues.form(booking);
        Uri uri = mContentResolver.insert(SafiriPersistenceContract.BookingsEntry.buildBookingsUri(), values);

        long id = ContentUris.parseId(uri);

        return (int)id;
    }

    @Override
    public void cancelItem(@NonNull Booking booking) {
        ContentValues values = new ContentValues();
        values.put(SafiriPersistenceContract.BookingsEntry.COLUMN_STATUS, BOOKING_CANCEL_STATUS);

        String selection = SafiriPersistenceContract.BookingsEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(booking.getId())};

        mContentResolver.update(SafiriPersistenceContract.BookingsEntry.buildBookingUri(
                booking.getId()), values, selection, selectionArgs);
    }
}
