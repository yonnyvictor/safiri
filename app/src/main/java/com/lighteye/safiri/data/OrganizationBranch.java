package com.lighteye.safiri.data;

import android.database.Cursor;

import com.google.firebase.database.Exclude;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/14/16.
 */
public class OrganizationBranch {
    private int id;
    private String nodeKey;
    private String contacts;
    private String address;
    private String organizationKey;
    private String townKey;
    private String town;
    private int organizationId;
    private int townId;

    public OrganizationBranch() {
    }

    public OrganizationBranch(int id, String nodeKey, String contacts, String address,
                              String organizationKey, String townKey, int organizationId,
                              int townId, String town) {
        this.id = id;
        this.nodeKey = nodeKey;
        this.contacts = contacts;
        this.address = address;
        this.organizationKey = organizationKey;
        this.townKey = townKey;
        this.organizationId = organizationId;
        this.townId = townId;
        this.town = town;
    }

    public static OrganizationBranch from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry._ID));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ADDRESS));
        String contacts = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_CONTACTS));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ORGANIZATION_BRANCH_NODE_KEY_ALIAS));
        String organizationKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ORGANIZATION_KEY));
        String townKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_TOWN_KEY));
        int organizationId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ORGANIZATION_ID));
        int townId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_TOWN_ID));
        String town = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.OrganizationsEntry.COLUMN_TOWN_NAME_ALIAS));

        OrganizationBranch branch = new OrganizationBranch(
                id,
                nodeKey,
                contacts,
                address,
                organizationKey,
                townKey,
                organizationId,
                townId,
                town
        );

        return branch;
    }

    @Exclude
    public int getId() {
        return id;
    }

    @Exclude
    public String getNodeKey() {
        return nodeKey;
    }

    public String getContacts() {
        return contacts;
    }

    public String getAddress() {
        return address;
    }

    @Exclude
    public String getOrganizationKey() {
        return organizationKey;
    }

    public String getTownKey() {
        return townKey;
    }

    @Exclude
    public int getOrganizationId() {
        return organizationId;
    }

    @Exclude
    public int getTownId() {
        return townId;
    }

    @Exclude
    public void setId(int id) {
        this.id = id;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public void setTownKey(String townKey) {
        this.townKey = townKey;
    }

    @Exclude
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Exclude
    public void setTownId(int townId) {
        this.townId = townId;
    }

    @Exclude
    public String getTown() {
        return town;
    }

    @Exclude
    public void setTown(String town) {
        this.town = town;
    }

}
