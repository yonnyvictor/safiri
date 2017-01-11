package com.lighteye.safiri.data.source.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;
import com.lighteye.safiri.data.source.remote.BookingsRemoteDataSource;
import com.lighteye.safiri.data.source.remote.response.BookingsResponse;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yonny on 7/16/16.
 */
public class SafiriSyncAdapter extends AbstractThreadedSyncAdapter{

    public final String LOG_TAG = SafiriSyncAdapter.class.getSimpleName();

    public static final String SYNC_TYPE = "sync_type";
    public static final String BOOKING_KEY = "bookingId";
    public static final String BOOKING_DETAIL_KEY = "bookingDetailId";
    public static final String BOOKING_NODE_KEY = "bookingNode";
    public static final String ROUTE_NODE_KEY = "routeNode";
    public static final String TRAVEL_DATE_KEY = "travelDate";
    public static final String BOOKING_UPSTREAM_STATUS = "Recieved";

    private static final int SYNC_ALL = 1;
    private static final int SYNC_TOWNS = 2;
    private static final int SYNC_ORGANIZATIONS = 3;
    private static final int SYNC_ORGANIZATION_BRANCHES = 4;
    private static final int SYNC_LOCAL_BOOKINGS = 5;
    private static final int SYNC_REMOTE_BOOKINGS = 6;
    private static final int SYNC_DELETED_BOOKING = 7;
    private static final int SYNC_DELETED_BOOKINGS = 8;

    private SyncOperations mSyncOperations;

    private BookingsRemoteDataSource mBookingsRemoteDataSource;

    public SafiriSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mSyncOperations = new SyncOperations(context);
        mBookingsRemoteDataSource = new BookingsRemoteDataSource();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        //Log.d(LOG_TAG, "onPerformSync Called.");
        int bookingId;
        String routeKey, travelDate, bookingKey;

        int match = extras.getInt(SYNC_TYPE);

