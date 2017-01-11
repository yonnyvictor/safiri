package com.lighteye.safiri.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.FleetTypesEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.SeatTypesEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.OrganizationBranchesEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.OrganizationsEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.TownsEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.RoutesEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.SeatsEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.SeatChargesEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.SeatsConfigurationEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.BookingsEntry;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract.BookingDetailsEntry;


/**
 * Created by yonny on 7/16/16.
 */
public class SafiriDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Safiri.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ", ";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String NOT_NULL = " NOT NULL";
    private static final String AUTOINCREMENT = " AUTOINCREMENT";

    private static final String SQL_CREATE_TOWNS_TABLE = "CREATE TABLE " +
            TownsEntry.TABLE_NAME + " (" +
            TownsEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            TownsEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            TownsEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " UNIQUE (" + TownsEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";

    private static final String SQL_CREATE_FLEET_TYPES_TABLE = "CREATE TABLE " +
            FleetTypesEntry.TABLE_NAME + " (" +
            FleetTypesEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            FleetTypesEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            FleetTypesEntry.COLUMN_CAPACITY + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            FleetTypesEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " UNIQUE (" + FleetTypesEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";

    private static final String SQL_CREATE_SEAT_TYPES_TABLE = "CREATE TABLE " +
            SeatTypesEntry.TABLE_NAME + " (" +
            SeatTypesEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            SeatTypesEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatTypesEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " UNIQUE (" + SeatTypesEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";

    private static final String SQL_CREATE_ORGANIZATIONS_TABLE = "CREATE TABLE " +
            OrganizationsEntry.TABLE_NAME + " (" +
            OrganizationsEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            OrganizationsEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_TYPE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_ADDRESS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_CONTACTS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_TOWN_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_TOWN_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_CREATED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationsEntry.COLUMN_MODIFIED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + OrganizationsEntry.COLUMN_TOWN_ID + ") REFERENCES " +
            TownsEntry.TABLE_NAME + " (" + TownsEntry._ID + ") " +
            " UNIQUE (" + OrganizationsEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";

    private static final String SQL_CREATE_ORGANIZATION_BRANCHES_TABLE = "CREATE TABLE " +
            OrganizationBranchesEntry.TABLE_NAME + " (" +
            OrganizationBranchesEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_ADDRESS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_CONTACTS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_TOWN_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_TOWN_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_ORGANIZATION_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            OrganizationBranchesEntry.COLUMN_ORGANIZATION_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + OrganizationBranchesEntry.COLUMN_ORGANIZATION_ID + ") REFERENCES " +
            OrganizationsEntry.TABLE_NAME + " (" + OrganizationsEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + OrganizationBranchesEntry.COLUMN_TOWN_ID + ") REFERENCES " +
            TownsEntry.TABLE_NAME + " (" + TownsEntry._ID + ")" + COMMA_SEP +
            " UNIQUE (" + OrganizationsEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";

    private static final String SQL_CREATE_ROUTES_TABLE = "CREATE TABLE " +
            RoutesEntry.TABLE_NAME + " (" +
            RoutesEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            RoutesEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_DEPARTURE + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_ARRIVAL + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_ORIGIN + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_DESTINATION + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_ORIGIN_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_DESTINATION_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_FLEET_TYPE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_FLEET_TYPE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_ORGANIZATION_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_ORGANIZATION_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_MON + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_TUE + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_WED + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_THU + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_FRI + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_SAT + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_SUN + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_CREATED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_MODIFIED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            RoutesEntry.COLUMN_STATUS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + RoutesEntry.COLUMN_ORGANIZATION_ID + ") REFERENCES " +
            OrganizationsEntry.TABLE_NAME + " (" + OrganizationsEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + RoutesEntry.COLUMN_FLEET_TYPE_ID + ") REFERENCES " +
            FleetTypesEntry.TABLE_NAME + " (" + FleetTypesEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + RoutesEntry.COLUMN_ORIGIN + ") REFERENCES " +
            TownsEntry.TABLE_NAME + " (" + TownsEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + RoutesEntry.COLUMN_DESTINATION + ") REFERENCES " +
            TownsEntry.TABLE_NAME + " (" + TownsEntry._ID + ")" + COMMA_SEP +
            " UNIQUE (" + RoutesEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";


    private static final String SQL_CREATE_SEATS_TABLE = "CREATE TABLE " +
            SeatsEntry.TABLE_NAME + " (" +
            SeatsEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            SeatsEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_SEAT_ROW + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_SEAT_COLUMN + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_SEAT_TYPE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_SEAT_TYPE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_SEATS_CONFIGURATION_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_SEATS_CONFIGURATION_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatsEntry.COLUMN_STATUS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + SeatsEntry.COLUMN_SEAT_TYPE_ID + ") REFERENCES " +
            SeatTypesEntry.TABLE_NAME + " (" + SeatTypesEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + SeatsEntry.COLUMN_SEATS_CONFIGURATION_ID + ") REFERENCES " +
            SeatsConfigurationEntry.TABLE_NAME + " (" + SeatsConfigurationEntry._ID + ")" + COMMA_SEP +
            " UNIQUE (" + SeatsEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";


    private static final String SQL_CREATE_SEATS_CONFIGURATION_TABLE = "CREATE TABLE " +
            SeatsConfigurationEntry.TABLE_NAME + " (" +
            SeatsConfigurationEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_SEAT_ROWS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_SEAT_COLUMNS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_PATH_COLUMN + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_DRIVER_ROW_SEATS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_DOOR_ROW + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_DOOR_ROW_SEATS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_LAST_ROW_SEATS + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_FLEET_TYPE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_FLEET_TYPE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_ORGANIZATION_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatsConfigurationEntry.COLUMN_ORGANIZATION_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + SeatsConfigurationEntry.COLUMN_ORGANIZATION_ID + ") REFERENCES " +
            OrganizationsEntry.TABLE_NAME + " (" + OrganizationsEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + SeatsConfigurationEntry.COLUMN_FLEET_TYPE_ID + ") REFERENCES " +
            FleetTypesEntry.TABLE_NAME + " (" + FleetTypesEntry._ID + ")" + COMMA_SEP +
            " UNIQUE (" + SeatsConfigurationEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";

    private static final String SQL_CREATE_SEAT_CHARGES_TABLE = "CREATE TABLE " +
            SeatChargesEntry.TABLE_NAME + " (" +
            SeatChargesEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            SeatChargesEntry.COLUMN_CHARGE + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_SEAT_TYPE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_SEAT_TYPE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_ROUTE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_ROUTE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_CREATED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_MODIFIED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            SeatChargesEntry.COLUMN_CURRENT + BOOLEAN_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + SeatChargesEntry.COLUMN_SEAT_TYPE_ID + ") REFERENCES " +
            SeatTypesEntry.TABLE_NAME + " (" + SeatTypesEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + SeatChargesEntry.COLUMN_ROUTE_ID + ") REFERENCES " +
            RoutesEntry.TABLE_NAME + " (" + RoutesEntry._ID + ")" + COMMA_SEP +
            " UNIQUE (" + SeatChargesEntry.COLUMN_NODE_KEY + ")" +
            " ON CONFLICT REPLACE);";


    private static final String SQL_CREATE_BOOKINGS_TABLE = "CREATE TABLE " +
            BookingsEntry.TABLE_NAME + " (" +
            BookingsEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            BookingsEntry.COLUMN_USER_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            BookingsEntry.COLUMN_ROUTE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingsEntry.COLUMN_ROUTE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            BookingsEntry.COLUMN_TRAVEL_DATE + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingsEntry.COLUMN_STATUS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + BookingsEntry.COLUMN_ROUTE_ID + ") REFERENCES " +
            RoutesEntry.TABLE_NAME + " (" + RoutesEntry._ID + "));";


    private static final String SQL_CREATE_BOOKING_DETAILS_TABLE = "CREATE TABLE " +
            BookingDetailsEntry.TABLE_NAME + " (" +
            BookingDetailsEntry._ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            BookingDetailsEntry.COLUMN_BOOKING_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_NODE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_SEAT_CHARGE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_SEAT_CHARGE_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_SEAT_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_SEAT_KEY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_TRAVELLER + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_CREATED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_MODIFIED + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            BookingDetailsEntry.COLUMN_STATUS + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            " FOREIGN KEY (" + BookingDetailsEntry.COLUMN_SEAT_CHARGE_ID + ") REFERENCES " +
            SeatChargesEntry.TABLE_NAME + " (" + SeatChargesEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + BookingDetailsEntry.COLUMN_BOOKING_ID + ") REFERENCES " +
            RoutesEntry.TABLE_NAME + " (" + RoutesEntry._ID + ")" + COMMA_SEP +
            " FOREIGN KEY (" + BookingDetailsEntry.COLUMN_SEAT_ID + ") REFERENCES " +
            SeatsEntry.TABLE_NAME + " (" + SeatsEntry._ID + ")" + COMMA_SEP +
            " UNIQUE (" + BookingDetailsEntry.COLUMN_NODE_KEY + ") ON CONFLICT REPLACE);";

    public SafiriDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TOWNS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ROUTES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FLEET_TYPES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SEAT_TYPES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SEAT_CHARGES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SEATS_CONFIGURATION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SEATS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORGANIZATIONS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORGANIZATION_BRANCHES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKINGS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKING_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
