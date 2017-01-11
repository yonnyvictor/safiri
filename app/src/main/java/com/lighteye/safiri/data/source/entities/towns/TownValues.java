package com.lighteye.safiri.data.source.entities.towns;

import android.content.ContentValues;

import com.lighteye.safiri.data.Town;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/16/16.
 */
public class TownValues {

    public static ContentValues form(Town town){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.TownsEntry._ID, town.getId());
        values.put(SafiriPersistenceContract.TownsEntry.COLUMN_NAME, town.getName());
        values.put(SafiriPersistenceContract.TownsEntry.COLUMN_NODE_KEY, town.getNodeKey());
        return values;
    }
}
