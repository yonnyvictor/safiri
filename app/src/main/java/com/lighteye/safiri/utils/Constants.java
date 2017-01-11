package com.lighteye.safiri.utils;

import com.lighteye.safiri.BuildConfig;

/**
 * Created by yonny on 7/14/16.
 */
public final class Constants {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static final String DISPLAY_DATE_FORMAT = "dd MMMM yyyy";

    public static final String TIME_FORMAT = "HH:mm";

    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD = "hasLoggedInWithPassword";


    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    /**
     * Constants related to locations in Firebase.
     */
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_TOWNS = "towns";
    public static final String FIREBASE_LOCATION_ORGANIZATIONS = "organizations";
    public static final String FIREBASE_LOCATION_ORGANIZATION_BRANCHES = "organizationBranches";
    public static final String FIREBASE_LOCATION_BOOKINGS = "bookings";




    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_FIRST_RUN = "FIRST_RUN";

    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";

    public static final String KEY_UID = "UID";
    public static final String KEY_TOKEN = "TOKEN";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_USERNAME = "USERNAME";
    public static final String KEY_ORGANIZATION_ID = "ORGANIZATION_ID";
    public static final String KEY_FLEET_TYPE_ID = "FLEET_TYPE_ID";

    /**
     * Constants for loaders
     */
    public static final int ORGANIZATIONS_LOADER = 1;
    public static final int ORGANIZATION_LOADER = 2;

    public static final int ORGANIZATION_BRANCHES_LOADER = 3;

    public static final int ROUTES_LOADER = 4;
    public static final int ROUTE_LOADER = 5;
    public static final int ROUTES_SEARCH_LOADER = 14;
    public static final int ROUTES_DAY_LOADER = 15;

    public static final int TOWNS_LOADER = 6;

    public static final int BOOKINGS_LOADER = 7;
    public static final int BOOKING_LOADER = 8;

    public static final int BOOKING_DETAILS_LOADER = 9;

    public static final int SEATS_LOADER = 10;

    public static final int SEATS_CONFIGURATION_LOADER = 11;

    public static final int SEAT_CHARGES_LOADER = 12;
    public static final int SEAT_CHARGE_LOADER = 13;


}
