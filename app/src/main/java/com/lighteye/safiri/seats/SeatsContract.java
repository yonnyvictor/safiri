package com.lighteye.safiri.seats;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;
import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.SeatsConfiguration;
import com.lighteye.safiri.data.source.remote.response.BookingsResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by yonny on 7/27/16.
 */
public interface SeatsContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showAddEditSeatUi(Seat seat);
        void showSeats(Cursor details, SeatsConfiguration seatsConfiguration);
    }

    interface Presenter extends BasePresenter {
        void loadSeatsConfiguration();
        void loadSeats();
        void selectedSeat(Seat seat);
        Observable<Map<String, BookingsResponse>> getSeatBooking(String seatKey);
    }
}
