package com.lighteye.safiri.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesDataSource;

/**
 * Created by yonny on 7/18/16.
 */
public class OrganizationBranchesLocalDataSource implements OrganizationBranchesDataSource {

    private static OrganizationBranchesLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private OrganizationBranchesLocalDataSource(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }

    public static OrganizationBranchesLocalDataSource getInstance(@NonNull ContentResolver contentResolver){
        if(INSTANCE == null)
            INSTANCE = new OrganizationBranchesLocalDataSource(contentResolver);
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {

    }

    @Override
    public void getItems(@NonNull String organizationId,
                         @NonNull GetItemsCallback callback) {

    }
}
