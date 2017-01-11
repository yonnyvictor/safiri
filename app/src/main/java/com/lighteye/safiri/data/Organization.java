package com.lighteye.safiri.data;

import android.database.Cursor;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

import java.util.Map;

/**
 * Created by yonny on 7/14/16.
 */
public class Organization {

    private int id;
    private String nodeKey;
    private String name;
    private String type;
    private String address;
    private String contacts;
    private int townId;
    private String townKey;
    private Timestamp timestampCreated;
    private Timestamp timestampLastChanged;
    private String town;

    public Organization() {
    }

    public Organization(int id, String nodeKey, String name, String type, String address,
                        String contacts, int townId, String townKey, Timestamp timestampCreated,
                        Timestamp timestampLastChanged) {
        this.id = id;
        this.nodeKey = nodeKey;
        this.name = name;
        this.type = type;
        this.address = address;
        this.contacts = contacts;
        this.townId = townId;
        this.townKey = townKey;
        this.timestampCreated = timestampCreated;

        if(timestampLastChanged != null){
            this.timestampLastChanged = timestampLastChanged;;
        }else{
            Map<String, String> timestamp = ServerValue.TIMESTAMP;
            String now = ServerValue.TIMESTAMP.get("timestamp");
            timestampLastChanged = new Timestamp(Long.parseLong(now));
        }
    }

    public static Organization from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry._ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_ORGANIZATION_NAME_ALIAS));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_TYPE));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_ADDRESS));
        String contacts = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_CONTACTS));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_ORGANIZATION_NODE_KEY_ALIAS));
        String townKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_TOWN_KEY));
        int townId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_TOWN_ID));
        long created = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_CREATED));
        long modified = cursor.getLong(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_MODIFIED));
        String town = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_TOWN_NAME_ALIAS));


        Organization organization = new Organization(
                id,
                nodeKey,
                name,
                type,
                address,
                contacts,
                townId,
                townKey,
                new Timestamp(created),
                new Timestamp(modified)
        );

        organization.setTown(town);

        return organization;
    }

    @Exclude
    public int getId() {
        return id;
    }

    @Exclude
    public String getNodeKey() {
        return nodeKey;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getContacts() {
        return contacts;
    }

    @Exclude
    public void setId(int id) {
        this.id = id;
    }

    @Exclude
    public int getTownId() {
        return townId;
    }

    public String getTownKey() {
        return townKey;
    }

    public Timestamp getTimestampCreated() {
        return timestampCreated;
    }

    public Timestamp getTimestampLastChanged() {
        return timestampLastChanged;
    }

    @Exclude
    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Exclude
    public void setTownId(int townId) {
        this.townId = townId;
    }

    public void setTownKey(String townKey) {
        this.townKey = townKey;
    }

    public void setTimestampCreated(Timestamp timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public void setTimestampLastChanged(Timestamp timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", nodeKey='" + nodeKey + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", contacts='" + contacts + '\'' +
                ", townId=" + townId +
                ", townKey='" + townKey + '\'' +
                ", timestampCreated=" + timestampCreated +
                ", timestampLastChanged=" + timestampLastChanged +
                '}';
    }
}
