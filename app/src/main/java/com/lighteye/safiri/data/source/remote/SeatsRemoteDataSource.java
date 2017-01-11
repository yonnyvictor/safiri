package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.source.entities.seats.SeatsDataSource;
import com.lighteye.safiri.data.source.remote.response.SeatsResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsRemoteDataSource extends BaseRemoteDataSource implements SeatsDataSource{

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, Map<String, SeatsResponse>>> call = mSafiriService.getSeats();
        call.enqueue(new Callback<Map<String, Map<String, SeatsResponse>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, SeatsResponse>>> call,
                                   Response<Map<String, Map<String, SeatsResponse>>> response) {
                ArrayList<Seat> items = new ArrayList<Seat>();

                for (Map.Entry<String, Map<String, SeatsResponse>> entry : response.body().entrySet()) {
                    String seatConfigurationKey = entry.getKey();
                    Map<String, SeatsResponse> childMap = entry.getValue();

                    for (Map.Entry<String, SeatsResponse> child : childMap.entrySet()) {
                        String nodeKey = child.getKey();
                        SeatsResponse res = child.getValue();

                        Seat obj = new Seat(
                                0,
                                0,
                                0,
                                seatConfigurationKey,
                                nodeKey,
                                res.getName(),
                                res.getRow(),
                                res.getColumn(),
                                res.getSeatTypeKey(),
                                res.getStatus()
                        );

                        items.add(obj);
                    }
                }

                callback.onSeatsLoaded(items);
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, SeatsResponse>>> call, Throwable t) {
                Log.e("SeatsRemote", t.getMessage());
            }
        });
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
