package com.lighteye.safiri.data.source.entities.organizations;

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
public class OrganizationsLoader extends BaseLoader {

    public OrganizationsLoader(@NonNull Context context) {
       super(context);
    }

    public Loader<Cursor> createItemsLoader(){
        return new CursorLoader(mContext,
                SafiriPersistenceContract.OrganizationsEntry.CONTENT_URI,
                SafiriPersistenceContract.OrganizationsEntry.ORGANIZATIONS_COLUMNS,
                null,
                null,
                null
        );
    }

    public Loader<Cursor> createItemLoader(String organizationId){
        return new CursorLoader(mContext,
                SafiriPersistenceContract.OrganizationsEntry.buildOrganizationUri(Long.parseLong(organizationId)),
                SafiriPersistenceContract.OrganizationsEntry.ORGANIZATIONS_COLUMNS,
                SafiriPersistenceContract.OrganizationsEntry.TABLE_NAME + "." +
                        SafiriPersistenceContract.OrganizationsEntry._ID + " = ?",
                new String[]{String.valueOf(organizationId)},
                null
        );
    }
}
