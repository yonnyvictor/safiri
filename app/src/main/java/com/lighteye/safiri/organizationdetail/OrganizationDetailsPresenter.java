package com.lighteye.safiri.organizationdetail;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.data.OrganizationBranch;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesDataSource;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesLoader;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesRepository;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsDataSource;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsLoader;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsRepository;
import com.lighteye.safiri.utils.Constants;

import java.util.List;

/**
 * Created by yonny on 7/15/16.
 */
public class OrganizationDetailsPresenter implements OrganizationDetailsContract.Presenter,
        LoaderManager.LoaderCallbacks<Cursor>, OrganizationsDataSource.GetItemCallback,
        OrganizationBranchesRepository.LoadDataCallback,
        OrganizationBranchesDataSource.GetItemsCallback {

    @NonNull
    private final OrganizationsRepository mOrganizationsRepository;

    @NonNull
    private final OrganizationBranchesRepository mOrganizationBranchesRepository;

    private OrganizationDetailsContract.View mOrganizationDetailsView;
    private OrganizationsLoader mOrganizationsLoader;
    private OrganizationBranchesLoader mOrganizationBranchesLoader;
    private LoaderManager mLoaderManager;
    private Organization mOrganization;
    private String mOrganizationId;

    public OrganizationDetailsPresenter(@NonNull OrganizationsRepository mOrganizationsRepository,
                                        @NonNull OrganizationBranchesRepository
                                                mOrganizationBranchesRepository,
                                        OrganizationDetailsContract.View mOrganizationDetailsView,
                                        OrganizationsLoader mOrganizationsLoader,
                                        OrganizationBranchesLoader mOrganizationBranchesLoader,
                                        LoaderManager mLoaderManager, String organizationId) {
        this.mOrganizationsRepository = mOrganizationsRepository;
        this.mOrganizationBranchesRepository = mOrganizationBranchesRepository;
        this.mOrganizationDetailsView = mOrganizationDetailsView;
        this.mOrganizationsLoader = mOrganizationsLoader;
        this.mOrganizationBranchesLoader = mOrganizationBranchesLoader;
        this.mLoaderManager = mLoaderManager;
        this.mOrganizationId = organizationId;

        mOrganizationDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadOrganization();
        loadOrganizationBranches(mOrganizationId);
    }

    @Override
    public void onItemLoaded(Organization item) {
        mLoaderManager.initLoader(Constants.ORGANIZATION_LOADER, null, this);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constants.ORGANIZATION_LOADER:
                return mOrganizationsLoader.createItemLoader(mOrganizationId);
            case Constants.ORGANIZATION_BRANCHES_LOADER:
                return mOrganizationBranchesLoader.createItemsLoader(mOrganizationId);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if(data.moveToLast()){
                switch (loader.getId()){
                    case Constants.ORGANIZATION_LOADER:
                        showOrganization(data);
                        break;
                    case Constants.ORGANIZATION_BRANCHES_LOADER:
                        onDataLoaded(data);
                        break;
                }
            }

        }else{

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void loadOrganizationBranches(String organizationId) {
        mOrganizationDetailsView.setLoadingOrganizationBranchesIndicator(true);
        mOrganizationBranchesRepository.getItems(organizationId, this);
    }


    @Override
    public void onItemsLoaded(List<OrganizationBranch> items) {
        if(mLoaderManager.getLoader(Constants.ORGANIZATION_BRANCHES_LOADER) == null)
            mLoaderManager.initLoader(Constants.ORGANIZATION_BRANCHES_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.ORGANIZATION_BRANCHES_LOADER, null, this);
    }

    @Override
    public void onDataLoaded(Cursor data) {
        mOrganizationDetailsView.setLoadingOrganizationBranchesIndicator(false);
        mOrganizationDetailsView.showOrganizationBranches(data);
    }

    @Override
    public void onDataEmpty() {

    }

    @Override
    public void onDataReset() {

    }


    private void loadOrganization(){
        mOrganizationDetailsView.setLoadingOrganizationIndicator(true);
        mOrganizationsRepository.getItem(mOrganizationId, this);
    }

    private void showOrganization(Cursor data){
        mOrganizationDetailsView.showOrganization(Organization.from(data));
    }
}
