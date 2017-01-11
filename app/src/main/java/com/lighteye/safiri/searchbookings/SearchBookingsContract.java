package com.lighteye.safiri.searchbookings;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;

/**
 * Created by yonny on 7/25/16.
 */
public interface SearchBookingsContract {
    interface View extends BaseView<Presenter> {
        void loadTowns(Cursor data);
        void showSearchForm();
        void showSearchFormError();
        void submitSearchDetails(String originId, String destinationId, String travelDate);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadTowns();
    }
}
