package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.SeatType;
import com.lighteye.safiri.data.source.entities.seattypes.SeatTypesDataSource;
import com.lighteye.safiri.data.source.remote.response.SeatTypesResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatTypesRemoteDataSource extends BaseRemoteDataSource implements SeatTypesDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, SeatTypesResponse>> call = mSafiriService.getSeatTypes();
        call.enqueue(new Callback<Map<String, SeatTypesResponse>>() {
            @Override
            public void onResponse(Call<Map<String, SeatTypesResponse>> call,
                                   Response<Map<String, SeatTypesResponse>> response) {
                ArrayList<SeatType> items = new ArrayList<SeatType>();
                for (Map.Entry<String, SeatTypesResponse> entry : response.body().entrySet()) {
                    String nodeKey = entry.getKey();
                    SeatTypesResponse res = entry.getValue();
                    SeatType obj = new SeatType(0, res.getName(), nodeKey);
                    items.add(obj);
                }

                callback.onItemsLoaded(items);
            }

            @Override
            public void onFailure(Call<Map<String, SeatTypesResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
