package com.lighteye.safiri.data.source.entities.organizationbranches;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.OrganizationBranch;

import java.util.List;

/**
 * Created by yonny on 7/16/16.
 */
public interface OrganizationBranchesDataSource {

    interface GetItemsCallback {
        void onItemsLoaded(List<OrganizationBranch> items);
        void onDataNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItems(@NonNull String organizationId, @NonNull GetItemsCallback callback);
}
