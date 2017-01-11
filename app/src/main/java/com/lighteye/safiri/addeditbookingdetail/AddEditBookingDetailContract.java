package com.lighteye.safiri.addeditbookingdetail;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Seat;

/**
 * Created by yonny on 7/29/16.
 */
public interface AddEditBookingDetailContract {

    interface View extends BaseView<Presenter> {
        void showEmptySeatError();
        void showBookingDetailUi();
        void validateBookingDetail();
        void showSelectedSeat(Seat seat);
        void showBookingDetailUi(BookingDetail bookingDetail);
        void openSeatsUi(String organizationId, String fleetTypeId, String routeKey, String travelDate);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void showSeatsUi();
        void addSelectedSeat(Seat seat);
        void loadSeatCharge(String traveller);
        void loadBookingDetail(BookingDetail bookingDetail);
        void saveBookingDetail(BookingDetail bookingDetail);
    }
}
