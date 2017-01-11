package com.lighteye.safiri.data.source.entities.towns;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.source.BaseLoader;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/16/16.
 */
public class TownsLoader extends BaseLoader {

    public TownsLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public Loader<Cursor> createItemsLoader() {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.TownsEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Loader<Cursor> createItemLoader(String townId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.TownsEntry.buildTownUri(townId),
                null,
                null,
                new String[]{String.valueOf(townId)},
                null
        );
    }
}
