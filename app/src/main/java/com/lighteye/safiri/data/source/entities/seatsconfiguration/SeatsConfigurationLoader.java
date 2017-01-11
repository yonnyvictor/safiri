package com.lighteye.safiri.data.source.entities.seatsconfiguration;

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
public class SeatsConfigurationLoader extends BaseLoader {

    public SeatsConfigurationLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public Loader<Cursor> createItemsLoader() {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatsConfigurationEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Loader<Cursor> createItemLoader(String seatConfigurationId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatsConfigurationEntry.buildSeatsConfigurationUri(seatConfigurationId),
                null,
                null,
                new String[]{String.valueOf(seatConfigurationId)},
                null
        );
    }

    public Loader<Cursor> createItemLoader(String organizationId, String fleetTypeId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.SeatsConfigurationEntry.buildSeatsConfigurationWithOrganizationFleetTypeUri(organizationId, fleetTypeId),
                null,
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_ORGANIZATION_ID + " = ? AND " +
                        SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_FLEET_TYPE_ID + " = ?",
                new String[]{String.valueOf(organizationId), String.valueOf(fleetTypeId)},
                null
        );
    }
}
