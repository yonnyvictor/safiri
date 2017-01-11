package com.lighteye.safiri.data;

import android.database.Cursor;

import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatCharge {
    private int id;
    private int routeId;
    private int seatTypeId;
    private String routeKey;
    private String nodeKey;
    private String seatTypeKey;
    private float charge;
    private Timestamp timestampCreated;
    private Timestamp timestampLastChanged;
    private boolean current;

    public SeatCharge() {
    }

    public SeatCharge(int id, int routeId, int seatTypeId, String routeKey, String nodeKey,
                      String seatTypeKey, float charge, Timestamp timestampCreated,
                      Timestamp timestampLastChanged, boolean current) {
        this.id = id;
        this.routeId = routeId;
        this.seatTypeId = seatTypeId;
        this.routeKey = routeKey;
        this.nodeKey = nodeKey;
        this.seatTypeKey = seatTypeKey;
        this.charge = charge;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
        this.current = current;
    }

    public static SeatCharge from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry._ID));
        float charge = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_CHARGE));
        int current = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_CURRENT));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_NODE_KEY));
        String routeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_ROUTE_KEY));
        int routeId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_ROUTE_ID));
        String seatTypeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_SEAT_TYPE_KEY));
        int seatTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_SEAT_TYPE_ID));
        long created = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_CREATED));
        long modified = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatChargesEntry.COLUMN_MODIFIED));


        SeatCharge seatCharge = new SeatCharge(
                id,
                routeId,
                seatTypeId,
                routeKey,
                nodeKey,
                seatTypeKey,
                charge,
                new Timestamp(created),
                new Timestamp(modified),
                (current != 0)
        );

        return seatCharge;
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

    public int getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(int seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getSeatTypeKey() {
        return seatTypeKey;
    }

    public void setSeatTypeKey(String seatTypeKey) {
        this.seatTypeKey = seatTypeKey;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
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

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "SeatCharge{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", seatTypeId=" + seatTypeId +
                ", routeKey='" + routeKey + '\'' +
                ", nodeKey='" + nodeKey + '\'' +
                ", seatTypeKey='" + seatTypeKey + '\'' +
                ", charge=" + charge +
                ", timestampCreated=" + timestampCreated +
                ", timestampLastChanged=" + timestampLastChanged +
                ", current=" + current +
                '}';
    }
}
