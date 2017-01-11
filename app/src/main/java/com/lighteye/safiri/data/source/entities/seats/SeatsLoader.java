package com.lighteye.safiri.data.source.entities.seats;

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
public class SeatsLoader extends BaseLoader {

    public SeatsLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public Loader<Cursor> createItemsLoader() {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatsEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    public Loader<Cursor> createItemsLoader(String seatsConfigurationId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatsEntry.buildSeatsWithSeatsConfigurationUri(seatsConfigurationId),
                null,
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEATS_CONFIGURATION_ID + " = ?",
                new String[]{String.valueOf(seatsConfigurationId)},
                null
        );
    }

    @Override
    public Loader<Cursor> createItemLoader(String seatId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatsEntry.buildSeatUri(seatId),
                null,
                null,
                new String[]{String.valueOf(seatId)},
                null
        );
    }
}
