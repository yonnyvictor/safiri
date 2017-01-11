package com.lighteye.safiri.routes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.source.entities.routes.RoutesDataSource;
import com.lighteye.safiri.data.source.entities.routes.RoutesLoader;
import com.lighteye.safiri.data.source.entities.routes.RoutesRepository;
import com.lighteye.safiri.utils.Constants;

import java.util.List;

/**
 * Created by yonny on 7/15/16.
 */
public class RoutesPresenter implements RoutesContract.Presenter,
        RoutesRepository.LoadDataCallback,
        RoutesDataSource.GetItemsCallback,
        RoutesDataSource.SearchItemsCallback,
        LoaderManager.LoaderCallbacks<Cursor>  {

    private final boolean mToday;
    private final boolean mSearch;

    private final RoutesContract.View mRoutesView;

    @NonNull
    private final RoutesRepository mRoutesRepository;

    @NonNull
    private final LoaderManager mLoaderManager;

    @NonNull
    private final RoutesLoader mLoaderProvider;

    private final String mDate;

    private final String mOriginId;

    private final String mDestinationId;


    public RoutesPresenter(RoutesContract.View routesView,
                                  @NonNull RoutesRepository routesRepository,
                                  @NonNull LoaderManager loaderManager,
                                  @NonNull RoutesLoader loaderProvider,
                           String date) {
        this.mRoutesView = routesView;
        this.mRoutesRepository = routesRepository;
        this.mLoaderManager = loaderManager;
        this.mLoaderProvider = loaderProvider;
        this.mDate = date;
        this.mOriginId = null;
        this.mDestinationId = null;
        mToday = true;
        mSearch = false;

        this.mRoutesView.setDate(mDate);
        this.mRoutesView.setPresenter(this);
    }

    public RoutesPresenter(RoutesContract.View routesView,
                           @NonNull RoutesRepository routesRepository,
                           @NonNull LoaderManager loaderManager,
                           @NonNull RoutesLoader loaderProvider,
                           String date,
                           String originId,
                           String destinationId) {
        this.mRoutesView = routesView;
        this.mRoutesRepository = routesRepository;
        this.mLoaderManager = loaderManager;
        this.mLoaderProvider = loaderProvider;
        this.mDate = date;
        this.mOriginId = originId;
        this.mDestinationId = destinationId;

        mToday = false;
        mSearch = true;

        this.mRoutesView.setDate(mDate);
        this.mRoutesView.setPresenter(this);
    }

    @Override
    public void start() {
        if(mToday)
            loadRoutes();
        if(mSearch)
            searchRoutes();
    }

    @Override
    public void onRoutesLoaded(List<Route> items) {
        if(mLoaderManager.getLoader(Constants.ROUTES_DAY_LOADER) == null)
            mLoaderManager.initLoader(Constants.ROUTES_DAY_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.ROUTES_DAY_LOADER, null, this);
    }

    @Override
    public void onRoutesDataLoaded(Cursor data) {
        mRoutesView.setLoadingIndicator(false);
        mRoutesView.showRoutes(data);
    }

    @Override
    public void onRoutesDataEmpty() {

    }

    @Override
    public void onRoutesNotAvailable() {
        mRoutesView.setLoadingIndicator(false);
        mRoutesView.showLoadingRoutesError();
    }

    @Override
    public void onRoutesDataReset() {
        mRoutesView.showRoutes(null);
    }

    @Override
    public void onSearchRoutesDataLoaded(Cursor data) {
        mRoutesView.setLoadingIndicator(false);
        mRoutesView.showRoutes(data);
    }

    @Override
    public void onSearchRoutesDataEmpty() {
        mRoutesView.setLoadingIndicator(false);
        //mRoutesView.showLoadingRoutesError();
    }

    @Override
    public void onSearchRoutesDataReset() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constants.ROUTES_DAY_LOADER:
                return mLoaderProvider.dayItemsLoader(mDate);
            case Constants.ROUTES_SEARCH_LOADER:
                return mLoaderProvider.searchItemsLoader(mOriginId, mDestinationId, mDate);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){

            switch (loader.getId()){
                case Constants.ROUTES_DAY_LOADER:
                    if(data.moveToLast())
                        onRoutesDataLoaded(data);
                    else
                        onRoutesDataEmpty();
                    break;
                case Constants.ROUTES_SEARCH_LOADER:
                    if(data.moveToLast())
                        onSearchRoutesDataLoaded(data);
                    else
                        onSearchRoutesDataEmpty();
                    break;
            }
        }else{
            onRoutesNotAvailable();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        onRoutesDataReset();
    }

    @Override
    public void loadRoutes() {
        mRoutesView.setLoadingIndicator(true);
        mRoutesRepository.getItems(this);
    }

    @Override
    public void searchRoutes() {
        mRoutesView.setLoadingIndicator(true);
        mRoutesRepository.searchItems(mOriginId, mDestinationId, mDate, this);
    }

    @Override
    public void openBookingUi(int routeId) {
        mRoutesView.showBookingsUi(routeId, mDate);
    }

    @Override
    public void onRoutesDataNotAvailable() {
        mRoutesView.setLoadingIndicator(false);
    }

    @Override
    public void onSearchRoutesLoaded(List<Route> items) {
        if(mLoaderManager.getLoader(Constants.ROUTES_SEARCH_LOADER) == null)
            mLoaderManager.initLoader(Constants.ROUTES_SEARCH_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.ROUTES_SEARCH_LOADER, null, this);
    }

    @Override
    public void onSearchRoutesNotAvailable() {
        mRoutesView.setLoadingIndicator(false);
    }
}
