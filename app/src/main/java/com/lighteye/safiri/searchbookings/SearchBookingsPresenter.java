package com.lighteye.safiri.searchbookings;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.lighteye.safiri.data.Town;
import com.lighteye.safiri.data.source.entities.towns.TownsDataSource;
import com.lighteye.safiri.data.source.entities.towns.TownsLoader;
import com.lighteye.safiri.data.source.entities.towns.TownsRepository;
import com.lighteye.safiri.utils.Constants;

import java.util.List;

/**
 * Created by yonny on 7/25/16.
 */
public class SearchBookingsPresenter implements SearchBookingsContract.Presenter,
        TownsRepository.LoadDataCallback, TownsDataSource.GetItemsCallback,
        LoaderManager.LoaderCallbacks<Cursor> {

    private final SearchBookingsContract.View mSearchBookingsView;

    @NonNull
    private final TownsRepository mTownsRepository;

    @NonNull
    private final LoaderManager mLoaderManager;

    @NonNull
    private final TownsLoader mLoaderProvider;

    public SearchBookingsPresenter(SearchBookingsContract.View mSearchBookingsView,
                                   @NonNull TownsRepository mTownsRepository,
                                   @NonNull LoaderManager mLoaderManager,
                                   @NonNull TownsLoader mLoaderProvider) {
        this.mSearchBookingsView = mSearchBookingsView;
        this.mTownsRepository = mTownsRepository;
        this.mLoaderManager = mLoaderManager;
        this.mLoaderProvider = mLoaderProvider;
        this.mSearchBookingsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTowns();
    }

    @Override
    public void loadTowns() {
        mTownsRepository.getItems(this);
    }

    @Override
    public void onItemsLoaded(List<Town> items) {
        if(mLoaderManager.getLoader(Constants.TOWNS_LOADER) == null)
            mLoaderManager.initLoader(Constants.TOWNS_LOADER, null, this);
        else
            mLoaderManager.restartLoader(Constants.TOWNS_LOADER, null, this);
    }

    @Override
    public void onDataLoaded(Cursor data) {
        mSearchBookingsView.loadTowns(data);
    }

    @Override
    public void onDataEmpty() {

    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onDataReset() {

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

    }

}
