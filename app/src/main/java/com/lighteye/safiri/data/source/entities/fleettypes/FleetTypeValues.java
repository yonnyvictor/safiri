package com.lighteye.safiri.data.source.entities.fleettypes;

import android.content.ContentValues;

import com.lighteye.safiri.data.FleetType;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class FleetTypeValues {
    public static ContentValues form(FleetType item){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.FleetTypesEntry._ID, item.getId());
        values.put(SafiriPersistenceContract.FleetTypesEntry.COLUMN_NAME, item.getName());
        values.put(SafiriPersistenceContract.FleetTypesEntry.COLUMN_CAPACITY, item.getCapacity());
        values.put(SafiriPersistenceContract.FleetTypesEntry.COLUMN_NODE_KEY, item.getNodeKey());
        return values;
    }
}
