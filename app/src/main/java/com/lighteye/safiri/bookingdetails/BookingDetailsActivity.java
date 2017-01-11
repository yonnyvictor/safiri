package com.lighteye.safiri.bookingdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lighteye.safiri.BaseActivity;
import com.lighteye.safiri.R;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsLoader;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsRepository;
import com.lighteye.safiri.data.source.entities.bookings.BookingsLoader;
import com.lighteye.safiri.data.source.entities.bookings.BookingsRepository;
import com.lighteye.safiri.data.source.entities.routes.RoutesLoader;
import com.lighteye.safiri.data.source.entities.routes.RoutesRepository;
import com.lighteye.safiri.data.source.local.BookingDetailsLocalDataSource;
import com.lighteye.safiri.data.source.local.BookingsLocalDataSource;
import com.lighteye.safiri.data.source.local.RoutesLocalDataSource;
import com.lighteye.safiri.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookingDetailsActivity extends BaseActivity {

    public static final String EXTRA_ROUTE_ID = "ROUTE_ID";
    public static final String EXTRA_TRAVEL_DATE = "TRAVEL_DATE";
    public static final String EXTRA_BOOKING_ID = "BOOKING_ID";
    public static final String EXTRA_EDITABLE = "EDITABLE";

    public static final int REQUEST_ADD_BOOKING_DETAIL = 1;

    private boolean mNewBooking = true;
    private boolean mEditBooking = false;
    private boolean mPastBooking = false;

    private BookingDetailsPresenter mBookingDetailsPresenter;
    private BookingDetailsView mBookingDetailsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested route id
        String routeId = getIntent().getStringExtra(EXTRA_ROUTE_ID);
        // Get the requested travel date
        String travelDate = getIntent().getStringExtra(EXTRA_TRAVEL_DATE);
        // Get the requested booking id
        String bookingId = getIntent().getStringExtra(EXTRA_BOOKING_ID);

        boolean editable = getIntent().getBooleanExtra(EXTRA_EDITABLE, true);

        if (bookingId != null){
            mNewBooking = false;
            Calendar calendar = Calendar.getInstance();
            long todayMilliseconds = calendar.getTimeInMillis();
            //Log.e("BookingDetailsActivity", "Today Milliseconds: " + todayMilliseconds);
            long travelDateMilliseconds = Utils.convertDateString(travelDate);
            //Log.e("BookingDetailsActivity", "Travel Date Milliseconds: " + travelDateMilliseconds);
            if(todayMilliseconds < travelDateMilliseconds && editable){
                mEditBooking = true;
            }else{
                mPastBooking = true;
            }
        }


        mBookingDetailsView = (BookingDetailsView) findViewById(R.id.booking_details_view);
        checkNotNull(mBookingDetailsView, "mBookingDetailsView not found in layout");

        mBookingDetailsPresenter = new BookingDetailsPresenter(
                RoutesRepository.getInstance(RoutesLocalDataSource.getInstance(getContentResolver())),
                BookingsRepository.getInstance(BookingsLocalDataSource.getInstance(getContentResolver())),
                BookingDetailsRepository.getInstance(BookingDetailsLocalDataSource.getInstance(getContentResolver())),
                mBookingDetailsView,
                new RoutesLoader(this),
                new BookingsLoader(this),
                new BookingDetailsLoader(this),
                getSupportLoaderManager(),
                this,
                mUid,
                routeId,
                travelDate,
                bookingId,
                mEditBooking
        );

        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            ArrayList<BookingDetail> bookingDetailArrayList = savedInstanceState.getParcelableArrayList("bookingDetailsArrayList");
            mBookingDetailsPresenter.setBookingDetailsList(bookingDetailArrayList);
        }

        // Set up floating action button
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_add_booking_detail);
        checkNotNull(fab, "fab not found in layout");

        if(mNewBooking || mEditBooking){
            fab.setImageResource(R.drawable.ic_add);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBookingDetailsPresenter.showBookingDetailUi();
                }
            });
        } else{
            fab.setVisibility(FloatingActionButton.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //outState.putSerializable(CURRENT_FILTERING_KEY, mTasksPresenter.getFiltering());
        outState.putParcelableArrayList("bookingDetailsArrayList", mBookingDetailsPresenter.getBookingDetailsList());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_BOOKING_DETAIL) {
            // If the booking detail was added successfully, go back to the booking detail activity.
            if (resultCode == Activity.RESULT_OK) {
                BookingDetail bookingDetail = data.getExtras().getParcelable("bookingDetail");
                mBookingDetailsPresenter.addBookingDetail(bookingDetail);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mNewBooking){
            getMenuInflater().inflate(R.menu.new_booking_details_actions, menu);
        }
        if(mEditBooking){
            getMenuInflater().inflate(R.menu.edit_booking_details_actions, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_booking:
                mBookingDetailsPresenter.saveBooking();
                return true;
            case R.id.menu_cancel_booking:
                mBookingDetailsPresenter.cancelBooking();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBookingDetailsPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
