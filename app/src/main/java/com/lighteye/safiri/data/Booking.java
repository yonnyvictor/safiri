package com.lighteye.safiri.data;

import android.database.Cursor;

import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class Booking {
    private int id;
    private int routeId;
    private String routeKey;
    private String userId;
    private long travelDate;
    private String status;
    private String organizationName;
    private String originName;
    private String destinationName;
    private float totalCharge;


    public Booking() {
    }

    public Booking(int id, int routeId, String routeKey, String userId, long travelDate, String status) {
        this.id = id;
        this.routeId = routeId;
        this.routeKey = routeKey;
        this.userId = userId;
        this.travelDate = travelDate;
        this.status = status;
    }

    public static Booking from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(SafiriPersistenceContract.BookingsEntry._ID));
        String routeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingsEntry.COLUMN_ROUTE_NODE_KEY_ALIAS));
        String userId = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingsEntry.COLUMN_USER_KEY));
        int routeId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingsEntry.COLUMN_ROUTE_ID_ALIAS));
        long travelDate = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingsEntry.COLUMN_TRAVEL_DATE));
        String organizationName = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.RoutesEntry.COLUMN_ORGANIZATION_NAME_ALIAS));
        String originName = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.RoutesEntry.COLUMN_ORIGIN_NAME_ALIAS));
        String destinationName = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.RoutesEntry.COLUMN_DESTINATION_NAME_ALIAS));
        float totalCharge = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingsEntry.COLUMN_TOTAL_CHARGE_ALIAS));
        String status = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingsEntry.COLUMN_BOOKING_STATUS_ALIAS));

        Booking booking = new Booking(
                id,
                routeId,
                routeKey,
                userId,
                travelDate,
                status
        );

        booking.setOrganizationName(organizationName);
        booking.setOriginName(originName);
        booking.setDestinationName(destinationName);
        booking.setTotalCharge(totalCharge);

        return booking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(long travelDate) {
        this.travelDate = travelDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public float getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(float totalCharge) {
        this.totalCharge = totalCharge;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", routeKey='" + routeKey + '\'' +
                ", userId='" + userId + '\'' +
                ", travelDate=" + travelDate +
                ", status='" + status + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", originName='" + originName + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", totalCharge=" + totalCharge +
                '}';
    }
}
