package com.lighteye.safiri.addeditbookingdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsLoader;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesLoader;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesRepository;
import com.lighteye.safiri.data.source.local.SeatChargesLocalDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddEditBookingDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ORGANIZATION_ID = "ORGANIZATION_ID";
    public static final String EXTRA_FLEET_TYPE_ID = "FLEET_TYPE_ID";
    public static final String EXTRA_ROUTE_KEY = "ROUTE_KEY";
    public static final String EXTRA_TRAVEL_DATE = "TRAVEL_DATE";
    public static final String ARGUMENT_EDIT_BOOKING_DETAIL_ID = "EDIT_BOOKING_DETAIL_ID";

    public static final int REQUEST_ADD_BOOKING_DETAIL = 1;
    public static final int REQUEST_EDIT_BOOKING_DETAIL = 2;

    public static final int REQUEST_SELECT_SEAT = 1;

    private AddEditBookingDetailPresenter mAddEditBookingDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_booking_detail);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        final AddEditBookingDetailView addEditSeatView =
                (AddEditBookingDetailView) findViewById(R.id.add_edit_seat_view);
        checkNotNull(addEditSeatView, "addEditSeatView not found in layout");

        String bookingDetailId = null;
        String organizationId = getIntent().getStringExtra(EXTRA_ORGANIZATION_ID);
        String fleetTypeId = getIntent().getStringExtra(EXTRA_FLEET_TYPE_ID);
        String routeKey = getIntent().getStringExtra(EXTRA_ROUTE_KEY);
        String travelDate = getIntent().getStringExtra(EXTRA_TRAVEL_DATE);

        if (getIntent().hasExtra(ARGUMENT_EDIT_BOOKING_DETAIL_ID)) {
            bookingDetailId = getIntent().getStringExtra(ARGUMENT_EDIT_BOOKING_DETAIL_ID);
            ab.setTitle(R.string.edit_booking_detail);
        } else {
            ab.setTitle(R.string.add_booking_detail);
        }

        mAddEditBookingDetailPresenter = new AddEditBookingDetailPresenter(
                SeatChargesRepository.getInstance(SeatChargesLocalDataSource.getInstance(getContentResolver())),
                new BookingDetailsLoader(this),
                new SeatChargesLoader(this),
                getSupportLoaderManager(),
                addEditSeatView,
                organizationId,
                fleetTypeId,
                routeKey,
                travelDate,
                bookingDetailId
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_SEAT) {
            // If the booking detail was added successfully, go back to the booking detail activity.
            if (resultCode == Activity.RESULT_OK) {
                Seat seat = data.getExtras().getParcelable("selectedSeat");
                mAddEditBookingDetailPresenter.addSelectedSeat(seat);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAddEditBookingDetailPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
