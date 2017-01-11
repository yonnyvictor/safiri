package com.lighteye.safiri.routes;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.source.remote.BookingsRemoteDataSource;
import com.lighteye.safiri.utils.Utils;
import com.twotoasters.sectioncursoradapter.adapter.SectionCursorAdapter;
import com.twotoasters.sectioncursoradapter.adapter.viewholder.ViewHolder;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yonny on 7/26/16.
 */
public class RoutesAdapter extends SectionCursorAdapter<String, RoutesAdapter.SectionViewHolder, RoutesAdapter.ItemViewHolder> {

    private final RoutesView.RouteItemListener mItemListener;
    private String mDate;
    private BookingsRemoteDataSource mBookingsRemoteDataSource;


    public static class SectionViewHolder extends ViewHolder{
        private final TextView mSectionName;
        public SectionViewHolder(View rootView) {
            super(rootView);
            mSectionName = (TextView) rootView.findViewById(R.id.listview_header_text);
        }
    }

    public static class ItemViewHolder extends ViewHolder{
        //private final TextView titleTextView;
        private final TextView originTextView;
        private final TextView destinationTextView;
        private final TextView departureTextView;
        private final TextView seatTextView;
        private final GridLayout containerLayout;
        private final ProgressBar progressBar;
        private Observable<Integer> bookingsObservable;
        private int seats = 0;

        public ItemViewHolder(View rootView) {
            super(rootView);
            originTextView = (TextView)rootView.findViewById(R.id.route_origin);
            destinationTextView = (TextView)rootView.findViewById(R.id.route_destination);
            departureTextView = (TextView)rootView.findViewById(R.id.route_departure);
            seatTextView = (TextView)rootView.findViewById(R.id.route_seat);
            progressBar = (ProgressBar) rootView.findViewById(R.id.pbLoading);
            containerLayout = (GridLayout)rootView.findViewById(R.id.route_container);
        }

        public void showLoadingSeatsIndicator(){
            seatTextView.setVisibility(TextView.GONE);
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        public void showNumberOfSeats(){
            progressBar.setVisibility(ProgressBar.GONE);
            seatTextView.setText(String.valueOf(seats));
            seatTextView.setVisibility(TextView.VISIBLE);
        }
    }

    public RoutesAdapter(Context context, Cursor cursor, RoutesView.RouteItemListener itemListener) {
        super(context, cursor, 0, R.layout.listview_header, R.layout.card_route);
        mItemListener = itemListener;
        mBookingsRemoteDataSource = new BookingsRemoteDataSource();
    }

    @Override
    protected String getSectionFromCursor(Cursor cursor) {
        final Route route = Route.from(cursor);
        return route.getOrganizationName();
    }

    @Override
    protected SectionViewHolder createSectionViewHolder(View sectionView, String section) {
        return new SectionViewHolder(sectionView);
    }

    @Override
    protected void bindSectionViewHolder(int position, SectionViewHolder sectionViewHolder, ViewGroup parent, String section) {
        sectionViewHolder.mSectionName.setText(section);
    }

    @Override
    protected ItemViewHolder createItemViewHolder(Cursor cursor, View itemView) {
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void bindItemViewHolder(final ItemViewHolder itemViewHolder, Cursor cursor, ViewGroup parent) {
        final int position = cursor.getPosition();
        final Route route = Route.from(cursor);

        //itemViewHolder.titleTextView.setText(route.getName());
        itemViewHolder.originTextView.setText(route.getOriginName());
        itemViewHolder.destinationTextView.setText(route.getDestinationName());
        itemViewHolder.departureTextView.setText(Utils.millisOfDayString(route.getDepartureTime()));

        itemViewHolder.showLoadingSeatsIndicator();

        long dateInMilliseconds = Utils.convertDateString(mDate);

        itemViewHolder.bookingsObservable = mBookingsRemoteDataSource.getNumberOfBookings(route.getNodeKey(),
                String.valueOf(dateInMilliseconds));
        itemViewHolder.bookingsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                itemViewHolder.seats = route.getFleetTypeCapacity();
                itemViewHolder.showNumberOfSeats();
                Log.e("RoutesAdapter", "Error Retrieving seats: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                itemViewHolder.seats = (route.getFleetTypeCapacity() - integer);
                itemViewHolder.showNumberOfSeats();
                //Log.e("RoutesAdapter", "No. of Seats: " + integer.toString());
            }
        });

        itemViewHolder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onRouteClick(route);
            }
        });
    }

    @Override
    protected int getMaxIndexerLength() {
        return 1;
    }

    public void setDate(String date) {
        this.mDate = date;
    }
}
