package com.lighteye.safiri.data.source.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lighteye.safiri.data.FleetType;
import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.data.OrganizationBranch;
import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.SeatCharge;
import com.lighteye.safiri.data.SeatType;
import com.lighteye.safiri.data.SeatsConfiguration;
import com.lighteye.safiri.data.Town;
import com.lighteye.safiri.data.source.entities.fleettypes.FleetTypeValues;
import com.lighteye.safiri.data.source.entities.fleettypes.FleetTypesDataSource;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchValues;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesDataSource;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationValues;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsDataSource;
import com.lighteye.safiri.data.source.entities.routes.RouteValues;
import com.lighteye.safiri.data.source.entities.routes.RoutesDataSource;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargeValues;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesDataSource;
import com.lighteye.safiri.data.source.entities.seats.SeatValues;
import com.lighteye.safiri.data.source.entities.seats.SeatsDataSource;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationDataSource;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationValues;
import com.lighteye.safiri.data.source.entities.seattypes.SeatTypeValues;
import com.lighteye.safiri.data.source.entities.seattypes.SeatTypesDataSource;
import com.lighteye.safiri.data.source.entities.towns.TownValues;
import com.lighteye.safiri.data.source.entities.towns.TownsDataSource;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;
import com.lighteye.safiri.data.source.remote.FleetTypesRemoteDataSource;
import com.lighteye.safiri.data.source.remote.OrganizationBranchesRemoteDataSource;
import com.lighteye.safiri.data.source.remote.OrganizationsRemoteDataSource;
import com.lighteye.safiri.data.source.remote.RoutesRemoteDataSource;
import com.lighteye.safiri.data.source.remote.SeatChargesRemoteDataSource;
import com.lighteye.safiri.data.source.remote.SeatTypesRemoteDataSource;
import com.lighteye.safiri.data.source.remote.SeatsConfigurationRemoteDataSource;
import com.lighteye.safiri.data.source.remote.SeatsRemoteDataSource;
import com.lighteye.safiri.data.source.remote.TownsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yonny on 7/22/16.
 */
public class SyncOperations implements SyncContract {

    public final String LOG_TAG = SyncOperations.class.getSimpleName();

    private final int TOWNS = 1;
    private final int ORGANIZATIONS = 2;
    private final int ORGANIZATION_BRANCHES = 3;
    private final int FLEET_TYPES = 4;
    private final int ROUTES = 5;
    private final int SEATS_CONFIGURATION = 6;
    private final int SEAT_TYPES = 7;
    private final int SEATS = 8;
    private final int SEAT_CHARGES = 9;
    private final int BOOKINGS = 10;

    private Context mContext;
    private TownsRemoteDataSource mTownsRemoteDataSource;
    private OrganizationsRemoteDataSource mOrganizationsRemoteDataSource;
    private OrganizationBranchesRemoteDataSource mOrganizationBranchesRemoteDataSource;
    private FleetTypesRemoteDataSource mFleetTypesRemoteDataSource;
    private SeatTypesRemoteDataSource mSeatTypesRemoteDataSource;
    private RoutesRemoteDataSource mRoutesRemoteDataSource;
    private SeatsConfigurationRemoteDataSource mSeatsConfigurationRemoteDataSource;
    private SeatChargesRemoteDataSource mSeatChargesRemoteDataSource;
    private SeatsRemoteDataSource mSeatsRemoteDataSource;

    public SyncOperations(Context context) {
        mContext = context;
        mTownsRemoteDataSource = new TownsRemoteDataSource();
        mOrganizationsRemoteDataSource = new OrganizationsRemoteDataSource();
        mOrganizationBranchesRemoteDataSource = new OrganizationBranchesRemoteDataSource();
        mFleetTypesRemoteDataSource = new FleetTypesRemoteDataSource();
        mSeatTypesRemoteDataSource = new SeatTypesRemoteDataSource();
        mRoutesRemoteDataSource = new RoutesRemoteDataSource();
        mSeatsConfigurationRemoteDataSource = new SeatsConfigurationRemoteDataSource();
        mSeatChargesRemoteDataSource = new SeatChargesRemoteDataSource();
        mSeatsRemoteDataSource = new SeatsRemoteDataSource();
    }

