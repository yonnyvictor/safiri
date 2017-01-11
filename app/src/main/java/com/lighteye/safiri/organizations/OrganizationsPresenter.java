package com.lighteye.safiri.organizations;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsDataSource;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsLoader;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsRepository;
import com.lighteye.safiri.utils.Constants;

import java.util.List;

/**
 * Created by yonny on 7/15/16.
 */
public class OrganizationsPresenter implements OrganizationsContract.Presenter,
        OrganizationsRepository.LoadDataCallback, OrganizationsDataSource.GetItemsCallback,
        LoaderManager.LoaderCallbacks<Cursor> {

    private final OrganizationsContract.View mOrganizationsView;

    @NonNull
    private final OrganizationsRepository mOrganizationsRepository;

    @NonNull
    private final LoaderManager mLoaderManager;

    @NonNull
    private final OrganizationsLoader mLoaderProvider;


    public OrganizationsPresenter(OrganizationsContract.View organizationsView,
                                  @NonNull OrganizationsRepository organizationsRepository,
                                  @NonNull LoaderManager loaderManager,
                                  @NonNull OrganizationsLoader loaderProvider) {
        this.mOrganizationsView = organizationsView;
        this.mOrganizationsRepository = organizationsRepository;
        this.mLoaderManager = loaderManager;
        this.mLoaderProvider = loaderProvider;
        this.mOrganizationsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadOrganizations();
    }

    @Override
    public void loadOrganizations() {
        mOrganizationsView.setLoadingIndicator(true);
        mOrganizationsRepository.getItems(this);
    }

    @Override
    public void openOrganizationDetails(int requestedOrganizationId) {
        mOrganizationsView.showOrganizationDetailsUi(requestedOrganizationId);
    }

    @Override
    public void onItemsLoaded(List<Organization> items) {
        if(mLoaderManager.getLoader(Constants.ORGANIZATIONS_LOADER) == null)
            mLoaderManager.initLoader(Constants.ORGANIZATIONS_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.ORGANIZATIONS_LOADER, null, this);
    }

    @Override
    public void onDataLoaded(Cursor data) {
        mOrganizationsView.setLoadingIndicator(false);
        mOrganizationsView.showOrganizations(data);
    }

    @Override
    public void onDataEmpty() {

    }

    @Override
    public void onDataNotAvailable() {
        mOrganizationsView.setLoadingIndicator(false);
        mOrganizationsView.showLoadingOrganizationsError();
    }

    @Override
    public void onDataReset() {
        mOrganizationsView.showOrganizations(null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mLoaderProvider.createItemsLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            if(data.moveToLast())
                onDataLoaded(data);
            else
                onDataEmpty();
        }else{
            onDataNotAvailable();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        onDataReset();
    }


}
