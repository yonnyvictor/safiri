package com.lighteye.safiri.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.lighteye.safiri.R;
import com.lighteye.safiri.bookingdetails.BookingDetailsActivity;
import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;
import com.lighteye.safiri.data.source.remote.BookingsRemoteDataSource;
import com.lighteye.safiri.utils.Utils;

import org.joda.time.LocalTime;

import rx.Observable;
import rx.Observer;

/**
 * Created by yonny on 8/12/16.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private Cursor mCursor = null;
    private Intent mIntent;
    private String mToday;
    private BookingsRemoteDataSource mBookingsRemoteDataSource;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
        mToday = Utils.todayString();
        mBookingsRemoteDataSource = new BookingsRemoteDataSource();
    }

    private void initCursor(){
        if (mCursor != null) {
            mCursor.close();
        }
        final long identityToken = Binder.clearCallingIdentity();

        String selectionDay = Utils.routeSelectionDay(mToday);
        String selectionDayValue = "1";
        LocalTime localTime = new LocalTime();
        int now = localTime.getMillisOfDay();
        String selection = SafiriPersistenceContract.RoutesEntry.COLUMN_DEPARTURE + " > ? AND " + selectionDay;
        mCursor = mContext.getContentResolver().query(
                SafiriPersistenceContract.RoutesEntry.buildTodayRoutesUri(mToday),
                SafiriPersistenceContract.RoutesEntry.ROUTES_COLUMNS,
                selection,
                new String[]{String.valueOf(now), selectionDayValue},
                null
        );

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onCreate() {
        initCursor();
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
    }

    @Override
    public void onDataSetChanged() {
        /** Listen for data changes and initialize the cursor again **/
        initCursor();
    }

    @Override
    public void onDestroy() {
        mCursor.close();
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        /** Populate your widget's single list item **/
        final RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        mCursor.moveToPosition(i);

        final Route route = Route.from(mCursor);

        remoteViews.setTextViewText(R.id.widget_route_organization_name, route.getOrganizationName());
        remoteViews.setTextViewText(R.id.widget_route_origin, route.getOriginName());
        remoteViews.setTextViewText(R.id.widget_route_destination, route.getDestinationName());
        remoteViews.setTextViewText(R.id.widget_route_departure, Utils.millisOfDayString(route.getDepartureTime()));

        final Intent fillInIntent = new Intent();
        fillInIntent.putExtra(BookingDetailsActivity.EXTRA_ROUTE_ID, String.valueOf(route.getId()));
        fillInIntent.putExtra(BookingDetailsActivity.EXTRA_TRAVEL_DATE, mToday);

        remoteViews.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);

        //TODO: show available number of seats
        Observable<Integer> bookings = mBookingsRemoteDataSource.getNumberOfBookings(route.getNodeKey(), mToday);
        bookings.subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        remoteViews.setTextViewText(R.id.widget_route_seat, String.valueOf(route.getFleetTypeCapacity()));
                    }

                    @Override
                    public void onNext(Integer integer) {
                        int seats = route.getFleetTypeCapacity() - integer;
                        remoteViews.setTextViewText(R.id.widget_route_seat, String.valueOf(seats));
                    }
                });

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