    @Override
    public void syncTowns(@NonNull final SyncComplete callback) {
            mTownsRemoteDataSource.getItems(new TownsDataSource.GetItemsCallback() {
            @Override
            public void onItemsLoaded(List<Town> items) {
                insertItems(TOWNS, items);
                callback.onComplete(TOWNS, true);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onComplete(TOWNS, false);
            }
        });
    }

    @Override
    public void syncOrganizations(@NonNull final SyncComplete callback) {
        mOrganizationsRemoteDataSource.getItems(new OrganizationsDataSource.GetItemsCallback() {
            @Override
            public void onItemsLoaded(List<Organization> items) {
                insertItems(ORGANIZATIONS, items);
                callback.onComplete(ORGANIZATIONS, true);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onComplete(ORGANIZATIONS, false);
            }
        });
    }

    @Override
    public void syncOrganizationBranches(@NonNull final SyncComplete callback) {
        mOrganizationBranchesRemoteDataSource.getItems(new OrganizationBranchesDataSource.GetItemsCallback() {
            @Override
            public void onItemsLoaded(List<OrganizationBranch> items) {
                insertItems(ORGANIZATION_BRANCHES, items);
                callback.onComplete(ORGANIZATION_BRANCHES, true);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onComplete(ORGANIZATION_BRANCHES, false);
            }
        });
    }

    @Override
    public void syncFleetTypes(@NonNull final SyncComplete callback) {
        mFleetTypesRemoteDataSource.getItems(new FleetTypesDataSource.GetItemsCallback() {
            @Override
            public void onItemsLoaded(List<FleetType> items) {
                insertItems(FLEET_TYPES, items);
                callback.onComplete(FLEET_TYPES, true);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onComplete(FLEET_TYPES, false);
            }
        });
    }

    @Override
    public void syncRoutes(@NonNull final SyncComplete callback) {
        mRoutesRemoteDataSource.getItems(new RoutesDataSource.GetItemsCallback() {
            @Override
            public void onRoutesLoaded(List<Route> items) {
                insertItems(ROUTES, items);
                callback.onComplete(ROUTES, true);
            }

            @Override
            public void onRoutesNotAvailable() {
                callback.onComplete(ROUTES, true);
            }
        });
    }

    @Override
    public void syncSeatsConfiguration(@NonNull final SyncComplete callback) {
        mSeatsConfigurationRemoteDataSource.getItems(new SeatsConfigurationDataSource.GetItemsCallback() {
            @Override
            public void onSeatsConfigurationsLoaded(List<SeatsConfiguration> items) {
                insertItems(SEATS_CONFIGURATION, items);
                callback.onComplete(SEATS_CONFIGURATION, true);
            }

            @Override
            public void onSeatsConfigurationsNotAvailable() {
                callback.onComplete(SEATS_CONFIGURATION, false);
            }
        });
    }

    @Override
    public void syncSeatTypes(@NonNull final SyncComplete callback) {
        mSeatTypesRemoteDataSource.getItems(new SeatTypesDataSource.GetItemsCallback() {
            @Override
            public void onItemsLoaded(List<SeatType> items) {
                insertItems(SEAT_TYPES, items);
                callback.onComplete(SEAT_TYPES, true);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onComplete(SEAT_TYPES, false);
            }
        });
    }

    @Override
    public void syncSeatCharges(@NonNull final SyncComplete callback) {
        mSeatChargesRemoteDataSource.getItems(new SeatChargesDataSource.GetItemsCallback() {
            @Override
            public void onSeatChargesLoaded(List<SeatCharge> items) {
                insertItems(SEAT_CHARGES, items);
                callback.onComplete(SEAT_CHARGES, true);
            }

            @Override
            public void onSeatChargesNotAvailable() {
                callback.onComplete(SEAT_CHARGES, false);
            }
        });
    }

    @Override
    public void syncSeats(@NonNull final SyncComplete callback) {
        mSeatsRemoteDataSource.getItems(new SeatsDataSource.GetItemsCallback() {
            @Override
            public void onSeatsLoaded(List<Seat> items) {
                insertItems(SEATS, items);
                callback.onComplete(SEATS, true);
            }

            @Override
            public void onSeatsNotAvailable() {
                callback.onComplete(SEATS, false);
            }
        });
    }

