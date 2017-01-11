package com.lighteye.safiri.data.source.entities.organizationbranches;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.source.BaseLoader;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/16/16.
 */
public class OrganizationBranchesLoader extends BaseLoader {

    public OrganizationBranchesLoader(@NonNull Context context) {
        super(context);
    }

    public Loader<Cursor> createItemsLoader(){
        return new CursorLoader(mContext,
                SafiriPersistenceContract.OrganizationBranchesEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    public Loader<Cursor> createItemLoader(String organizationId){
        return new CursorLoader(mContext,
                SafiriPersistenceContract.OrganizationBranchesEntry.CONTENT_URI,
                null,
                null,
                new String[]{String.valueOf(organizationId)},
                null
        );
    }

    public Loader<Cursor> createItemsLoader(String organizationId){
        return new CursorLoader(mContext,
                SafiriPersistenceContract.OrganizationBranchesEntry
                        .buildOrganizationBranchesWithOrganizationUri(organizationId),
                SafiriPersistenceContract.OrganizationBranchesEntry.ORGANIZATION_BRANCHES_COLUMNS,
                SafiriPersistenceContract.OrganizationBranchesEntry.COLUMN_ORGANIZATION_ID + " = ?",
                new String[]{String.valueOf(organizationId)},
                null
        );
    }
}
