package com.lighteye.safiri.data.source.entities.organizations;

import android.content.ContentValues;

import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/16/16.
 */
public class OrganizationValues {

    public static ContentValues form(Organization organization){
        long created = organization.getTimestampCreated().getTimestamp();
        long modified = organization.getTimestampCreated().getTimestamp();

        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.OrganizationsEntry._ID, organization.getId());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_NAME, organization.getName());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_TYPE, organization.getType());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_ADDRESS, organization.getAddress());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_CONTACTS, organization.getContacts());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_TOWN_KEY, organization.getTownKey());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_TOWN_ID, organization.getTownId());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_NODE_KEY, organization.getNodeKey());
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_CREATED, created);
        values.put(SafiriPersistenceContract.OrganizationsEntry.COLUMN_MODIFIED, modified);
        return values;
    }
}
