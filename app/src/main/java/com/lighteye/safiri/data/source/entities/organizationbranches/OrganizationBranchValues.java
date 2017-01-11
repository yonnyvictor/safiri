package com.lighteye.safiri.data.source.entities.organizationbranches;

import android.content.ContentValues;

import com.lighteye.safiri.data.OrganizationBranch;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/16/16.
 */
public class OrganizationBranchValues {

    public static ContentValues form(OrganizationBranch organizationBranch){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.OrganizationBranchesEntry._ID, organizationBranch.getId());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ORGANIZATION_ID, organizationBranch.getOrganizationId());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_TOWN_ID, organizationBranch.getTownId());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ADDRESS, organizationBranch.getAddress());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_CONTACTS, organizationBranch.getContacts());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ORGANIZATION_KEY, organizationBranch.getOrganizationKey());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_TOWN_KEY, organizationBranch.getTownKey());
        values.put(SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_NODE_KEY, organizationBranch.getNodeKey());
        return values;
    }
}
