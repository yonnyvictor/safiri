package com.lighteye.safiri.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class Seat implements Parcelable {
    private int id;
    private int seatsConfigurationId;
    private int seatTypeId;
    private String seatsConfigurationKey;
    private String nodeKey;
    private String name;
    private int row;
    private int column;
    private String seatTypeKey;
    private String status;

    public Seat() {
    }

    public Seat(int id, int seatsConfigurationId, int seatTypeId, String seatsConfigurationKey,
                String nodeKey, String name, int row, int column, String seatTypeKey,
                String status) {
        this.id = id;
        this.seatsConfigurationId = seatsConfigurationId;
        this.seatTypeId = seatTypeId;
        this.seatsConfigurationKey = seatsConfigurationKey;
        this.nodeKey = nodeKey;
        this.name = name;
        this.row = row;
        this.column = column;
        this.seatTypeKey = seatTypeKey;
        this.status = status;
    }

    public Seat(Parcel parcel){
        id = parcel.readInt();
        seatsConfigurationId = parcel.readInt();
        seatTypeId = parcel.readInt();
        seatsConfigurationKey = parcel.readString();
        nodeKey = parcel.readString();
        name = parcel.readString();
        row = parcel.readInt();
        column = parcel.readInt();
        seatTypeKey = parcel.readString();
        status = parcel.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Seat createFromParcel(Parcel in){
            return new Seat(in);
        }

        public Seat[] newArray(int size){
            return new Seat[size];
        }
    };

    public static Seat from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry._ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_NAME));
        int row = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_ROW));
        int column = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_COLUMN));
        String status = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_STATUS));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_NODE_KEY));
        String seatsConfigurationKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEATS_CONFIGURATION_KEY));
        int seatsConfigurationId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEATS_CONFIGURATION_ID));
        String seatTypeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_TYPE_KEY));
        int seatTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_TYPE_ID));



        Seat seat = new Seat(
                id,
                seatsConfigurationId,
                seatTypeId,
                seatsConfigurationKey,
                nodeKey,
                name,
                row,
                column,
                seatTypeKey,
                status
        );

        return seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatsConfigurationId() {
        return seatsConfigurationId;
    }

    public void setSeatsConfigurationId(int seatsConfigurationId) {
        this.seatsConfigurationId = seatsConfigurationId;
    }

    public int getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(int seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public String getSeatsConfigurationKey() {
        return seatsConfigurationKey;
    }

    public void setSeatsConfigurationKey(String seatsConfigurationKey) {
        this.seatsConfigurationKey = seatsConfigurationKey;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getSeatTypeKey() {
        return seatTypeKey;
    }

    public void setSeatTypeKey(String seatTypeKey) {
        this.seatTypeKey = seatTypeKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatsConfigurationId=" + seatsConfigurationId +
                ", seatTypeId=" + seatTypeId +
                ", seatsConfigurationKey='" + seatsConfigurationKey + '\'' +
                ", nodeKey='" + nodeKey + '\'' +
                ", name='" + name + '\'' +
                ", row=" + row +
                ", column=" + column +
                ", seatTypeKey='" + seatTypeKey + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(seatsConfigurationId);
        parcel.writeInt(seatTypeId);
        parcel.writeString(seatsConfigurationKey);
        parcel.writeString(nodeKey);
        parcel.writeString(name);
        parcel.writeInt(row);
        parcel.writeInt(column);
        parcel.writeString(seatTypeKey);
        parcel.writeString(status);
    }
}
