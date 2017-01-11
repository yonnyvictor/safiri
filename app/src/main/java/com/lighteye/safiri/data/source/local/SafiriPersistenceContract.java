package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.lighteye.safiri.BuildConfig;

/**
 * Created by yonny on 7/16/16.
 */
public class SafiriPersistenceContract {

    private static final String SEPARATOR = "/";
    private static final String CONTENT_SCHEME = "content://";

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);

    public static final String PATH_TOWNS = "towns";
    public static final String PATH_ROUTES = "routes";
    public static final String PATH_SEATS = "seats";
    public static final String PATH_SEAT_CHARGES = "seat_charges";
    public static final String PATH_SEATS_CONFIGURATION = "seats_confiuration";
    public static final String PATH_FLEET_TYPES = "fleet_types";
    public static final String PATH_SEAT_TYPES = "seat_types";
    public static final String PATH_ORGANIZATIONS = "organizations";
    public static final String PATH_ORGANIZATION_BRANCHES = "organization_branches";
    public static final String PATH_BOOKINGS = "bookings";
    public static final String PATH_BOOKING_DETAILS = "booking_details";


    public SafiriPersistenceContract() {
    }

    /*
        Inner class that defines the table contents of the towns table
     */
    public static final class TownsEntry implements BaseColumns {
        public static final String TABLE_NAME = "towns";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOWNS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_TOWNS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_TOWNS;

        public static Uri buildTownUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTownUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildTownsUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }

    /*
        Inner class that defines the table contents of the organizations table
     */
    public static final class OrganizationsEntry implements BaseColumns {
        public static final String TABLE_NAME = "organizations";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CONTACTS = "contacts";
        public static final String COLUMN_TOWN_ID = "town_id";
        public static final String COLUMN_TOWN_KEY = "town_key";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_MODIFIED = "modified";

        public static final String COLUMN_ORGANIZATION_ID_ALIAS = "_id";
        public static final String COLUMN_ORGANIZATION_NAME_ALIAS = "organizationName";
        public static final String COLUMN_TOWN_NAME_ALIAS = "townName";
        public static final String COLUMN_ORGANIZATION_NODE_KEY_ALIAS = "organizationNode";

        public static final String COLUMN_ORGANIZATION_ID = TABLE_NAME +
                "." + OrganizationsEntry._ID + " AS " + COLUMN_ORGANIZATION_ID_ALIAS;

        public static final String COLUMN_ORGANIZATION_NAME = TABLE_NAME +
                "." +COLUMN_NAME + " AS " + COLUMN_ORGANIZATION_NAME_ALIAS;

        public static final String COLUMN_ORGANIZATION_NODE_KEY = TABLE_NAME +
                "." + COLUMN_NODE_KEY + " AS " + COLUMN_ORGANIZATION_NODE_KEY_ALIAS;

        public static final String COLUMN_TOWN_NAME = TownsEntry.TABLE_NAME +
                "." + TownsEntry.COLUMN_NAME + " AS " + COLUMN_TOWN_NAME_ALIAS;

        public static String[] ORGANIZATIONS_COLUMNS = new String[]{
                COLUMN_ORGANIZATION_ID,
                COLUMN_ORGANIZATION_NAME,
                COLUMN_TYPE,
                COLUMN_ADDRESS,
                COLUMN_CONTACTS,
                COLUMN_TOWN_ID,
                COLUMN_TOWN_KEY,
                COLUMN_ORGANIZATION_NODE_KEY,
                COLUMN_CREATED,
                COLUMN_MODIFIED,
                COLUMN_TOWN_NAME
        };

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ORGANIZATIONS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_ORGANIZATIONS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_ORGANIZATIONS;

        public static Uri buildOrganizationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildOrganizationUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }
    }

    /*
        Inner class that defines the table contents of the organization branches table
     */
    public static final class OrganizationBranchesEntry implements BaseColumns {
        public static final String TABLE_NAME = "organization_branches";
        public static final String COLUMN_ORGANIZATION_ID = "organization_id";
        public static final String COLUMN_TOWN_ID = "town_id";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CONTACTS = "contacts";
        public static final String COLUMN_TOWN_KEY = "town_key";
        public static final String COLUMN_ORGANIZATION_KEY = "organization_key";
        public static final String COLUMN_NODE_KEY = "node_key";

        public static final String COLUMN_ORGANIZATION_BRANCH_ID_ALIAS = "_id";
        public static final String COLUMN_TOWN_NAME_ALIAS = "townName";
        public static final String COLUMN_ORGANIZATION_BRANCH_NODE_KEY_ALIAS = "organizationBranchNode";

        public static final String COLUMN_ORGANIZATION_BRANCH_ID = TABLE_NAME +
                "." + OrganizationBranchesEntry._ID + " AS " + COLUMN_ORGANIZATION_BRANCH_ID_ALIAS;

        public static final String COLUMN_ORGANIZATION_BRANCH_NODE_KEY = TABLE_NAME +
                "." + COLUMN_NODE_KEY + " AS " + COLUMN_ORGANIZATION_BRANCH_NODE_KEY_ALIAS;

        public static final String COLUMN_TOWN_NAME = TownsEntry.TABLE_NAME +
                "." + TownsEntry.COLUMN_NAME + " AS " + COLUMN_TOWN_NAME_ALIAS;

        public static String[] ORGANIZATION_BRANCHES_COLUMNS = new String[]{
                COLUMN_ORGANIZATION_BRANCH_ID,
                COLUMN_ORGANIZATION_ID,
                COLUMN_ORGANIZATION_KEY,
                COLUMN_ADDRESS,
                COLUMN_CONTACTS,
                COLUMN_TOWN_ID,
                COLUMN_TOWN_KEY,
                COLUMN_ORGANIZATION_BRANCH_NODE_KEY,
                COLUMN_TOWN_NAME
        };


        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ORGANIZATION_BRANCHES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_ORGANIZATION_BRANCHES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_ORGANIZATION_BRANCHES;

        public static Uri buildOrganizationBranchUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildOrganizationBranchUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildOrganizationBranchesWithOrganizationUri(String organizationId) {
            return CONTENT_URI.buildUpon().appendPath(organizationId).build();
        }
    }

    /*
        Inner class that defines the table contents of the fleet types table
     */
    public static final class FleetTypesEntry implements BaseColumns {
        public static final String TABLE_NAME = "fleet_types";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CAPACITY = "capacity";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FLEET_TYPES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_FLEET_TYPES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_FLEET_TYPES;

        public static Uri buildFleetTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFleetTypeUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildFleetTypesUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }

    /*
        Inner class that defines the table contents of the seat types table
     */
    public static final class SeatTypesEntry implements BaseColumns {
        public static final String TABLE_NAME = "seat_types";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SEAT_TYPES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEAT_TYPES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEAT_TYPES;

        public static Uri buildSeatTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSeatTypeUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildSeatTypesUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }


    /*
        Inner class that defines the table contents of the routes table
     */
    public static final class RoutesEntry implements BaseColumns {

        public static final String TABLE_NAME = "routes";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORGANIZATION_ID = "organization_id";
        public static final String COLUMN_ORGANIZATION_KEY = "organization_key";
        public static final String COLUMN_FLEET_TYPE_ID = "fleet_type_id";
        public static final String COLUMN_FLEET_TYPE_KEY = "fleet_type_key";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_ORIGIN_KEY = "origin_key";
        public static final String COLUMN_DESTINATION_KEY = "destination_key";
        public static final String COLUMN_ORIGIN = "origin";
        public static final String COLUMN_DESTINATION = "destination";
        public static final String COLUMN_DEPARTURE = "departure_time";
        public static final String COLUMN_ARRIVAL = "arrival_time";
        public static final String COLUMN_MON = "mon";
        public static final String COLUMN_TUE = "tue";
        public static final String COLUMN_WED = "wed";
        public static final String COLUMN_THU = "thu";
        public static final String COLUMN_FRI = "fri";
        public static final String COLUMN_SAT = "sat";
        public static final String COLUMN_SUN = "sun";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_MODIFIED = "modified";
        public static final String COLUMN_STATUS = "status";

        public static final String COLUMN_ROUTE_ID_ALIAS = "_id";
        public static final String COLUMN_ROUTE_NAME_ALIAS = "routeName";
        public static final String COLUMN_ROUTE_NODE_KEY_ALIAS = "routeNode";
        public static final String COLUMN_ROUTE_CREATED_ALIAS = "routeCreated";
        public static final String COLUMN_ROUTE_MODIFIED_ALIAS = "routeModified";
        public static final String COLUMN_ORGANIZATION_NAME_ALIAS = "organizationName";
        public static final String COLUMN_FLEET_TYPE_NAME_ALIAS = "fleetTypeName";
        public static final String COLUMN_FLEET_TYPE_CAPACITY_ALIAS = "fleetTypeCapacity";
        public static final String COLUMN_ORIGIN_NAME_ALIAS = "originName";
        public static final String COLUMN_DESTINATION_NAME_ALIAS = "destinationName";

        public static final String COLUMN_ROUTE_ID = TABLE_NAME +
                "." + RoutesEntry._ID + " AS " + COLUMN_ROUTE_ID_ALIAS;

        public static final String COLUMN_ROUTE_NAME = TABLE_NAME +
                "." + RoutesEntry.COLUMN_NAME + " AS " + COLUMN_ROUTE_NAME_ALIAS;

        public static final String COLUMN_ROUTE_NODE_KEY = RoutesEntry.TABLE_NAME +
                "." + RoutesEntry.COLUMN_NODE_KEY + " AS " + COLUMN_ROUTE_NODE_KEY_ALIAS;

        public static final String COLUMN_ROUTE_CREATED = RoutesEntry.TABLE_NAME +
                "." + RoutesEntry.COLUMN_CREATED + " AS " + COLUMN_ROUTE_CREATED_ALIAS;

        public static final String COLUMN_ROUTE_MODIFIED = RoutesEntry.TABLE_NAME +
                "." + RoutesEntry.COLUMN_MODIFIED + " AS " + COLUMN_ROUTE_MODIFIED_ALIAS;

        public static final String COLUMN_ORGANIZATION_NAME = OrganizationsEntry.TABLE_NAME +
                "." + OrganizationsEntry.COLUMN_NAME + " AS organizationName";

        public static final String COLUMN_FLEET_TYPE_NAME = FleetTypesEntry.TABLE_NAME + "." +
                FleetTypesEntry.COLUMN_NAME + " AS fleetTypeName";

        public static final String COLUMN_FLEET_TYPE_CAPACITY = FleetTypesEntry.TABLE_NAME + "." +
                FleetTypesEntry.COLUMN_CAPACITY + " AS fleetTypeCapacity";

        public static final String COLUMN_ORIGIN_NAME = "originTown.name AS originName";

        public static final String COLUMN_DESTINATION_NAME = "destinationTown.name AS destinationName";

        public static String[] ROUTES_COLUMNS = new String[]{
                RoutesEntry.COLUMN_ROUTE_ID,
                RoutesEntry.COLUMN_ROUTE_NAME,
                RoutesEntry.COLUMN_ORIGIN,
                RoutesEntry.COLUMN_ORIGIN_KEY,
                RoutesEntry.COLUMN_DESTINATION,
                RoutesEntry.COLUMN_DESTINATION_KEY,
                RoutesEntry.COLUMN_DEPARTURE,
                RoutesEntry.COLUMN_ARRIVAL,
                RoutesEntry.COLUMN_ORGANIZATION_ID,
                RoutesEntry.COLUMN_ORGANIZATION_KEY,
                RoutesEntry.COLUMN_FLEET_TYPE_ID,
                RoutesEntry.COLUMN_FLEET_TYPE_KEY,
                RoutesEntry.COLUMN_ROUTE_NODE_KEY,
                RoutesEntry.COLUMN_MON,
                RoutesEntry.COLUMN_TUE,
                RoutesEntry.COLUMN_WED,
                RoutesEntry.COLUMN_THU,
                RoutesEntry.COLUMN_FRI,
                RoutesEntry.COLUMN_SAT,
                RoutesEntry.COLUMN_SUN,
                RoutesEntry.COLUMN_ROUTE_CREATED,
                RoutesEntry.COLUMN_ROUTE_MODIFIED,
                RoutesEntry.COLUMN_STATUS,
                RoutesEntry.COLUMN_ORGANIZATION_NAME,
                RoutesEntry.COLUMN_FLEET_TYPE_NAME,
                RoutesEntry.COLUMN_FLEET_TYPE_CAPACITY,
                RoutesEntry.COLUMN_ORIGIN_NAME,
                RoutesEntry.COLUMN_DESTINATION_NAME
        };

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROUTES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_ROUTES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_ROUTES;

        public static Uri buildRouteUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRouteUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildTodayRoutesUri(String day) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(day).build();
            return uri;
        }

        public static Uri buildSearchRoutesUri(String origin, String destination, String day) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(origin).appendPath(destination).appendPath(day).build();
            return uri;
        }
    }

    /*
        Inner class that defines the table contents of the seats table
     */
    public static final class SeatsEntry implements BaseColumns {
        public static final String TABLE_NAME = "seats";
        public static final String COLUMN_SEATS_CONFIGURATION_ID = "seats_configuration_id";
        public static final String COLUMN_SEAT_TYPE_ID = "seat_type_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SEAT_ROW = "seat_row";
        public static final String COLUMN_SEAT_COLUMN = "seat_column";
        public static final String COLUMN_SEAT_TYPE_KEY = "seat_type_key";
        public static final String COLUMN_SEATS_CONFIGURATION_KEY = "seats_configuration_key";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_STATUS = "status";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SEATS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEATS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEATS;

        public static Uri buildSeatUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSeatsWithSeatsConfigurationUri(String seatsConfigurationId) {
            return CONTENT_URI.buildUpon().appendPath(seatsConfigurationId).build();
        }

        public static Uri buildSeatUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }
    }

    /*
        Inner class that defines the table contents of the towns table
     */
    public static final class SeatsConfigurationEntry implements BaseColumns {
        public static final String TABLE_NAME = "seats_configuration";
        public static final String COLUMN_ORGANIZATION_ID = "organization_id";
        public static final String COLUMN_FLEET_TYPE_ID = "fleet_type_id";
        public static final String COLUMN_ORGANIZATION_KEY = "organization_key";
        public static final String COLUMN_FLEET_TYPE_KEY = "fleet_type_key";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_SEAT_ROWS = "seat_rows";
        public static final String COLUMN_SEAT_COLUMNS = "seat_columns";
        public static final String COLUMN_PATH_COLUMN = "path_column";
        public static final String COLUMN_DRIVER_ROW_SEATS = "driver_row_seats";
        public static final String COLUMN_DOOR_ROW_SEATS = "door_row_seats";
        public static final String COLUMN_LAST_ROW_SEATS = "last_row_seats";
        public static final String COLUMN_DOOR_ROW = "door_row";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SEATS_CONFIGURATION).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEATS_CONFIGURATION;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEATS_CONFIGURATION;

        public static Uri buildSeatsConfigurationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSeatsConfigurationUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildSeatsConfigurationWithOrganizationFleetTypeUri(String organizationId, String fleetTypeId) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(organizationId).appendPath(fleetTypeId).build();
            return uri;
        }

        public static Uri buildSeatsConfigurationsUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }

    /*
        Inner class that defines the table contents of the towns table
     */
    public static final class SeatChargesEntry implements BaseColumns {
        public static final String TABLE_NAME = "seat_charges";
        public static final String COLUMN_SEAT_TYPE_ID = "seat_type_id";
        public static final String COLUMN_ROUTE_ID = "route_id";
        public static final String COLUMN_SEAT_TYPE_KEY = "seat_type_key";
        public static final String COLUMN_ROUTE_KEY = "route_key";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_CHARGE = "charge";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_MODIFIED = "modified";
        public static final String COLUMN_CURRENT = "current";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SEAT_CHARGES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEAT_CHARGES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_SEAT_CHARGES;

        public static Uri buildSeatChargeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSeatChargeUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildCurrentSeatChargeUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath("current").appendPath(id).build();
            return uri;
        }

        public static Uri buildSeatChargesUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }

    /*
        Inner class that defines the table contents of the towns table
     */
    public static final class BookingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "bookings";
        public static final String COLUMN_ROUTE_ID = "route_id";
        public static final String COLUMN_ROUTE_KEY = "route_key";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_USER_KEY = "user_key";
        public static final String COLUMN_TRAVEL_DATE = "travel_date";
        public static final String COLUMN_STATUS = "status";


        public static final String COLUMN_BOOKING_ID_ALIAS = "_id";
        public static final String COLUMN_ROUTE_ID_ALIAS = "route_id";
        public static final String COLUMN_TOTAL_CHARGE_ALIAS = "totalCharge";
        public static final String COLUMN_ROUTE_NODE_KEY_ALIAS = "routeNode";
        public static final String COLUMN_BOOKING_STATUS_ALIAS = "bookingStatus";


        public static final String COLUMN_BOOKING_ROUTE_ID = TABLE_NAME +
                "." + BookingsEntry.COLUMN_ROUTE_ID + " AS " + COLUMN_ROUTE_ID_ALIAS;

        public static final String COLUMN_BOOKING_ID = TABLE_NAME +
                "." + BookingsEntry._ID + " AS " + COLUMN_BOOKING_ID_ALIAS;

        public static final String COLUMN_TOTAL_CHARGE = "SUM ( " +
                SeatChargesEntry.COLUMN_CHARGE + " ) AS " + COLUMN_TOTAL_CHARGE_ALIAS;



        public static final String COLUMN_ROUTE_NODE_KEY = BookingsEntry.TABLE_NAME +
                "." + BookingsEntry.COLUMN_ROUTE_KEY + " AS " + COLUMN_ROUTE_NODE_KEY_ALIAS;

        public static final String COLUMN_BOOKING_STATUS = BookingsEntry.TABLE_NAME +
                "." + BookingsEntry.COLUMN_STATUS + " AS " + COLUMN_BOOKING_STATUS_ALIAS;


        public static String[] BOOKING_COLUMNS = new String[]{
                COLUMN_BOOKING_ID,
                COLUMN_BOOKING_ROUTE_ID,
                COLUMN_ROUTE_NODE_KEY,
                COLUMN_USER_KEY,
                COLUMN_TRAVEL_DATE,
                COLUMN_BOOKING_STATUS,
                RoutesEntry.COLUMN_ORGANIZATION_NAME,
                RoutesEntry.COLUMN_ORIGIN_NAME,
                RoutesEntry.COLUMN_DESTINATION_NAME,
                COLUMN_TOTAL_CHARGE
        };

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BOOKINGS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_BOOKINGS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_BOOKINGS;

        public static Uri buildBookingUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildBookingUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildBookingsUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }

    /*
        Inner class that defines the table contents of the towns table
     */
    public static final class BookingDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "booking_details";
        public static final String COLUMN_BOOKING_ID = "booking_id";
        public static final String COLUMN_SEAT_ID = "seat_id";
        public static final String COLUMN_SEAT_CHARGE_ID = "seat_charge_id";
        public static final String COLUMN_NODE_KEY = "node_key";
        public static final String COLUMN_SEAT_KEY = "seat_key";
        public static final String COLUMN_SEAT_CHARGE_KEY = "seat_charge_key";
        public static final String COLUMN_TRAVELLER = "traveller";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_MODIFIED = "modified";
        public static final String COLUMN_STATUS = "status";

        public static final String COLUMN_BOOKING_DETAIL_ID_ALIAS = "_id";
        public static final String COLUMN_SEAT_NAME_ALIAS = "seatName";
        public static final String COLUMN_SEAT_CHARGE_ALIAS = "seatCharge";
        public static final String COLUMN_BOOKING_DETAIL_NODE_KEY_ALIAS = "bookingDetailNode";
        public static final String COLUMN_BOOKING_DETAIL_CREATED_ALIAS = "bookingDetailCreated";
        public static final String COLUMN_BOOKING_DETAIL_MODIFIED_ALIAS = "bookingDetailModified";
        public static final String COLUMN_BOOKING_DETAIL_STATUS_ALIAS = "bookingDetailStatus";

        public static final String COLUMN_BOOKING_DETAIL_ID = TABLE_NAME +
                "." + BookingDetailsEntry._ID + " AS " + COLUMN_BOOKING_DETAIL_ID_ALIAS;

        public static final String COLUMN_SEAT_NAME = SeatsEntry.TABLE_NAME +
                "." + SeatsEntry.COLUMN_NAME + " AS " + COLUMN_SEAT_NAME_ALIAS;

        public static final String COLUMN_SEAT_CHARGE = SeatChargesEntry.TABLE_NAME +
                "." + SeatChargesEntry.COLUMN_CHARGE + " AS " + COLUMN_SEAT_CHARGE_ALIAS;

        public static final String COLUMN_BOOKING_DETAIL_NODE_KEY = BookingDetailsEntry.TABLE_NAME +
                "." + BookingDetailsEntry.COLUMN_NODE_KEY + " AS " + COLUMN_BOOKING_DETAIL_NODE_KEY_ALIAS;

        public static final String COLUMN_BOOKING_DETAIL_CREATED = BookingDetailsEntry.TABLE_NAME +
                "." + BookingDetailsEntry.COLUMN_CREATED + " AS " + COLUMN_BOOKING_DETAIL_CREATED_ALIAS;

        public static final String COLUMN_BOOKING_DETAIL_MODIFIED = BookingDetailsEntry.TABLE_NAME +
                "." + BookingDetailsEntry.COLUMN_MODIFIED + " AS " + COLUMN_BOOKING_DETAIL_MODIFIED_ALIAS;

        public static final String COLUMN_BOOKING_DETAIL_STATUS = BookingDetailsEntry.TABLE_NAME +
                "." + BookingDetailsEntry.COLUMN_STATUS + " AS " + COLUMN_BOOKING_DETAIL_STATUS_ALIAS;

        public static String[] BOOKING_DETAILS_COLUMNS = new String[]{
                COLUMN_BOOKING_DETAIL_ID,
                COLUMN_BOOKING_ID,
                COLUMN_SEAT_ID,
                COLUMN_SEAT_CHARGE_ID,
                COLUMN_BOOKING_DETAIL_NODE_KEY,
                COLUMN_SEAT_KEY,
                COLUMN_SEAT_CHARGE_KEY,
                COLUMN_TRAVELLER,
                COLUMN_SEAT_NAME,
                COLUMN_SEAT_CHARGE,
                COLUMN_BOOKING_DETAIL_CREATED,
                COLUMN_BOOKING_DETAIL_MODIFIED,
                COLUMN_BOOKING_DETAIL_STATUS
        };

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BOOKING_DETAILS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_BOOKING_DETAILS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + SEPARATOR + CONTENT_AUTHORITY + SEPARATOR + PATH_BOOKING_DETAILS;

        public static Uri buildBookingDetailUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildBookingDetailUri(String id) {
            Uri uri = CONTENT_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildBookingDetailsWithBookingUri(String bookingId) {
            return CONTENT_URI.buildUpon().appendPath("booking").appendPath(bookingId).build();
        }

        public static Uri buildBookingDetailsUri(){
            return CONTENT_URI.buildUpon().build();
        }
    }
}
