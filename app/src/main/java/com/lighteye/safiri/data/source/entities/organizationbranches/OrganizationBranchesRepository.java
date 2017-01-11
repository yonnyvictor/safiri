package com.lighteye.safiri.data.source.entities.organizationbranches;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/16/16.
 */
public class OrganizationBranchesRepository implements OrganizationBranchesDataSource {

    private static OrganizationBranchesRepository INSTANCE = null;

    private final OrganizationBranchesDataSource mOrganizationBranchesLocalDataSource;

    private OrganizationBranchesRepository(OrganizationBranchesDataSource organizationBranchesLocalDataSource) {
        this.mOrganizationBranchesLocalDataSource = organizationBranchesLocalDataSource;
    }

    public static OrganizationBranchesRepository getInstance(
            OrganizationBranchesDataSource organizationBranchesLocalDataSource){
        if(INSTANCE == null)
            INSTANCE = new OrganizationBranchesRepository(organizationBranchesLocalDataSource);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getItems(@NonNull GetItemsCallback callback) {
        callback.onItemsLoaded(null);
    }

    @Override
    public void getItems(@NonNull String organizationId,
                         @NonNull GetItemsCallback callback) {
        callback.onItemsLoaded(null);
    }

    public interface LoadDataCallback{
        void onDataLoaded(Cursor data);
        void onDataEmpty();
        void onDataNotAvailable();
        void onDataReset();
    }

}
