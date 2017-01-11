package com.lighteye.safiri.data.source.entities.bookingdetails;

import android.content.ContentValues;

import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingDetailsValues {

    public static ContentValues form(BookingDetail bookingDetail){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.BookingDetailsEntry._ID, bookingDetail.getId());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_ID, bookingDetail.getBookingId());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_ID, bookingDetail.getSeatId());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_CHARGE_ID, bookingDetail.getSeatChargeId());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_TRAVELLER, bookingDetail.getTraveller());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_CHARGE_KEY, bookingDetail.getSeatChargeKey());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_KEY, bookingDetail.getSeatKey());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_STATUS, bookingDetail.getStatus());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_CREATED, bookingDetail.getTimestampCreated());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_MODIFIED, bookingDetail.getTimestampLastChanged());
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_NODE_KEY, bookingDetail.getNodeKey());
        return values;
    }
}
