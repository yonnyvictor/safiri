package com.lighteye.safiri.routes;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;

/**
 * Created by yonny on 7/15/16.
 */
public interface RoutesContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showRoutes(Cursor routes);
        void showLoadingRoutesError();
        void showBookingsUi(int routeId, String date);
        void setDate(String date);
        void showNoRoutes();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void searchRoutes();
        void loadRoutes();
        void openBookingUi(int routeId);
    }
}
