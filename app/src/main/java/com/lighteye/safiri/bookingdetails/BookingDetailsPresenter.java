package com.lighteye.safiri.bookingdetails;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsDataSource;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsLoader;
import com.lighteye.safiri.data.source.entities.bookingdetails.BookingDetailsRepository;
import com.lighteye.safiri.data.source.entities.bookings.BookingsDataSource;
import com.lighteye.safiri.data.source.entities.bookings.BookingsLoader;
import com.lighteye.safiri.data.source.entities.bookings.BookingsRepository;
import com.lighteye.safiri.data.source.entities.routes.RoutesDataSource;
import com.lighteye.safiri.data.source.entities.routes.RoutesLoader;
import com.lighteye.safiri.data.source.entities.routes.RoutesRepository;
import com.lighteye.safiri.data.source.sync.SafiriSyncAdapter;
import com.lighteye.safiri.utils.Constants;
import com.lighteye.safiri.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yonny on 7/15/16.
 */
public class BookingDetailsPresenter implements BookingDetailsContract.Presenter,
        LoaderManager.LoaderCallbacks<Cursor>,
        RoutesDataSource.GetItemCallback,
        BookingsDataSource.GetItemCallback,
        BookingDetailsRepository.LoadDataCallback,
        BookingDetailsDataSource.GetItemsCallback{

    @NonNull
    private final RoutesRepository mRoutesRepository;

    @NonNull
    private final BookingsRepository mBookingsRepository;

    @NonNull
    private final BookingDetailsRepository mBookingDetailsRepository;

    private BookingDetailsContract.View mBookingDetailsView;
    private RoutesLoader mRoutesLoader;
    private BookingsLoader mBookingsLoader;
    private BookingDetailsLoader mBookingDetailsLoader;

    private LoaderManager mLoaderManager;

    private ArrayList<BookingDetail> mBookingDetailsList;

    private String mUid;
    private String mRouteId;
    private String mBookingId;
    private String mOrganizationId;
    private String mFleetTypeId;
    private String mRouteKey;
    private String mTravelDate;
    private Route mRoute;
    private Booking mBooking;
    private boolean mEditable;

    private Context mContext;

    public BookingDetailsPresenter(@NonNull RoutesRepository routesRepository,
                                   @NonNull BookingsRepository bookingsRepository,
                                   @NonNull BookingDetailsRepository bookingDetailsRepository,
                                   BookingDetailsContract.View bookingDetailsView,
                                   RoutesLoader routesLoader,
                                   BookingsLoader bookingsLoader,
                                   BookingDetailsLoader bookingDetailsLoader,
                                   LoaderManager loaderManager,
                                   Context context,
                                   String uid,
                                   String routeId,
                                   String travelDate,
                                   String bookingId,
                                   boolean editable) {
        this.mRoutesRepository = routesRepository;
        this.mBookingsRepository = bookingsRepository;
        this.mBookingDetailsRepository = bookingDetailsRepository;
        this.mBookingDetailsView = bookingDetailsView;
        this.mRoutesLoader = routesLoader;
        this.mBookingsLoader = bookingsLoader;
        this.mBookingDetailsLoader = bookingDetailsLoader;
        this.mLoaderManager = loaderManager;
        this.mContext = context;
        this.mUid = uid;
        this.mRouteId = routeId;
        this.mTravelDate = travelDate;
        this.mBookingId = bookingId;
        this.mEditable = editable;

        this.mBookingDetailsView.setPresenter(this);
        mBookingDetailsList = new ArrayList<>();
    }


    @Override
    public void start() {
        loadRoute();
        if(!isNewBooking()) {
            loadBooking();
            loadBookingDetails();
        }else{
            mBookingDetailsView.showBookingDetailsList();
        }
    }

    @Override
    public void onBookingDetailsLoaded(List<BookingDetail> items) {
        if(mLoaderManager.getLoader(Constants.BOOKING_DETAILS_LOADER) == null)
            mLoaderManager.initLoader(Constants.BOOKING_DETAILS_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.BOOKING_DETAILS_LOADER, null, this);
    }

    @Override
    public void onBookingDetailsDataLoaded(Cursor data) {
        mBookingDetailsView.showBookingDetails(data, mEditable);
    }

    @Override
    public void onBookingDetailsDataEmpty() {

    }

    @Override
    public void onBookingDetailsDataNotAvailable() {

    }

    @Override
    public void onBookingDetailsDataReset() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constants.ROUTE_LOADER:
                return mRoutesLoader.createItemLoader(mRouteId);
            case Constants.BOOKING_LOADER:
                return mBookingsLoader.createItemLoader(mBookingId);
            case Constants.BOOKING_DETAILS_LOADER:
                return mBookingDetailsLoader.createItemsLoader(mBookingId);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if(data.moveToLast()){
                switch (loader.getId()){
                    case Constants.ROUTE_LOADER:
                        showRoute(data);
                        break;
                    case Constants.BOOKING_LOADER:
                        showBooking(data);
                        break;
                    case Constants.BOOKING_DETAILS_LOADER:
                        onBookingDetailsDataLoaded(data);
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
    public void saveBooking() {
        if(isNewBooking()){
            mBookingDetailsView.validateNewBooking();
        }
    }

    @Override
    public void createBooking(ArrayList<BookingDetail> bookingDetails) {
        Booking booking = createNewBooking();
        int bookingId = mBookingsRepository.saveItem(booking);
        booking.setId(bookingId);
        for (BookingDetail bookingDetail : bookingDetails) {
            bookingDetail.setBookingId(bookingId);
            mBookingDetailsRepository.saveItem(bookingDetail);
        }
        SafiriSyncAdapter.syncRemoteBookings(mContext, bookingId);
        mBookingDetailsView.showBookingsList();
    }

    @Override
    public void populateBooking() {

    }

    @Override
    public void cancelBooking() {
        mBookingDetailsRepository.cancelBookingItems(mBooking.getId());
        mBookingsRepository.cancelItem(mBooking);
        SafiriSyncAdapter.syncDeletedBookings(mContext, mBooking.getRouteKey(),
                String.valueOf(mBooking.getTravelDate()), mBooking.getId());
        mBookingDetailsView.showBookingsList();
    }

    @Override
    public void onRouteLoaded(Route item) {
        mLoaderManager.initLoader(Constants.ROUTE_LOADER, null, this);
    }

    @Override
    public void onBookingLoaded(Booking item) {
        mLoaderManager.initLoader(Constants.BOOKING_LOADER, null, this);
    }

    @Override
    public void onRouteNotAvailable() {

    }

    @Override
    public void onBookingNotAvailable() {

    }

    @Override
    public void onBookingDetailsNotAvailable() {

    }

    @Override
    public void showBookingDetailUi() {
        mBookingDetailsView.showBookingDetailForm(mOrganizationId, mFleetTypeId, mRouteKey, mTravelDate);
    }

    @Override
    public void addBookingDetail(BookingDetail bookingDetail) {
        if(isNewBooking()){
            mBookingDetailsList.add(bookingDetail);
        }else{
            //save BookingDetail to db
        }

    }

    @Override
    public void removeBookingDetail(BookingDetail bookingDetail) {
        if(isNewBooking()){
            //remove BookingDetail from ArrayList
            mBookingDetailsList.remove(bookingDetail);
            mBookingDetailsView.removeBookingDetail(bookingDetail);
        }else{
            //delete BookingDetail from db
            int items = mBookingDetailsView.countAdapterItems();
            if(items > 1){
                mBookingDetailsRepository.cancelItem(bookingDetail);
                SafiriSyncAdapter.syncDeletedBooking(mContext, mBooking.getRouteKey(),
                        String.valueOf(mBooking.getTravelDate()), bookingDetail.getNodeKey());
            }else if(items == 1){
               cancelBooking();
            }

        }
    }

    @Override
    public void setBookingDetailsList(ArrayList<BookingDetail> bookingDetailsList) {
        mBookingDetailsList = bookingDetailsList;
    }

    @Override
    public ArrayList<BookingDetail> getBookingDetailsList() {
        return mBookingDetailsList;
    }

    private boolean isNewBooking() {
        return mBookingId == null;
    }

    private void loadRoute(){
        mRoutesRepository.getItem(mRouteId, this);
    }

    private void loadBooking(){
        mBookingsRepository.getItem(mBookingId, this);
    }

    private void loadBookingDetails(){
        mBookingDetailsRepository.getBookingItems(mBookingId, this);
    }


    private void showRoute(Cursor data){
        mRoute = Route.from(data);
        mOrganizationId = String.valueOf(mRoute.getOrganizationId());
        mFleetTypeId = String.valueOf(mRoute.getFleetTypeId());
        mRouteKey = mRoute.getNodeKey();

        //show route details on view
        mBookingDetailsView.showRoute(mRoute, mTravelDate);

    }

    private void showBooking(Cursor data){
        mBooking = Booking.from(data);
        if(mBooking.getStatus() == "Cancelled"){
            mEditable = false;
        }
        mBookingDetailsView.showBooking(mBooking);
    }

    private Booking createNewBooking(){
        int id = 0;
        int routeId = mRoute.getId();
        String routeKey = mRoute.getNodeKey();
        String userId = mUid;
        long travelDate = Utils.convertDateString(mTravelDate);
        String status = "Confirmed";

        Booking booking = new Booking(
                id,
                routeId,
                routeKey,
                userId,
                travelDate,
                status
        );

       return booking;
    }


}
