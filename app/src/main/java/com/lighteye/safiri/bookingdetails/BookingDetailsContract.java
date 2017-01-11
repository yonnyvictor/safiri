package com.lighteye.safiri.bookingdetails;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;
import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Route;

import java.util.ArrayList;

/**
 * Created by yonny on 7/15/16.
 */
public interface BookingDetailsContract {
    interface View extends BaseView<Presenter> {
        void showRoute(Route route, String travelDate);
        void showBookingsList();
        void showBookingsError();
        void showBooking(Booking booking);
        void showBookingDetails(Cursor details, boolean editable);
        void showBookingDetailForm(String organizationId, String fleetTypeId, String routeKey, String travelDate);
        void showBookingDetailsList();
        void validateNewBooking();
        void addBookingDetail(BookingDetail bookingDetail);
        void removeBookingDetail(BookingDetail bookingDetail);
        int countAdapterItems();
    }

    interface Presenter extends BasePresenter {
        void saveBooking();
        void createBooking(ArrayList<BookingDetail> bookingDetails);
        void cancelBooking();
        void populateBooking();
        void showBookingDetailUi();
        void addBookingDetail(BookingDetail bookingDetail);
        void removeBookingDetail(BookingDetail bookingDetail);
        void setBookingDetailsList(ArrayList<BookingDetail> bookingDetailsList);
        ArrayList<BookingDetail> getBookingDetailsList();
    }
}
