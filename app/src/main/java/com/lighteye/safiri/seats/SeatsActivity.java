package com.lighteye.safiri.seats;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.source.entities.seats.SeatsLoader;
import com.lighteye.safiri.data.source.entities.seats.SeatsRepository;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationLoader;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationRepository;
import com.lighteye.safiri.data.source.local.SeatsConfigurationLocalDataSource;
import com.lighteye.safiri.data.source.local.SeatsLocalDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class SeatsActivity extends AppCompatActivity {

    public static final String EXTRA_ORGANIZATION_ID = "ORGANIZATION_ID";
    public static final String EXTRA_FLEET_TYPE_ID = "FLEET_TYPE_ID";
    public static final String EXTRA_ROUTE_KEY = "ROUTE_KEY";
    public static final String EXTRA_TRAVEL_DATE = "TRAVEL_DATE";
    public static final String ARGUMENT_CHANGE_SEAT_ID = "CHANGE_SEAT_ID";

    public static final int REQUEST_SELECT_SEAT = 1;


    private SeatsPresenter mSeatsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        final SeatsView mSeatsView = (SeatsView) findViewById(R.id.seats_view);
        checkNotNull(mSeatsView, "mSeatsView not found in layout");

        String seatId = null;
        String organizationId = getIntent().getStringExtra(EXTRA_ORGANIZATION_ID);
        String fleetTypeId = getIntent().getStringExtra(EXTRA_FLEET_TYPE_ID);
        String routeKey = getIntent().getStringExtra(EXTRA_ROUTE_KEY);
        String travelDate = getIntent().getStringExtra(EXTRA_TRAVEL_DATE);

        if (getIntent().hasExtra(ARGUMENT_CHANGE_SEAT_ID)) {
            seatId = getIntent().getStringExtra(ARGUMENT_CHANGE_SEAT_ID);
            ab.setTitle(R.string.change_seat);
        } else {
            ab.setTitle(R.string.select_seat);
        }

        mSeatsPresenter = new SeatsPresenter(
                SeatsRepository.getInstance(SeatsLocalDataSource.getInstance(getContentResolver())),
                SeatsConfigurationRepository.getInstance(SeatsConfigurationLocalDataSource.getInstance(getContentResolver())),
                mSeatsView,
                new SeatsLoader(this),
                new SeatsConfigurationLoader(this),
                getSupportLoaderManager(),
                organizationId,
                fleetTypeId,
                routeKey,
                travelDate
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        mSeatsPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
