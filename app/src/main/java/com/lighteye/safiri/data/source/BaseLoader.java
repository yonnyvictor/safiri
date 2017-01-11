package com.lighteye.safiri.data.source;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;

/**
 * Created by yonny on 7/16/16.
 */
public abstract class BaseLoader {

    @NonNull
    protected final Context mContext;

    public BaseLoader(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    public abstract Loader<Cursor> createItemsLoader();
    public abstract Loader<Cursor> createItemLoader(String itemId);


}
