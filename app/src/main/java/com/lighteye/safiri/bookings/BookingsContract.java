package com.lighteye.safiri.bookings;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;
import com.lighteye.safiri.data.Booking;

/**
 * Created by yonny on 7/15/16.
 */
public interface BookingsContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showBookings(Cursor routes);
        void showLoadingBookingsError();
        void showBookingDetailsUi(int bookingId, int routeId, String date, boolean editable);
        void showNoBookings();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadBookings();
        void openBookingDetailUi(Booking booking);
    }
}