        switch (match) {
            case SYNC_ALL:
                mSyncOperations.syncAll();
                break;
            case SYNC_TOWNS:
                mSyncOperations.syncTowns(null);
                break;
            case SYNC_ORGANIZATIONS:
                mSyncOperations.syncOrganizations(null);
                break;
            case SYNC_ORGANIZATION_BRANCHES:
                mSyncOperations.syncOrganizationBranches(null);
                break;
            case SYNC_LOCAL_BOOKINGS:
                bookingId = extras.getInt(BOOKING_KEY);
                performRemoteBookingSync(bookingId);
                break;
            case SYNC_DELETED_BOOKING:
                routeKey = extras.getString(ROUTE_NODE_KEY);
                travelDate = extras.getString(TRAVEL_DATE_KEY);
                bookingKey = extras.getString(BOOKING_NODE_KEY);
                performDeleteBookingSync(routeKey, travelDate, bookingKey);
                break;
            case SYNC_DELETED_BOOKINGS:
                routeKey = extras.getString(ROUTE_NODE_KEY);
                travelDate = extras.getString(TRAVEL_DATE_KEY);
                bookingId = extras.getInt(BOOKING_KEY);
                performDeleteBookingsSync(routeKey, travelDate, bookingId);
                break;
        }
    }

    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putInt(SYNC_TYPE, SYNC_ALL);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    public static void syncRemoteBookings(Context context, int bookingId){
        Bundle bundle = new Bundle();
        bundle.putInt(SYNC_TYPE, SYNC_LOCAL_BOOKINGS);
        bundle.putInt(BOOKING_KEY, bookingId);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    public static void syncDeletedBookings(Context context, String routeKey, String travelDate, int bookingId){
        Bundle bundle = new Bundle();
        bundle.putInt(SYNC_TYPE, SYNC_DELETED_BOOKINGS);
        bundle.putInt(BOOKING_KEY, bookingId);
        bundle.putString(ROUTE_NODE_KEY, routeKey);
        bundle.putString(TRAVEL_DATE_KEY, travelDate);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    public static void syncDeletedBooking(Context context, String routeKey, String travelDate, String bookingKey){
        Bundle bundle = new Bundle();
        bundle.putInt(SYNC_TYPE, SYNC_DELETED_BOOKING);
        bundle.putString(BOOKING_NODE_KEY, bookingKey);
        bundle.putString(ROUTE_NODE_KEY, routeKey);
        bundle.putString(TRAVEL_DATE_KEY, travelDate);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }

            onAccountCreated(context);

        }
        return newAccount;
    }

    private static void onAccountCreated(Context context) {
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }

    private void performDeleteBookingSync(String routeKey, String travelDate, String bookingKey){
        Observable<Void> observable = mBookingsRemoteDataSource.deleteBooking(routeKey, travelDate, bookingKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }

    private void performProcessBookingSync(String bookingKey){
        Observable<Void> observable = mBookingsRemoteDataSource.processBooking(bookingKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }

    private void performDeleteBookingsSync(String routeKey, String travelDate, int bookingId){

        //retrieve booking details from local db based on booking id
        Cursor bookingDetailsCursor = retrieveBookingDetails(bookingId);

        //loop through bookingDetailsCursor
        try {
            while (bookingDetailsCursor.moveToNext()) {
                final BookingDetail bookingDetail = BookingDetail.from(bookingDetailsCursor);

                performDeleteBookingSync(routeKey, travelDate, bookingDetail.getNodeKey());
            }
        } finally {
            bookingDetailsCursor.close();
        }
    }

    private void performRemoteBookingSync(int bookingId){
        Context context = getContext();

        //retrieve booking from local db based on booking id
        Cursor bookingCursor = retrieveBooking(bookingId);
        Log.e("SafiriSyncAdapter", "BookingCursor: " + String.valueOf(bookingCursor.getCount()));

        if(bookingCursor.moveToFirst()){
            Booking booking = Booking.from(bookingCursor);
            bookingCursor.close();
            Log.e("SafiriSyncAdapter", booking.toString());
            //retrieve booking details from local db based on booking id
            Cursor bookingDetailsCursor = retrieveBookingDetails(bookingId);
            Log.e("SafiriSyncAdapter", "BookingDetailsCursor: " + String.valueOf(bookingDetailsCursor.getCount()));

            //loop through bookingDetailsCursor
            try {
                while (bookingDetailsCursor.moveToNext()) {
                    final BookingDetail bookingDetail = BookingDetail.from(bookingDetailsCursor);
                    Log.e("SafiriSyncAdapter", bookingDetail.toString());

                    Observable<BookingsResponse> observable = mBookingsRemoteDataSource.saveBooking(booking, bookingDetail);
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<BookingsResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(LOG_TAG, e.getMessage());
                        }

                        @Override
                        public void onNext(BookingsResponse bookingsResponse) {
                            Log.e(LOG_TAG, bookingsResponse.toString());
                            updateBookingDetailStatus(bookingDetail.getId());
                            performProcessBookingSync(bookingDetail.getNodeKey());
                        }
                    });
                }
            } finally {
                bookingDetailsCursor.close();
            }

        }
    }

    private int updateBookingDetailStatus(int bookingDetailId){
        ContentValues values = new ContentValues();
        values.put(SafiriPersistenceContract.BookingDetailsEntry.COLUMN_STATUS, BOOKING_UPSTREAM_STATUS);
        String selection = SafiriPersistenceContract.BookingDetailsEntry._ID + " = ?";
        int inserted = getContext().getContentResolver().update(
                SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailUri(bookingDetailId),
                values,
                selection,
                new String[]{String.valueOf(bookingDetailId)}
        );

        return inserted;
    }

    private Cursor retrieveBooking(int bookingId){
        return getContext().getContentResolver().query(
                SafiriPersistenceContract.BookingsEntry.buildBookingUri(bookingId),
                SafiriPersistenceContract.BookingsEntry.BOOKING_COLUMNS,
                SafiriPersistenceContract.BookingsEntry.TABLE_NAME + "." + SafiriPersistenceContract.BookingsEntry._ID + " = ?",
                new String[]{String.valueOf(bookingId)},
                null
        );
    }

    private Cursor retrieveBookingDetail(int bookingDetailId){
        return getContext().getContentResolver().query(
                SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailUri(bookingDetailId),
                SafiriPersistenceContract.BookingDetailsEntry.BOOKING_DETAILS_COLUMNS,
                SafiriPersistenceContract.BookingDetailsEntry.TABLE_NAME + "." +
                        SafiriPersistenceContract.BookingDetailsEntry._ID + " = ?",
                new String[]{String.valueOf(bookingDetailId)},
                null
        );
    }

    private Cursor retrieveBookingDetails(int bookingId){
        return getContext().getContentResolver().query(
                SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailsWithBookingUri(String.valueOf(bookingId)),
                SafiriPersistenceContract.BookingDetailsEntry.BOOKING_DETAILS_COLUMNS,
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_ID + " = ?",
                new String[]{String.valueOf(bookingId)},
                null
        );
    }


}
