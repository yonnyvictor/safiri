package com.lighteye.safiri.bookings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.ListView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.bookingdetails.BookingDetailsActivity;
import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.utils.ScrollChildSwipeRefreshLayout;
import com.lighteye.safiri.utils.Utils;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingsView extends ScrollChildSwipeRefreshLayout implements BookingsContract.View{

    public interface BookingItemListener {
        void onBookingClick(Booking booking);
    }

    private BookingsContract.Presenter mPresenter;

    private boolean mActive;

    private ListView mBookingsListView;

    private BookingsAdapter mBookingsAdapter;

    private BookingItemListener mBookingItemListener;


    public BookingsView(Context context) {
        super(context);
        initializeScreen();
    }

    public BookingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        final SwipeRefreshLayout srl = this;
        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showBookings(Cursor routes) {
        mBookingsAdapter.swapCursor(routes);
    }

    @Override
    public void showLoadingBookingsError() {

    }

    @Override
    public void showBookingDetailsUi(int bookingId, int routeId, String date, boolean editable) {
        Intent intent = new Intent(getContext(), BookingDetailsActivity.class);
        intent.putExtra(BookingDetailsActivity.EXTRA_BOOKING_ID, String.valueOf(bookingId));
        intent.putExtra(BookingDetailsActivity.EXTRA_ROUTE_ID, String.valueOf(routeId));
        intent.putExtra(BookingDetailsActivity.EXTRA_TRAVEL_DATE, date);
        intent.putExtra(BookingDetailsActivity.EXTRA_EDITABLE, editable);
        startActivity(intent);
    }

    @Override
    public void showNoBookings() {

    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(BookingsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
    }

    private void startActivity(Intent intent) {
        Activity activity = getActivity(this);
        activity.startActivity(intent, Utils.activityTransition(getContext()));
    }

    private void initializeScreen() {
        inflate(getContext(), R.layout.bookings_view_content, this);

        mBookingItemListener = new BookingItemListener() {
            @Override
            public void onBookingClick(Booking booking) {
                mPresenter.openBookingDetailUi(booking);
            }
        };

        mBookingsAdapter = new BookingsAdapter(getContext(), null, mBookingItemListener);

        mBookingsListView = (ListView)findViewById(R.id.bookings_list_view);
        mBookingsListView.setAdapter(mBookingsAdapter);

        // Set the emptyView to the ListView
        mBookingsListView.setEmptyView(findViewById(R.id.empty_booking_element));

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = this;
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(mBookingsListView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadBookings();
            }
        });

        mActive = true;
    }
}
