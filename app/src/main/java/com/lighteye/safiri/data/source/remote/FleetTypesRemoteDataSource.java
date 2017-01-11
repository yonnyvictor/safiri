package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.FleetType;
import com.lighteye.safiri.data.source.entities.fleettypes.FleetTypesDataSource;
import com.lighteye.safiri.data.source.remote.response.FleetTypesResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/20/16.
 */
public class FleetTypesRemoteDataSource extends BaseRemoteDataSource implements FleetTypesDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, FleetTypesResponse>> call = mSafiriService.getFleetTypes();
        call.enqueue(new Callback<Map<String, FleetTypesResponse>>() {
            @Override
            public void onResponse(Call<Map<String, FleetTypesResponse>> call,
                                   Response<Map<String, FleetTypesResponse>> response) {
                ArrayList<FleetType> items = new ArrayList<FleetType>();
                for (Map.Entry<String, FleetTypesResponse> entry : response.body().entrySet()) {
                    String nodeKey = entry.getKey();
                    FleetTypesResponse res = entry.getValue();
                    FleetType obj = new FleetType(0, nodeKey, res.getName(), res.getCapacity());
                    items.add(obj);
                }

                callback.onItemsLoaded(items);
            }

            @Override
            public void onFailure(Call<Map<String, FleetTypesResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
