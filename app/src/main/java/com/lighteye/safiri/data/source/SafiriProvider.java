package com.lighteye.safiri.data.source;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lighteye.safiri.data.source.local.SafiriDbHelper;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.BookingDetailsEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.BookingsEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.FleetTypesEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.OrganizationsEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.OrganizationBranchesEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.RoutesEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.SeatChargesEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.SeatsEntry;
import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.TownsEntry;

/**
 * Created by yonny on 7/16/16.
 */
public class SafiriProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private SafiriDbHelper mOpenHelper;

    private static final int TOWNS = 100;
    private static final int TOWN_WITH_ID = 101;

    private static final int ORGANIZATIONS = 200;
    private static final int ORGANIZATION_WITH_ID = 201;

    private static final int ORGANIZATION_BRANCHES = 300;
    private static final int ORGANIZATION_BRANCH_WITH_ID = 301;

    private static final int ROUTES = 400;
    private static final int ROUTE_WITH_ID = 401;
    private static final int ROUTE_WITH_NODE_KEY = 402;
    private static final int ROUTES_WITH_ORIGIN_DESTINATION_DATE = 403;
    private static final int ROUTES_WITH_DATE = 404;

    private static final int SEATS = 500;
    private static final int SEAT_WITH_ID = 501;

    private static final int SEATS_CONFIGURATION = 600;
    private static final int SEAT_CONFIGURATION_WITH_ID = 601;
    private static final int SEAT_CONFIGURATION_WITH_ORGANIZATION_FLEET_TYPE_IDS = 603;

    private static final int SEAT_TYPES = 700;
    private static final int SEAT_TYPE_WITH_ID = 701;

    private static final int SEAT_CHARGES = 800;
    private static final int SEAT_CHARGE_WITH_ID = 801;
    private static final int SEAT_CHARGE_CURRENT = 802;

    private static final int FLEET_TYPES = 900;
    private static final int FLEET_TYPE_WITH_ID = 901;

    private static final int BOOKINGS = 1000;
    private static final int BOOKING_WITH_ID = 1001;

    private static final int BOOKING_DETAILS = 1100;
    private static final int BOOKING_DETAIL_WITH_ID = 1101;
    private static final int BOOKING_DETAILS_WITH_BOOKING_ID = 1102;



    static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SafiriPersistenceContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_TOWNS, TOWNS);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_TOWNS + "/#", TOWN_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ORGANIZATIONS, ORGANIZATIONS);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ORGANIZATIONS + "/#", ORGANIZATION_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ORGANIZATION_BRANCHES, ORGANIZATION_BRANCHES);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ORGANIZATION_BRANCHES + "/#", ORGANIZATION_BRANCH_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ROUTES, ROUTES);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ROUTES + "/#", ROUTE_WITH_ID);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ROUTES + "/nodeKey", ROUTE_WITH_NODE_KEY);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ROUTES + "/#/#/*", ROUTES_WITH_ORIGIN_DESTINATION_DATE);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_ROUTES + "/*", ROUTES_WITH_DATE);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEATS, SEATS);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEATS + "/#", SEAT_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEATS_CONFIGURATION, SEATS_CONFIGURATION);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEATS_CONFIGURATION + "/#", SEAT_CONFIGURATION_WITH_ID);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEATS_CONFIGURATION + "/#/#", SEAT_CONFIGURATION_WITH_ORGANIZATION_FLEET_TYPE_IDS);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEAT_CHARGES, SEAT_CHARGES);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEAT_CHARGES + "/#", SEAT_CHARGE_WITH_ID);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEAT_CHARGES + "/current/#", SEAT_CHARGE_CURRENT);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEAT_TYPES, SEAT_TYPES);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_SEAT_TYPES + "/#", SEAT_TYPE_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_FLEET_TYPES, FLEET_TYPES);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_FLEET_TYPES + "/#", FLEET_TYPE_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_BOOKINGS, BOOKINGS);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_BOOKINGS + "/#", BOOKING_WITH_ID);

        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_BOOKING_DETAILS, BOOKING_DETAILS);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_BOOKING_DETAILS + "/#", BOOKING_DETAIL_WITH_ID);
        uriMatcher.addURI(authority, SafiriPersistenceContract.PATH_BOOKING_DETAILS + "/booking/#", BOOKING_DETAILS_WITH_BOOKING_ID);

        return uriMatcher;
    }

    //organization name selection
    private static final String sOrganizationNameSelection =
            SafiriPersistenceContract.OrganizationsEntry.TABLE_NAME +
                    "." + SafiriPersistenceContract.OrganizationsEntry.COLUMN_NAME + " = ? ";

    //fleet type name selection
    private static final String sFleetTypeNameSelection =
            SafiriPersistenceContract.FleetTypesEntry.TABLE_NAME +
                    "." + SafiriPersistenceContract.FleetTypesEntry.COLUMN_NAME + " = ? ";

    //seat type name selection
    private static final String sSeatTypeNameSelection =
            SafiriPersistenceContract.SeatTypesEntry.TABLE_NAME +
                    "." + SafiriPersistenceContract.SeatTypesEntry.COLUMN_NAME + " = ? ";

    //town name selection
    private static final String sTownNameSelection =
            SafiriPersistenceContract.TownsEntry.TABLE_NAME +
                    "." + SafiriPersistenceContract.TownsEntry.COLUMN_NAME + " = ? ";

    @Override
    public boolean onCreate() {
        mOpenHelper = new SafiriDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case TOWNS:
                return SafiriPersistenceContract.TownsEntry.CONTENT_TYPE;
            case TOWN_WITH_ID:
                return SafiriPersistenceContract.TownsEntry.CONTENT_ITEM_TYPE;
            case ORGANIZATIONS:
                return SafiriPersistenceContract.OrganizationsEntry.CONTENT_TYPE;
            case ORGANIZATION_WITH_ID:
                return SafiriPersistenceContract.OrganizationsEntry.CONTENT_ITEM_TYPE;
            case ORGANIZATION_BRANCHES:
                return SafiriPersistenceContract.OrganizationBranchesEntry.CONTENT_TYPE;
            case ORGANIZATION_BRANCH_WITH_ID:
                return SafiriPersistenceContract.OrganizationBranchesEntry.CONTENT_ITEM_TYPE;
            case ROUTES:
                return SafiriPersistenceContract.RoutesEntry.CONTENT_TYPE;
            case ROUTES_WITH_ORIGIN_DESTINATION_DATE:
                return SafiriPersistenceContract.RoutesEntry.CONTENT_TYPE;
            case ROUTES_WITH_DATE:
                return SafiriPersistenceContract.RoutesEntry.CONTENT_TYPE;
            case ROUTE_WITH_ID:
                return SafiriPersistenceContract.RoutesEntry.CONTENT_ITEM_TYPE;
            case ROUTE_WITH_NODE_KEY:
                return SafiriPersistenceContract.RoutesEntry.CONTENT_ITEM_TYPE;
            case SEATS:
                return SafiriPersistenceContract.SeatsEntry.CONTENT_TYPE;
            case SEAT_WITH_ID:
                return SafiriPersistenceContract.SeatsEntry.CONTENT_ITEM_TYPE;
            case SEATS_CONFIGURATION:
                return SafiriPersistenceContract.SeatsConfigurationEntry.CONTENT_TYPE;
            case SEAT_CONFIGURATION_WITH_ID:
                return SafiriPersistenceContract.SeatsConfigurationEntry.CONTENT_ITEM_TYPE;
            case SEAT_CONFIGURATION_WITH_ORGANIZATION_FLEET_TYPE_IDS:
                return SafiriPersistenceContract.SeatsConfigurationEntry.CONTENT_ITEM_TYPE;
            case SEAT_CHARGES:
                return SafiriPersistenceContract.SeatChargesEntry.CONTENT_TYPE;
            case SEAT_CHARGE_WITH_ID:
                return SafiriPersistenceContract.SeatChargesEntry.CONTENT_ITEM_TYPE;
            case SEAT_CHARGE_CURRENT:
                return SafiriPersistenceContract.SeatChargesEntry.CONTENT_ITEM_TYPE;
            case SEAT_TYPES:
                return SafiriPersistenceContract.SeatTypesEntry.CONTENT_TYPE;
            case SEAT_TYPE_WITH_ID:
                return SafiriPersistenceContract.SeatTypesEntry.CONTENT_ITEM_TYPE;
            case FLEET_TYPES:
                return SafiriPersistenceContract.FleetTypesEntry.CONTENT_TYPE;
            case FLEET_TYPE_WITH_ID:
                return SafiriPersistenceContract.FleetTypesEntry.CONTENT_ITEM_TYPE;
            case BOOKINGS:
                return SafiriPersistenceContract.BookingsEntry.CONTENT_TYPE;
            case BOOKING_WITH_ID:
                return SafiriPersistenceContract.BookingsEntry.CONTENT_ITEM_TYPE;
            case BOOKING_DETAILS:
                return SafiriPersistenceContract.BookingDetailsEntry.CONTENT_TYPE;
            case BOOKING_DETAILS_WITH_BOOKING_ID:
                return SafiriPersistenceContract.BookingDetailsEntry.CONTENT_TYPE;
            case BOOKING_DETAIL_WITH_ID:
                return SafiriPersistenceContract.BookingDetailsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case TOWNS:
            case TOWN_WITH_ID:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.TownsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case ORGANIZATIONS:
            case ORGANIZATION_WITH_ID:
            {
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                String tables = OrganizationsEntry.TABLE_NAME + " INNER JOIN " +
                        TownsEntry.TABLE_NAME + " ON (" +
                        OrganizationsEntry.COLUMN_TOWN_ID + " = " +
                        TownsEntry.TABLE_NAME + "." + TownsEntry._ID + ") ";

                queryBuilder.setTables(tables);

                retCursor = queryBuilder.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ORGANIZATION_BRANCHES:
            case ORGANIZATION_BRANCH_WITH_ID:
            {
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                String tables = OrganizationBranchesEntry.TABLE_NAME + " INNER JOIN " +
                        TownsEntry.TABLE_NAME + " ON (" +
                        OrganizationBranchesEntry.COLUMN_TOWN_ID + " = " +
                        TownsEntry.TABLE_NAME + "." + TownsEntry._ID + ") ";

                queryBuilder.setTables(tables);

                retCursor = queryBuilder.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ROUTES:
            case ROUTE_WITH_ID:
            case ROUTES_WITH_ORIGIN_DESTINATION_DATE:
            case ROUTES_WITH_DATE:
            {
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                String tables = RoutesEntry.TABLE_NAME + " INNER JOIN " +
                        OrganizationsEntry.TABLE_NAME + " ON (" +
                        RoutesEntry.COLUMN_ORGANIZATION_ID + " = " +
                        OrganizationsEntry.TABLE_NAME + "." + OrganizationsEntry._ID + ") " +
                        "INNER JOIN " + FleetTypesEntry.TABLE_NAME + " ON (" +
                        RoutesEntry.COLUMN_FLEET_TYPE_ID + " = " +
                        FleetTypesEntry.TABLE_NAME + "." + FleetTypesEntry._ID + ") " +
                        "INNER JOIN " + TownsEntry.TABLE_NAME + " AS originTown ON (" +
                        RoutesEntry.COLUMN_ORIGIN + " = originTown." + TownsEntry._ID + ") " +
                        "INNER JOIN " + TownsEntry.TABLE_NAME + " AS destinationTown ON (" +
                        RoutesEntry.COLUMN_DESTINATION + " = destinationTown." + TownsEntry._ID + ") ";
                queryBuilder.setTables(tables);

                retCursor = queryBuilder.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ROUTE_WITH_NODE_KEY:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.RoutesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case SEATS:
            case SEAT_WITH_ID:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.SeatsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case SEATS_CONFIGURATION:
            case SEAT_CONFIGURATION_WITH_ID:
            case SEAT_CONFIGURATION_WITH_ORGANIZATION_FLEET_TYPE_IDS:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.SeatsConfigurationEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case SEAT_CHARGES:
            case SEAT_CHARGE_WITH_ID:
            case SEAT_CHARGE_CURRENT:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.SeatChargesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case SEAT_TYPES:
            case SEAT_TYPE_WITH_ID:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.SeatTypesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case FLEET_TYPES:
            case FLEET_TYPE_WITH_ID:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SafiriPersistenceContract.FleetTypesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case BOOKINGS:
            case BOOKING_WITH_ID:
            {
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                String tables = BookingsEntry.TABLE_NAME + " INNER JOIN " +
                        RoutesEntry.TABLE_NAME + " ON (" +
                        BookingsEntry.TABLE_NAME + "." + BookingsEntry.COLUMN_ROUTE_ID + " = " +
                        RoutesEntry.TABLE_NAME + "." + RoutesEntry._ID + ") " +
                        "INNER JOIN " + OrganizationsEntry.TABLE_NAME + " ON (" +
                        RoutesEntry.COLUMN_ORGANIZATION_ID + " = " +
                        OrganizationsEntry.TABLE_NAME + "." + OrganizationsEntry._ID + ") " +
                        "INNER JOIN " + TownsEntry.TABLE_NAME + " AS originTown ON (" +
                        RoutesEntry.COLUMN_ORIGIN + " = originTown." + TownsEntry._ID + ") " +
                        "INNER JOIN " + TownsEntry.TABLE_NAME + " AS destinationTown ON (" +
                        RoutesEntry.COLUMN_DESTINATION + " = destinationTown." + TownsEntry._ID + ") " +
                        "LEFT JOIN " + BookingDetailsEntry.TABLE_NAME + " ON (" +
                        BookingDetailsEntry.COLUMN_BOOKING_ID + " = " +
                        BookingsEntry.TABLE_NAME + "." + BookingsEntry._ID + ") AND " +
                        BookingDetailsEntry.TABLE_NAME + "."
                        + BookingDetailsEntry.COLUMN_STATUS + " != ? " +
                        "LEFT JOIN " + SeatChargesEntry.TABLE_NAME + " ON (" +
                        BookingDetailsEntry.COLUMN_SEAT_CHARGE_ID + " = " +
                        SeatChargesEntry.TABLE_NAME + "." + SeatChargesEntry._ID + ") ";
                queryBuilder.setTables(tables);
                String groupBy = BookingDetailsEntry.COLUMN_BOOKING_ID;
                String having = BookingDetailsEntry.TABLE_NAME + "."
                        + BookingDetailsEntry.COLUMN_STATUS + " != ?";

                String[] newSelectionArgs;

                if(selectionArgs != null && selectionArgs.length > 0){
                    newSelectionArgs = new String[selectionArgs.length + 1];
                    newSelectionArgs[0] = "Cancelled";

                    for (int i = 0; i < selectionArgs.length; i++) {
                        newSelectionArgs[i + 1] = selectionArgs[i];
                        Log.e("SafiriSyncAdapter", selectionArgs[i]);
                    }
                }else{
                    newSelectionArgs = new String[]{"Cancelled"};
                }

                retCursor = queryBuilder.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        newSelectionArgs,
                        groupBy,
                        null,
                        sortOrder
                );

                break;
            }
            case BOOKING_DETAILS:
            case BOOKING_DETAILS_WITH_BOOKING_ID:
            case BOOKING_DETAIL_WITH_ID:
            {
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                String tables = BookingDetailsEntry.TABLE_NAME + " INNER JOIN " +
                        SeatsEntry.TABLE_NAME + " ON (" +
                        BookingDetailsEntry.COLUMN_SEAT_ID + " = " +
                        SeatsEntry.TABLE_NAME + "." + SeatsEntry._ID + ") " +
                        "INNER JOIN " + SeatChargesEntry.TABLE_NAME + " ON (" +
                        BookingDetailsEntry.COLUMN_SEAT_CHARGE_ID + " = " +
                        SeatChargesEntry.TABLE_NAME + "." + SeatChargesEntry._ID + ") ";
                queryBuilder.setTables(tables);

                retCursor = queryBuilder.query(
                        mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        long _id = -1;
        switch (match) {
            case BOOKINGS:
                 _id = db.insert(SafiriPersistenceContract.BookingsEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = SafiriPersistenceContract.BookingsEntry.buildBookingUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert Booking item " + uri);
                }
                break;
            case BOOKING_DETAILS:
                _id = db.insert(SafiriPersistenceContract.BookingDetailsEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = SafiriPersistenceContract.BookingDetailsEntry.buildBookingDetailUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert BookingDetail item " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case BOOKING_WITH_ID:
                rowsUpdated = db.update(SafiriPersistenceContract.BookingsEntry.TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs
                );
                break;
            case BOOKING_DETAILS_WITH_BOOKING_ID:
            case BOOKING_DETAIL_WITH_ID:
                rowsUpdated = db.update(SafiriPersistenceContract.BookingDetailsEntry.TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final int match = sUriMatcher.match(uri);
        int returnCount = 0;

        switch (match) {
            case TOWNS:
                returnCount = insertValues(SafiriPersistenceContract.TownsEntry.TABLE_NAME, values);
                break;
            case ORGANIZATIONS:
                returnCount = insertValues(SafiriPersistenceContract.OrganizationsEntry.TABLE_NAME, values);
                break;
            case ORGANIZATION_BRANCHES:
                returnCount = insertValues(SafiriPersistenceContract.OrganizationBranchesEntry.TABLE_NAME, values);
                break;
            case ROUTES:
                returnCount = insertValues(SafiriPersistenceContract.RoutesEntry.TABLE_NAME, values);
                break;
            case SEATS:
                returnCount = insertValues(SafiriPersistenceContract.SeatsEntry.TABLE_NAME, values);
                break;
            case SEATS_CONFIGURATION:
                returnCount = insertValues(SafiriPersistenceContract.SeatsConfigurationEntry.TABLE_NAME, values);
                break;
            case SEAT_CHARGES:
                returnCount = insertValues(SafiriPersistenceContract.SeatChargesEntry.TABLE_NAME, values);
                break;
            case SEAT_TYPES:
                returnCount = insertValues(SafiriPersistenceContract.SeatTypesEntry.TABLE_NAME, values);
                break;
            case FLEET_TYPES:
                returnCount = insertValues(SafiriPersistenceContract.FleetTypesEntry.TABLE_NAME, values);
                break;
            default:
                returnCount = super.bulkInsert(uri, values);
        }

        if(returnCount > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return returnCount;
    }

    @Override
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }

    private int insertValues(String table, ContentValues[] values){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int affectedRows = 0;

        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                long _id = db.insert(table, null, value);
                if (_id != -1)
                    affectedRows++;
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return affectedRows;
    }
}
