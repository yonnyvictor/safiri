package com.lighteye.safiri.addeditbookingdetail;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.SeatCharge;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsDataSource;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsLoader;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesDataSource;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesLoader;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesRepository;
import com.lighteye.safiri.utils.Constants;
import com.lighteye.safiri.utils.Utils;

/**
 * Created by yonny on 7/29/16.
 */
public class AddEditBookingDetailPresenter implements AddEditBookingDetailContract.Presenter,
        LoaderManager.LoaderCallbacks<Cursor>, SeatChargesDataSource.GetItemCallback,
        BookingDetailsDataSource.GetItemCallback{

    @NonNull
    private final SeatChargesRepository mSeatChargesRepository;

    private AddEditBookingDetailContract.View mAddEditBookingDetailView;

    private BookingDetailsLoader mBookingDetailsLoader;
    private SeatChargesLoader mSeatChargesLoader;
    private LoaderManager mLoaderManager;

    private String mBookingDetailId;
    private String mOrganizationId;
    private String mFleetTypeId;
    private String mRouteKey;
    private String mTravelDate;

    private Seat mSelectedSeat;
    private String mTraveller;

    public AddEditBookingDetailPresenter(@NonNull SeatChargesRepository seatChargesRepository,
                                         BookingDetailsLoader bookingDetailsLoader,
                                         SeatChargesLoader seatChargesLoader,
                                         LoaderManager loaderManager,
                                         AddEditBookingDetailContract.View addEditBookingDetailView,
                                         String organizationId,
                                         String fleetTypeId,
                                         String routeKey,
                                         String travelDate,
                                         String bookingDetailId) {
        this.mSeatChargesRepository = seatChargesRepository;
        this.mBookingDetailsLoader = bookingDetailsLoader;
        this.mSeatChargesLoader = seatChargesLoader;
        this.mLoaderManager = loaderManager;
        this.mAddEditBookingDetailView = addEditBookingDetailView;
        this.mOrganizationId = organizationId;
        this.mFleetTypeId = fleetTypeId;
        this.mRouteKey = routeKey;
        this.mTravelDate = travelDate;
        this.mBookingDetailId = bookingDetailId;

        this.mAddEditBookingDetailView.setPresenter(this);
    }

    @Override
    public void loadSeatCharge(String traveller) {
        mTraveller = traveller;
        mSeatChargesRepository.getItem(String.valueOf(mSelectedSeat.getSeatTypeId()), this);
    }

    @Override
    public void showSeatsUi() {
        mAddEditBookingDetailView.openSeatsUi(mOrganizationId, mFleetTypeId, mRouteKey, mTravelDate);
    }

    @Override
    public void addSelectedSeat(Seat seat) {
        mSelectedSeat = seat;
        mAddEditBookingDetailView.showSelectedSeat(seat);
    }

    @Override
    public void start() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constants.BOOKING_DETAILS_LOADER:
                return mBookingDetailsLoader.createItemLoader(mBookingDetailId);
            case Constants.SEAT_CHARGE_LOADER:
                return mSeatChargesLoader.currentSeatChargeLoader(String.valueOf(mSelectedSeat.getSeatTypeId()));
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if(data.moveToLast()){
                switch (loader.getId()){
                    case Constants.BOOKING_DETAILS_LOADER:
                        //onDataLoaded(data);
                        break;
                    case Constants.SEAT_CHARGE_LOADER:
                        loadSeatCharge(data);
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
    public void onSeatChargeLoaded(SeatCharge item) {
        mLoaderManager.initLoader(Constants.SEAT_CHARGE_LOADER, null, this);
    }

    @Override
    public void onSeatChargeNotAvailable() {

    }

    @Override
    public void onBookingDetailLoaded(BookingDetail item) {

    }

    @Override
    public void onBookingDetailNotAvailable() {

    }

    @Override
    public void loadBookingDetail(BookingDetail bookingDetail) {
        mAddEditBookingDetailView.showBookingDetailUi(bookingDetail);
    }

    @Override
    public void saveBookingDetail(BookingDetail bookingDetail) {
        //persist Booking to the local db
    }

    private boolean isNewBookingDetail() {
        return mBookingDetailId == null;
    }

    private void loadSeatCharge(Cursor data){
        SeatCharge seatCharge = SeatCharge.from(data);
        createBookingDetail(seatCharge);
    }

    private void createBookingDetail(SeatCharge seatCharge){
        int id = 0;
        int bookingId = 0;
        int seatChargeId = seatCharge.getId();
        int seatId = mSelectedSeat.getId();
        String seatKey = mSelectedSeat.getNodeKey();
        String seatChargeKey = seatCharge.getNodeKey();
        String seatName = mSelectedSeat.getName();
        float charge = seatCharge.getCharge();
        String nodeKey = Utils.generateBookingPushId(mRouteKey);
        long now = System.currentTimeMillis();
        String status = "Pending";

        BookingDetail bookingDetail = new BookingDetail(id, bookingId, seatChargeId, seatId,
                seatKey, seatChargeKey, mTraveller, seatName, charge, nodeKey, status, now, now);

        if(isNewBookingDetail())
            loadBookingDetail(bookingDetail);
        else
            saveBookingDetail(bookingDetail);
    }
}
