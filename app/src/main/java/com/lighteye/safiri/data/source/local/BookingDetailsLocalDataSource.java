package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsDataSource;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsValues;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingDetailsLocalDataSource implements BookingDetailsDataSource{

    private final static String BOOKING_CANCEL_STATUS = "Cancelled";
    private static BookingDetailsLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private BookingDetailsLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static BookingDetailsLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new BookingDetailsLocalDataSource(contentResolver);
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
    public void saveItem(@NonNull BookingDetail bookingDetail) {
        checkNotNull(bookingDetail);
        ContentValues values = BookingDetailsValues.form(bookingDetail);
        mContentResolver.insert(SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailsUri(), values);
    }

    @Override
    public void cancelItem(@NonNull BookingDetail bookingDetail) {
        ContentValues values = new ContentValues();
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_STATUS, BOOKING_CANCEL_STATUS);

        String selection = SafiriPersistenceContract.BookingDetailsEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(bookingDetail.getId())};

        mContentResolver.update(SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailUri(
                bookingDetail.getId()), values, selection, selectionArgs);
    }

    @Override
    public void cancelBookingItems(int bookingId) {
        ContentValues values = new ContentValues();
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_STATUS, BOOKING_CANCEL_STATUS);

        String selection = SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_ID + " = ?";
        String[] selectionArgs = {String.valueOf(bookingId)};

        mContentResolver.update(SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailsWithBookingUri(
                String.valueOf(bookingId)), values, selection, selectionArgs);
    }
}
