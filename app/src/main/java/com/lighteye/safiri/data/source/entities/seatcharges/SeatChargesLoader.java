package com.lighteye.safiri.data.source.entities.seatcharges;

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
public class SeatChargesLoader extends BaseLoader {

    public SeatChargesLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public Loader<Cursor> createItemsLoader() {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatChargesEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Loader<Cursor> createItemLoader(String seatChargeId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatChargesEntry.buildSeatChargeUri(seatChargeId),
                null,
                null,
                new String[]{String.valueOf(seatChargeId)},
                null
        );
    }

    public Loader<Cursor> currentSeatChargeLoader(String seatTypeId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatChargesEntry.buildCurrentSeatChargeUri(seatTypeId),
                null,
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_SEAT_TYPE_ID + " = ? AND " +
                        SafiriPersistenceContract.SeatChargesEntry.COLUMN_CURRENT + " = ?",
                new String[]{String.valueOf(seatTypeId), String.valueOf(1)},
                null
        );
    }
}
