package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.source.entities.organizations.OrganizationsDataSource;

/**
 * Created by yonny on 7/18/16.
 */
public class OrganizationsLocalDataSource implements OrganizationsDataSource {

    private static OrganizationsLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private OrganizationsLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static OrganizationsLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new OrganizationsLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        //data will be loaded via Cursor Loader
    }

    @Override
    public void getItem(@NonNull String organizationId, @NonNull GetItemCallback callback) {
        //data will be loaded via Cursor Loader
    }
}