    @Override
    public void syncAll() {
        SyncComplete syncComplete = new SyncComplete() {
            @Override
            public void onComplete(int syncId, boolean success) {
                if(success){
                    switch (syncId){
                        case TOWNS:
                            syncOrganizations(this);
                            break;
                        case ORGANIZATIONS:
                            syncOrganizationBranches(this);
                            break;
                        case ORGANIZATION_BRANCHES:
                            syncFleetTypes(this);
                            break;
                        case FLEET_TYPES:
                            syncRoutes(this);
                            break;
                        case ROUTES:
                            syncSeatsConfiguration(this);
                            break;
                        case SEATS_CONFIGURATION:
                            syncSeatTypes(this);
                        case SEAT_TYPES:
                            syncSeats(this);
                            break;
                        case SEATS:
                            syncSeatCharges(this);
                            break;
                    }
                }
            }
        };
        syncTowns(syncComplete);
    }


    private <T> void insertItems(int entity, List<T> items){
        String logMessage = null;
        Uri uri = null;
        long townId, organizationId, fleetTypeId, routeId, seatTypeId, seatsConfigurationId, originId, destinationId;
        ArrayList<ContentValues> cVList = new ArrayList<ContentValues>();
        for(T t : items){
            switch (entity){
                case TOWNS:
                    uri = SafiriPersistenceContract.TownsEntry.CONTENT_URI;
                    Town town = (Town) t;
                    cVList.add(TownValues.form(town));
                    logMessage = "Towns";
                    break;
                case ORGANIZATIONS:
                    uri = SafiriPersistenceContract.OrganizationsEntry.CONTENT_URI;
                    Organization organization = (Organization) t;
                    townId = getTownId(organization.getTownKey());

                    if(townId > 0) {
                        organization.setTownId((int) townId);
                        cVList.add(OrganizationValues.form(organization));
                    }
                    logMessage = "Organizations";
                    break;
                case ORGANIZATION_BRANCHES:
                    uri = SafiriPersistenceContract.OrganizationBranchesEntry.CONTENT_URI;
                    OrganizationBranch branch = (OrganizationBranch) t;
                    townId = getTownId(branch.getTownKey());
                    organizationId = getOrganizationId(branch.getOrganizationKey());

                    if(townId > 0 && organizationId > 0){
                        branch.setTownId((int)townId);
                        branch.setOrganizationId((int)organizationId);

                        cVList.add(OrganizationBranchValues.form(branch));
                    }
                    logMessage = "Organization Branches";
                    break;
                case FLEET_TYPES:
                    FleetType fleetType = (FleetType) t;
                    cVList.add(FleetTypeValues.form(fleetType));
                    uri = SafiriPersistenceContract.FleetTypesEntry.CONTENT_URI;
                    logMessage = "FleetTypes";
                    break;
                case ROUTES:
                    uri = SafiriPersistenceContract.RoutesEntry.CONTENT_URI;
                    Route route = (Route) t;
                    fleetTypeId = getFleetTypeId(route.getFleetTypeKey());
                    organizationId = getOrganizationId(route.getOrganizationKey());
                    originId = getTownId(route.getOriginKey());
                    destinationId = getTownId(route.getDestinationKey());

                    if(fleetTypeId > 0 && organizationId > 0){
                        route.setFleetTypeId((int)fleetTypeId);
                        route.setOrganizationId((int)organizationId);
                        route.setOrigin((int)originId);
                        route.setDestination((int)destinationId);
                        cVList.add(RouteValues.form(route));
                    }
                    logMessage = "Routes";
                    break;
                case SEATS_CONFIGURATION:
                    uri = SafiriPersistenceContract.SeatsConfigurationEntry.CONTENT_URI;
                    SeatsConfiguration seatsConfiguration = (SeatsConfiguration) t;
                    fleetTypeId = getFleetTypeId(seatsConfiguration.getFleetTypeKey());
                    organizationId = getOrganizationId(seatsConfiguration.getOrganizationKey());

                    if(fleetTypeId > 0 && organizationId > 0){
                        seatsConfiguration.setFleetTypeId((int)fleetTypeId);
                        seatsConfiguration.setOrganizationId((int)organizationId);

                        cVList.add(SeatsConfigurationValues.form(seatsConfiguration));
                    }
                    logMessage = "SeatsConfiguration";
                    break;
                case SEAT_TYPES:
                    SeatType seatType = (SeatType) t;
                    cVList.add(SeatTypeValues.form(seatType));
                    uri = SafiriPersistenceContract.SeatTypesEntry.CONTENT_URI;
                    logMessage = "SeatTypes";
                    break;
                case SEATS:
                    Seat seat = (Seat) t;
                    uri = SafiriPersistenceContract.SeatsEntry.CONTENT_URI;
                    seatTypeId = getSeatTypeId(seat.getSeatTypeKey());
                    seatsConfigurationId = getSeatsConfigurationId(seat.getSeatsConfigurationKey());
                    if(seatTypeId > 0 && seatsConfigurationId > 0){
                        seat.setSeatTypeId((int)seatTypeId);
                        seat.setSeatsConfigurationId((int)seatsConfigurationId);

                        cVList.add(SeatValues.form(seat));
                    }
                    logMessage = "Seats";
                    break;
                case SEAT_CHARGES:
                    SeatCharge seatCharge = (SeatCharge) t;
                    uri = SafiriPersistenceContract.SeatChargesEntry.CONTENT_URI;
                    seatTypeId = getSeatTypeId(seatCharge.getSeatTypeKey());
                    routeId = getRouteId(seatCharge.getRouteKey());

                    if(seatTypeId > 0 && routeId > 0){
                        seatCharge.setSeatTypeId((int)seatTypeId);
                        seatCharge.setRouteId((int)routeId);

                        cVList.add(SeatChargeValues.form(seatCharge));
                    }
                    logMessage = "SeatCharges";
                    break;
            }
        }

        int inserted = insertData(uri, cVList);

        Log.e(LOG_TAG, inserted + ": " + logMessage + " Inserted");

    }

