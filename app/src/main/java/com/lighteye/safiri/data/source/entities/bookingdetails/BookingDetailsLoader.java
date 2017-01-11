package com.lighteye.safiri.data.source.entities.bookingdetails;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.source.BaseLoader;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingDetailsLoader extends BaseLoader {

    public BookingDetailsLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public Loader<Cursor> createItemsLoader() {
        return null;
    }

    @Override
    public Loader<Cursor> createItemLoader(String itemId) {
        return null;
    }

    public Loader<Cursor> createItemsLoader(String bookingId){
        return new CursorLoader(
                mContext,
                SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailsWithBookingUri(bookingId),
                SafiriPersistenceContract.BookingDetailsEntry.BOOKING_DETAILS_COLUMNS,
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_ID + " = ?",
                new String[]{String.valueOf(bookingId)},
                null
        );
    }



}
