package com.lighteye.safiri.data.source.entities.organizations;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Organization;

import java.util.List;

/**
 * Created by yonny on 7/16/16.
 */
public interface OrganizationsDataSource {

    interface GetItemsCallback {
        void onItemsLoaded(List<Organization> items);
        void onDataNotAvailable();
    }

    interface GetItemCallback {
        void onItemLoaded(Organization item);
        void onDataNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String organizationId, @NonNull GetItemCallback callback);
}
