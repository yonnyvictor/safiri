package com.lighteye.safiri.data.source.entities.organizations;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/16/16.
 */
public class OrganizationsRepository implements OrganizationsDataSource{

    private static OrganizationsRepository INSTANCE = null;

    private final OrganizationsDataSource mOrganizationsLocalDataSource;

    private OrganizationsRepository(OrganizationsDataSource organizationsLocalDataSource) {
        this.mOrganizationsLocalDataSource = organizationsLocalDataSource;
    }

    public static OrganizationsRepository getInstance(OrganizationsDataSource organizationsLocalDataSource){
       if(INSTANCE == null)
           INSTANCE = new OrganizationsRepository(organizationsLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        callback.onItemsLoaded(null);
    }

    @Override
    public void getItem(@NonNull String organizationId, @NonNull GetItemCallback callback) {
        callback.onItemLoaded(null);
    }

    public interface LoadDataCallback{
        void onDataLoaded(Cursor data);
        void onDataEmpty();
        void onDataNotAvailable();
        void onDataReset();
    }
}
