package com.lighteye.safiri.data.source.entities.seatcharges;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.SeatCharge;

import java.util.List;

/**
 * Created by yonny on 7/20/16.
 */
public interface SeatChargesDataSource {
    interface GetItemsCallback{
        void onSeatChargesLoaded(List<SeatCharge> items);
        void onSeatChargesNotAvailable();
    }

    interface GetItemCallback{
        void onSeatChargeLoaded(SeatCharge item);
        void onSeatChargeNotAvailable();
    }

    void getItems(@NonNull GetItemsCallback callback);
    void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback);
}
