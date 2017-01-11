package com.lighteye.safiri.data.source.entities.routes;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.source.BaseLoader;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;
import com.lighteye.safiri.utils.Utils;

import org.joda.time.LocalTime;

import java.util.Calendar;

/**
 * Created by yonny on 7/20/16.
 */
public class RoutesLoader extends BaseLoader{

    public RoutesLoader(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public Loader<Cursor> createItemsLoader() {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.RoutesEntry.CONTENT_URI,
                SafiriPersistenceContract.RoutesEntry.ROUTES_COLUMNS,
                null,
                null,
                null
        );
    }

    @Override
    public Loader<Cursor> createItemLoader(String itemId) {
        return new CursorLoader(mContext,
                SafiriPersistenceContract.RoutesEntry.buildRouteUri(Long.parseLong(itemId)),
                SafiriPersistenceContract.RoutesEntry.ROUTES_COLUMNS,
                SafiriPersistenceContract.RoutesEntry.TABLE_NAME + "." +
                        SafiriPersistenceContract.RoutesEntry._ID + " = ?",
                new String[]{String.valueOf(itemId)},
                null
        );
    }

    public Loader<Cursor> dayItemsLoader(String day) {
        String selectionDay = Utils.routeSelectionDay(day);
        String selectionDayValue = "1";
        LocalTime localTime = new LocalTime();
        int now = localTime.getMillisOfDay();

        String selection = SafiriPersistenceContract.RoutesEntry.COLUMN_DEPARTURE + " > ? AND " + selectionDay;
        return new CursorLoader(mContext,
                SafiriPersistenceContract.RoutesEntry.buildTodayRoutesUri(day),
                SafiriPersistenceContract.RoutesEntry.ROUTES_COLUMNS,
                selection,
                new String[]{String.valueOf(now), selectionDayValue},
                null
        );
    }

    public Loader<Cursor> searchItemsLoader(String origin, String destination, String day) {
        String selectionDay = "";
        String selectionDayValue = "1";
        int dayOfWeek = Utils.getDayOfWeek(day);
        switch (dayOfWeek){
            case Calendar.SUNDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_SUN + " = ?";
                break;
            case Calendar.MONDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_MON + " = ?";
                break;
            case Calendar.TUESDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_TUE + " = ?";
                break;
            case Calendar.WEDNESDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_WED + " = ?";
                break;
            case Calendar.THURSDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_THU + " = ?";
                break;
            case Calendar.FRIDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_FRI + " = ?";
                break;
            case Calendar.SATURDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_SAT + " = ?";
                break;
        }
        String selection = SafiriPersistenceContract.RoutesEntry.COLUMN_ORIGIN + " = ? AND " +
                SafiriPersistenceContract.RoutesEntry.COLUMN_DESTINATION + " = ? AND " + selectionDay;

        return new CursorLoader(mContext,
                SafiriPersistenceContract.RoutesEntry.buildSearchRoutesUri(origin, destination, day),
                SafiriPersistenceContract.RoutesEntry.ROUTES_COLUMNS,
                selection,
                new String[]{
                        String.valueOf(origin),
                        String.valueOf(destination),
                        selectionDayValue
                },
                null
        );
    }
}
