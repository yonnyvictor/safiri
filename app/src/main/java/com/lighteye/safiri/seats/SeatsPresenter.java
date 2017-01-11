package com.lighteye.safiri.seats;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.SeatsConfiguration;
import com.lighteye.safiri.data.source.entities.seats.SeatsDataSource;
import com.lighteye.safiri.data.source.entities.seats.SeatsLoader;
import com.lighteye.safiri.data.source.entities.seats.SeatsRepository;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationDataSource;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationLoader;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationRepository;
import com.lighteye.safiri.data.source.remote.BookingsRemoteDataSource;
import com.lighteye.safiri.data.source.remote.response.BookingsResponse;
import com.lighteye.safiri.utils.Constants;
import com.lighteye.safiri.utils.Utils;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by yonny on 7/27/16.
 */
public class SeatsPresenter implements SeatsContract.Presenter,
        LoaderManager.LoaderCallbacks<Cursor>,
        SeatsConfigurationDataSource.GetItemCallback,
        SeatsRepository.LoadDataCallback,
        SeatsDataSource.GetItemsCallback {

    @NonNull
    private final SeatsRepository mSeatsRepository;

    @NonNull
    private BookingsRemoteDataSource mBookingsRemoteDataSource;

    @NonNull
    private final SeatsConfigurationRepository mSeatsConfigurationRepository;

    private SeatsContract.View mSeatsView;

    private SeatsLoader mSeatsLoader;
    private SeatsConfigurationLoader mSeatsConfigurationLoader;

    private LoaderManager mLoaderManager;

    private String mOrganizationId;
    private String mFleetTypeId;

    private String mRouteKey;

    private String mTravelDate;

    private String mSeatsConfigurationId;

    private List<BookingsResponse> mCurrentBookings;

    private SeatsConfiguration mSeatsConfiguration;

    public SeatsPresenter(@NonNull SeatsRepository seatsRepository,
                          @NonNull SeatsConfigurationRepository seatsConfigurationRepository,
                          SeatsContract.View seatsView,
                          SeatsLoader seatsLoader,
                          SeatsConfigurationLoader seatsConfigurationLoader,
                          LoaderManager loaderManager,
                          String organizationId,
                          String fleetTypeId,
                          String routeKey,
                          String travelDate) {
        this.mSeatsRepository = seatsRepository;
        this.mSeatsConfigurationRepository = seatsConfigurationRepository;
        this.mSeatsView = seatsView;
        this.mSeatsLoader = seatsLoader;
        this.mSeatsConfigurationLoader = seatsConfigurationLoader;
        this.mLoaderManager = loaderManager;
        this.mOrganizationId = organizationId;
        this.mFleetTypeId = fleetTypeId;
        this.mRouteKey = routeKey;
        this.mTravelDate = travelDate;

        this.mSeatsView.setPresenter(this);
        mBookingsRemoteDataSource = new BookingsRemoteDataSource();
    }

    @Override
    public void loadSeatsConfiguration() {
        mSeatsConfigurationRepository.getItem(mOrganizationId, mFleetTypeId, this);
    }

    @Override
    public void loadSeats() {
        mSeatsRepository.getItems(mSeatsConfigurationId, this);
    }

    @Override
    public void start() {
        loadSeatsConfiguration();
    }

    @Override
    public void onSeatsConfigurationLoaded(SeatsConfiguration item) {
        mLoaderManager.initLoader(Constants.SEATS_CONFIGURATION_LOADER, null, this);
    }

    @Override
    public void onSeatsConfigurationNotAvailable() {

    }

    @Override
    public void onSeatsLoaded(List<Seat> items) {
        if(mLoaderManager.getLoader(Constants.SEATS_LOADER) == null)
            mLoaderManager.initLoader(Constants.SEATS_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.SEATS_LOADER, null, this);
    }

    @Override
    public void onSeatsNotAvailable() {

    }

    @Override
    public void onSeatsDataLoaded(Cursor data) {
        //mSeatsView.setLoadingIndicator(false);
        mSeatsView.showSeats(data, mSeatsConfiguration);
    }

    @Override
    public void onSeatsDataEmpty() {

    }

    @Override
    public void onSeatsDataNotAvailable() {

    }

    @Override
    public void onSeatsDataReset() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constants.SEATS_CONFIGURATION_LOADER:
                return mSeatsConfigurationLoader.createItemLoader(mOrganizationId, mFleetTypeId);
            case Constants.SEATS_LOADER:
                return mSeatsLoader.createItemsLoader(mSeatsConfigurationId);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if(data.moveToLast()){
                switch (loader.getId()){
                    case Constants.SEATS_CONFIGURATION_LOADER:
                        setSeatsConfiguration(data);
                        break;
                    case Constants.SEATS_LOADER:
                        onSeatsDataLoaded(data);
                        break;
                }
            }

        }else{

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void selectedSeat(Seat seat) {
        //pass the selected seat and close the activity
    }

    @Override
    public Observable<Map<String, BookingsResponse>> getSeatBooking(String seatKey) {
        long travelDate = Utils.convertDateString(mTravelDate);
        return mBookingsRemoteDataSource.getSeatBooking(mRouteKey, String.valueOf(travelDate), seatKey);
    }

    private void setSeatsConfiguration(Cursor data){
        mSeatsConfiguration = SeatsConfiguration.from(data);
        mSeatsConfigurationId = String.valueOf(mSeatsConfiguration.getId());
        loadSeats();
    }

}
