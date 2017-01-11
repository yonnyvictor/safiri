package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lighteye.safiri.data.SeatCharge;
import com.lighteye.safiri.data.Timestamp;
import com.lighteye.safiri.data.source.entities.seatcharges.SeatChargesDataSource;
import com.lighteye.safiri.data.source.remote.response.SeatChargesResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatChargesRemoteDataSource extends BaseRemoteDataSource implements SeatChargesDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, Map<String, SeatChargesResponse>>> call = mSafiriService.getSeatCharges();
        call.enqueue(new Callback<Map<String, Map<String, SeatChargesResponse>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, SeatChargesResponse>>> call,
                                   Response<Map<String, Map<String, SeatChargesResponse>>> response) {
                ArrayList<SeatCharge> items = new ArrayList<SeatCharge>();

                for (Map.Entry<String, Map<String, SeatChargesResponse>> entry : response.body().entrySet()) {
                    String routeKey = entry.getKey();
                    Map<String, SeatChargesResponse> childMap = entry.getValue();

                    for (Map.Entry<String, SeatChargesResponse> child : childMap.entrySet()) {
                        String nodeKey = child.getKey();
                        SeatChargesResponse res = child.getValue();
                        int id = 0;
                        int routeId = 0;
                        int seatTypeId = 0;
                        Timestamp created = new Timestamp(res.getTimestampCreated().getTimestamp());
                        Timestamp modified = new Timestamp(res.getTimestampLastChanged().getTimestamp());

                        SeatCharge obj = new SeatCharge(
                                id,
                                routeId,
                                seatTypeId,
                                routeKey,
                                nodeKey,
                                res.getSeatTypeKey(),
                                res.getCharge(),
                                created,
                                modified,
                                res.isCurrent()
                        );

                        items.add(obj);
                    }
                }

                callback.onSeatChargesLoaded(items);
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, SeatChargesResponse>>> call, Throwable t) {
                Log.e("SeatChargesRemote", t.getMessage());
            }
        });
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
