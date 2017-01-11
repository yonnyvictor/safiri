package com.lighteye.safiri.data.source.entities.bookings;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.source.BaseLoader;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class BookingsLoader extends BaseLoader {

    public BookingsLoader(@NonNull Context context) {
        super(context);
    }

    public Loader<Cursor> createItemsLoader(){
        String sortOrder = SafiriPersistenceContract.BookingsEntry.COLUMN_TRAVEL_DATE + " DESC";
        return new CursorLoader(mContext,
                SafiriPersistenceContract.BookingsEntry.CONTENT_URI,
                SafiriPersistenceContract.BookingsEntry.BOOKING_COLUMNS,
                null,
                null,
                sortOrder
        );
    }

    public Loader<Cursor> createItemLoader(String bookingId){
        return new CursorLoader(mContext,
                SafiriPersistenceContract.BookingsEntry.buildBookingUri(Long.parseLong(bookingId)),
                SafiriPersistenceContract.BookingsEntry.BOOKING_COLUMNS,
                SafiriPersistenceContract.BookingsEntry.TABLE_NAME + "." +
                        SafiriPersistenceContract.BookingsEntry._ID + " = ?",
                new String[]{String.valueOf(bookingId)},
                null
        );
    }
}
