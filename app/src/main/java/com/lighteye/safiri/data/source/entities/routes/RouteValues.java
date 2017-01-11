package com.lighteye.safiri.data.source.entities.routes;

import android.content.ContentValues;

import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class RouteValues {
    public static ContentValues form(Route item){
        long created = item.getTimestampCreated().getTimestamp();
        long modified = item.getTimestampCreated().getTimestamp();
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.RoutesEntry._ID, item.getId());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_NAME, item.getName());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_NODE_KEY, item.getNodeKey());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_ORGANIZATION_ID, item.getOrganizationId());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_ORGANIZATION_KEY, item.getOrganizationKey());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_FLEET_TYPE_ID, item.getFleetTypeId());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_FLEET_TYPE_KEY, item.getFleetTypeKey());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_ORIGIN, item.getOrigin());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_ORIGIN_KEY, item.getOriginKey());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_DESTINATION, item.getDestination());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_DESTINATION_KEY, item.getDestinationKey());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_DEPARTURE, item.getDepartureTime());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_ARRIVAL, item.getArrivalTime());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_MON, item.isMon());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_TUE, item.isTue());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_WED, item.isWed());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_THU, item.isThu());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_FRI, item.isFri());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_SAT, item.isSat());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_SUN, item.isSun());
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_CREATED, created);
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_MODIFIED, modified);
        values.put(SafiriPersistenceContract.RoutesEntry.COLUMN_STATUS, item.getStatus());

        return values;
    }
}