    private int insertData(Uri uri, ArrayList<ContentValues> items){
        int inserted = 0;
        // add to database
        if ( items.size() > 0 ) {
            ContentValues[] cvArray = new ContentValues[items.size()];
            items.toArray(cvArray);

            inserted = mContext.getContentResolver().bulkInsert(uri, cvArray);
        }

        return inserted;
    }

    private long getFleetTypeId(String key){
        return getItemId(SafiriPersistenceContract.FleetTypesEntry.CONTENT_URI,
                SafiriPersistenceContract.FleetTypesEntry._ID,
                SafiriPersistenceContract.FleetTypesEntry.COLUMN_NODE_KEY,
                key
        );
    }

    private long getSeatTypeId(String key){
        return getItemId(SafiriPersistenceContract.SeatTypesEntry.CONTENT_URI,
                SafiriPersistenceContract.SeatTypesEntry._ID,
                SafiriPersistenceContract.SeatTypesEntry.COLUMN_NODE_KEY,
                key
        );
    }

    private long getSeatsConfigurationId(String key){
        return getItemId(SafiriPersistenceContract.SeatsConfigurationEntry.CONTENT_URI,
                SafiriPersistenceContract.SeatsConfigurationEntry._ID,
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_NODE_KEY,
                key
        );
    }

    private long getOrganizationId(String key){
        return getItemId(SafiriPersistenceContract.OrganizationsEntry.CONTENT_URI,
                SafiriPersistenceContract.OrganizationsEntry._ID,
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_NODE_KEY,
                key
        );
    }

    private long getRouteId(String key){
        return getItemId(SafiriPersistenceContract.RoutesEntry.CONTENT_URI,
                SafiriPersistenceContract.RoutesEntry.TABLE_NAME + "." + SafiriPersistenceContract.RoutesEntry._ID + " AS _id",
                SafiriPersistenceContract.RoutesEntry.TABLE_NAME + "." + SafiriPersistenceContract.RoutesEntry.COLUMN_NODE_KEY,
                key
        );
    }

    private long getTownId(String key){
        return getItemId(SafiriPersistenceContract.TownsEntry.CONTENT_URI,
                SafiriPersistenceContract.TownsEntry._ID,
                SafiriPersistenceContract.TownsEntry.COLUMN_NODE_KEY,
                key
        );
    }

    /*
     * Helper method to retrieve row id based on the node key
     */
    private long getItemId(Uri uri, String idColumn, String selectionColumn, String nodeKey){
        long itemId = 0;
        // First, check if the town with this key exists in the db
        Cursor cursor = mContext.getContentResolver().query(
                uri,
                new String[]{idColumn},
                selectionColumn + " = ?",
                new String[]{nodeKey},
                null);

        if (cursor.moveToFirst()) {
            int itemIdIndex = cursor.getColumnIndex("_id");
            itemId = cursor.getLong(itemIdIndex);
            cursor.close();;
        }

        return itemId;
    }
}
