package com.lighteye.safiri.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingDetail implements Parcelable {
    private int id;
    private int bookingId;
    private int seatChargeId;
    private int seatId;
    private String seatKey;
    private String seatChargeKey;
    private String traveller;
    private String seatName;
    private float seatCharge;
    private String nodeKey;
    private String status;
    private long timestampCreated;
    private long timestampLastChanged;


    public BookingDetail() {
    }

    public BookingDetail(int id, int bookingId, int seatChargeId, int seatId, String seatKey,
                         String seatChargeKey, String traveller, String seatName, float seatCharge,
                         String nodeKey, String status, long timestampCreated,
                         long timestampLastChanged) {
        this.id = id;
        this.bookingId = bookingId;
        this.seatChargeId = seatChargeId;
        this.seatId = seatId;
        this.seatKey = seatKey;
        this.seatChargeKey = seatChargeKey;
        this.traveller = traveller;
        this.seatName = seatName;
        this.seatCharge = seatCharge;
        this.nodeKey = nodeKey;
        this.status = status;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
    }

    public BookingDetail(Parcel parcel){
        id = parcel.readInt();
        bookingId = parcel.readInt();
        seatChargeId = parcel.readInt();
        seatId = parcel.readInt();
        seatKey = parcel.readString();
        seatChargeKey = parcel.readString();
        traveller = parcel.readString();
        seatName = parcel.readString();
        seatCharge = parcel.readFloat();
        nodeKey = parcel.readString();
        status = parcel.readString();
        timestampCreated = parcel.readLong();
        timestampLastChanged = parcel.readLong();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public BookingDetail createFromParcel(Parcel in){
            return new BookingDetail(in);
        }

        public BookingDetail[] newArray(int size){
            return new BookingDetail[size];
        }
    };

    public static BookingDetail from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(SafiriPersistenceContract.BookingDetailsEntry._ID));
        int bookingId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_ID));
        int seatChargeId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_CHARGE_ID));
        int seatId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_ID));
        String seatKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_KEY));
        String seatChargeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_CHARGE_KEY));
        String traveller = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_TRAVELLER));
        String seatName = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_NAME_ALIAS));
        float seatCharge = cursor.getFloat(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_SEAT_CHARGE_ALIAS));
        long created = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_DETAIL_CREATED_ALIAS));
        long modified = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_DETAIL_MODIFIED_ALIAS));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_DETAIL_NODE_KEY_ALIAS));
        String status = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.BookingDetailsEntry.COLUMN_BOOKING_DETAIL_STATUS_ALIAS));

        BookingDetail bookingDetail = new BookingDetail(
                id,
                bookingId,
                seatChargeId,
                seatId,
                seatKey,
                seatChargeKey,
                traveller,
                seatName,
                seatCharge,
                nodeKey,
                status,
                created,
                modified
        );

        return bookingDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getSeatChargeId() {
        return seatChargeId;
    }

    public void setSeatChargeId(int seatChargeId) {
        this.seatChargeId = seatChargeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getSeatKey() {
        return seatKey;
    }

    public void setSeatKey(String seatKey) {
        this.seatKey = seatKey;
    }

    public String getSeatChargeKey() {
        return seatChargeKey;
    }

    public void setSeatChargeKey(String seatChargeKey) {
        this.seatChargeKey = seatChargeKey;
    }

    public String getTraveller() {
        return traveller;
    }

    public void setTraveller(String traveller) {
        this.traveller = traveller;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public float getSeatCharge() {
        return seatCharge;
    }

    public void setSeatCharge(float seatCharge) {
        this.seatCharge = seatCharge;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(long timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public long getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(long timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", seatChargeId=" + seatChargeId +
                ", seatId=" + seatId +
                ", seatKey='" + seatKey + '\'' +
                ", seatChargeKey='" + seatChargeKey + '\'' +
                ", traveller='" + traveller + '\'' +
                ", seatName='" + seatName + '\'' +
                ", seatCharge=" + seatCharge +
                ", nodeKey='" + nodeKey + '\'' +
                ", status='" + status + '\'' +
                ", timestampCreated=" + timestampCreated +
                ", timestampLastChanged=" + timestampLastChanged +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(bookingId);
        parcel.writeInt(seatChargeId);
        parcel.writeInt(seatId);
        parcel.writeString(seatKey);
        parcel.writeString(seatChargeKey);
        parcel.writeString(traveller);
        parcel.writeString(seatName);
        parcel.writeFloat(seatCharge);
        parcel.writeString(nodeKey);
        parcel.writeString(status);
        parcel.writeLong(timestampCreated);
        parcel.writeLong(timestampLastChanged);
    }
}
