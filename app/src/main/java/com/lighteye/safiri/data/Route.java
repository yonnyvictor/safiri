package com.lighteye.safiri.data;

import android.database.Cursor;

import static com.lighteye.safiri.data.source.local.SafiriPersistenceContract.RoutesEntry;

/**
 * Created by yonny on 7/20/16.
 */
public class Route {
    private int id;
    private String nodeKey;
    private String organizationKey;
    private String fleetTypeKey;
    private String name;
    private String originKey;
    private String destinationKey;
    private int departureTime;
    private String arrivalTime;
    private int organizationId;
    private int fleetTypeId;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thu;
    private boolean fri;
    private boolean sat;
    private boolean sun;
    private Timestamp timestampCreated;
    private Timestamp timestampLastChanged;
    private String status;
    private int origin;
    private int destination;
    private String organizationName;
    private String originName;
    private String destinationName;
    private String fleetTypeName;
    private int fleetTypeCapacity;

    public Route() {
    }

    public Route(int id, String nodeKey, String organizationKey, String fleetTypeKey, String name,
                 String originKey, String destinationKey, int departureTime, String arrivalTime,
                 int organizationId, int fleetTypeId, boolean mon, boolean tue, boolean wed,
                 boolean thu, boolean fri, boolean sat, boolean sun, Timestamp timestampCreated,
                 Timestamp timestampLastChanged, String status, int origin, int destination) {
        this.id = id;
        this.nodeKey = nodeKey;
        this.organizationKey = organizationKey;
        this.fleetTypeKey = fleetTypeKey;
        this.name = name;
        this.originKey = originKey;
        this.destinationKey = destinationKey;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.organizationId = organizationId;
        this.fleetTypeId = fleetTypeId;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
        this.status = status;
        this.origin = origin;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public String getFleetTypeKey() {
        return fleetTypeKey;
    }

    public void setFleetTypeKey(String fleetTypeKey) {
        this.fleetTypeKey = fleetTypeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginKey() {
        return originKey;
    }

    public void setOriginKey(String originKey) {
        this.originKey = originKey;
    }

    public String getDestinationKey() {
        return destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getFleetTypeId() {
        return fleetTypeId;
    }

    public void setFleetTypeId(int fleetTypeId) {
        this.fleetTypeId = fleetTypeId;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public Timestamp getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Timestamp timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Timestamp getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(Timestamp timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getFleetTypeName() {
        return fleetTypeName;
    }

    public void setFleetTypeName(String fleetTypeName) {
        this.fleetTypeName = fleetTypeName;
    }

    public int getFleetTypeCapacity() {
        return fleetTypeCapacity;
    }

    public void setFleetTypeCapacity(int fleetTypeCapacity) {
        this.fleetTypeCapacity = fleetTypeCapacity;
    }

    public static Route from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry._ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ROUTE_NAME_ALIAS));
        String status = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_STATUS));
        int departure = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_DEPARTURE));
        String arrival = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ARRIVAL));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ROUTE_NODE_KEY_ALIAS));
        String organizationKey = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ORGANIZATION_KEY));
        String fleetTypeKey = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_FLEET_TYPE_KEY));
        String destinationKey = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_DESTINATION_KEY));
        String originKey = cursor.getString(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ORIGIN_KEY));
        int origin = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ORIGIN));
        int destination = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_DESTINATION));
        int organizationId = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ORGANIZATION_ID));
        int fleetTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_FLEET_TYPE_ID));
        long created = cursor.getLong(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ROUTE_CREATED_ALIAS));
        long modified = cursor.getLong(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ROUTE_MODIFIED_ALIAS));
        int mon = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_MON));
        int tue = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_TUE));
        int wed = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_WED));
        int thu = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_THU));
        int fri = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_FRI));
        int sat = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_SAT));
        int sun = cursor.getInt(cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_SUN));
        String organizationName = cursor.getString(
                cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ORGANIZATION_NAME_ALIAS));
        String fleetTypeName = cursor.getString(
                cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_FLEET_TYPE_NAME_ALIAS));
        String originName = cursor.getString(
                cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_ORIGIN_NAME_ALIAS));
        String destinationName = cursor.getString(
                cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_DESTINATION_NAME_ALIAS));
        int capacity = cursor.getInt(
                cursor.getColumnIndexOrThrow(RoutesEntry.COLUMN_FLEET_TYPE_CAPACITY_ALIAS));

        Route route = new Route(
                id,
                nodeKey,
                organizationKey,
                fleetTypeKey,
                name,
                originKey,
                destinationKey,
                departure,
                arrival,
                organizationId,
                fleetTypeId,
                (mon != 0),
                (tue != 0),
                (wed != 0),
                (thu != 0),
                (fri != 0),
                (sat != 0),
                (sun != 0),
                new Timestamp(created),
                new Timestamp(modified),
                status,
                origin,
                destination
        );

        route.setOrganizationName(organizationName);
        route.setFleetTypeName(fleetTypeName);
        route.setOriginName(originName);
        route.setDestinationName(destinationName);
        route.setFleetTypeCapacity(capacity);

        return route;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", nodeKey='" + nodeKey + '\'' +
                ", organizationKey='" + organizationKey + '\'' +
                ", fleetTypeKey='" + fleetTypeKey + '\'' +
                ", name='" + name + '\'' +
                ", originKey='" + originKey + '\'' +
                ", destinationKey='" + destinationKey + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", organizationId=" + organizationId +
                ", fleetTypeId=" + fleetTypeId +
                ", mon=" + mon +
                ", tue=" + tue +
                ", wed=" + wed +
                ", thu=" + thu +
                ", fri=" + fri +
                ", sat=" + sat +
                ", sun=" + sun +
                ", timestampCreated=" + timestampCreated +
                ", timestampLastChanged=" + timestampLastChanged +
                ", status='" + status + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", organizationName='" + organizationName + '\'' +
                ", originName='" + originName + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", fleetTypeName='" + fleetTypeName + '\'' +
                '}';
    }
}
