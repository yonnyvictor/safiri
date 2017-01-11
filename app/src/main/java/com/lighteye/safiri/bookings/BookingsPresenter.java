package com.lighteye.safiri.bookings;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.source.entities.bookings.BookingsDataSource;
import com.lighteye.safiri.data.source.entities.bookings.BookingsLoader;
import com.lighteye.safiri.data.source.entities.bookings.BookingsRepository;
import com.lighteye.safiri.utils.Constants;
import com.lighteye.safiri.utils.Utils;

import java.util.List;

/**
 * Created by yonny on 7/15/16.
 */
public class BookingsPresenter implements BookingsContract.Presenter,
        BookingsRepository.LoadDataCallback,
        BookingsDataSource.GetItemsCallback,
        LoaderManager.LoaderCallbacks<Cursor>   {

    private final BookingsContract.View mBookingsView;

    @NonNull
    private final BookingsRepository mBookingsRepository;

    @NonNull
    private final LoaderManager mLoaderManager;

    @NonNull
    private final BookingsLoader mLoaderProvider;


    public BookingsPresenter(BookingsContract.View bookingsView,
                             @NonNull BookingsRepository bookingsRepository,
                             @NonNull LoaderManager loaderManager,
                             @NonNull BookingsLoader loaderProvider) {
        this.mBookingsView = bookingsView;
        this.mBookingsRepository = bookingsRepository;
        this.mLoaderManager = loaderManager;
        this.mLoaderProvider = loaderProvider;

        this.mBookingsView.setPresenter(this);
    }

    @Override
    public void loadBookings() {
        mBookingsView.setLoadingIndicator(true);
        mBookingsRepository.getItems(this);
    }

    @Override
    public void openBookingDetailUi(Booking booking) {
        boolean editable = true;
        if(booking.getStatus().equals("Cancelled")){
            editable = false;
        }
        mBookingsView.showBookingDetailsUi(booking.getId(), booking.getRouteId(),
                (Utils.convertMilliSecondsToDateString(booking.getTravelDate())), editable);
    }

    @Override
    public void start() {
        loadBookings();
    }

    @Override
    public void onBookingsLoaded(List<Booking> items) {
        if(mLoaderManager.getLoader(Constants.BOOKINGS_LOADER) == null)
            mLoaderManager.initLoader(Constants.BOOKINGS_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.BOOKINGS_LOADER, null, this);
    }

    @Override
    public void onBookingsNotAvailable() {
        mBookingsView.setLoadingIndicator(false);
        mBookingsView.showLoadingBookingsError();
    }

    @Override
    public void onBookingsDataLoaded(Cursor data) {
        mBookingsView.setLoadingIndicator(false);
        mBookingsView.showBookings(data);
    }

    @Override
    public void onBookingsDataEmpty() {
        mBookingsView.setLoadingIndicator(false);
    }

    @Override
    public void onBookingsDataNotAvailable() {
        mBookingsView.setLoadingIndicator(false);
        mBookingsView.showLoadingBookingsError();
    }

    @Override
    public void onBookingsDataReset() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mLoaderProvider.createItemsLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            if(data.moveToLast())
                onBookingsDataLoaded(data);
            else
                onBookingsDataEmpty();
        }else{
            onBookingsDataNotAvailable();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
