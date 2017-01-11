package com.lighteye.safiri.data.source.entities.seattypes;

import android.content.ContentValues;

import com.lighteye.safiri.data.SeatType;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatTypeValues {
    public static ContentValues form(SeatType item){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.SeatTypesEntry._ID, item.getId());
        values.put(SafiriPersistenceContract.SeatTypesEntry.COLUMN_NAME, item.getName());
        values.put(SafiriPersistenceContract.SeatTypesEntry.COLUMN_NODE_KEY, item.getNodeKey());
        return values;
    }
}
