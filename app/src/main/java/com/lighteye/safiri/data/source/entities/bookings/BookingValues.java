package com.lighteye.safiri.data.source.entities.bookings;

import android.content.ContentValues;

import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class BookingValues {

    public static ContentValues form(Booking booking){

        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.BookingsEntry._ID, booking.getId());
        values.put(SafiriPersistenceContract.BookingsEntry.COLUMN_ROUTE_ID, booking.getRouteId());
        values.put(SafiriPersistenceContract.BookingsEntry.COLUMN_TRAVEL_DATE, booking.getTravelDate());
        values.put(SafiriPersistenceContract.BookingsEntry.COLUMN_USER_KEY, booking.getUserId());
        values.put(SafiriPersistenceContract.BookingsEntry.COLUMN_ROUTE_KEY, booking.getRouteKey());
        values.put(SafiriPersistenceContract.BookingsEntry.COLUMN_STATUS, booking.getStatus());
        return values;
    }
}
