package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.SeatsConfiguration;
import com.lighteye.safiri.data.source.entities.seatsconfiguration.SeatsConfigurationDataSource;
import com.lighteye.safiri.data.source.remote.response.SeatsConfigurationResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsConfigurationRemoteDataSource extends BaseRemoteDataSource
        implements SeatsConfigurationDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, Map<String, SeatsConfigurationResponse>>> call = mSafiriService.getSeatsConfiguration();
        call.enqueue(new Callback<Map<String, Map<String, SeatsConfigurationResponse>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, SeatsConfigurationResponse>>> call,
                                   Response<Map<String, Map<String, SeatsConfigurationResponse>>> response) {
                ArrayList<SeatsConfiguration> items = new ArrayList<SeatsConfiguration>();

                for (Map.Entry<String, Map<String, SeatsConfigurationResponse>> entry : response.body().entrySet()) {
                    String organizationKey = entry.getKey();
                    Map<String, SeatsConfigurationResponse> childMap = entry.getValue();

                    for (Map.Entry<String, SeatsConfigurationResponse> child : childMap.entrySet()) {
                        String nodeKey = child.getKey();
                        SeatsConfigurationResponse res = child.getValue();
                        int id = 0;
                        int organizationId = 0;
                        int fleetTypeId = 0;
                        int doorRow = 2;
                        SeatsConfiguration obj = new SeatsConfiguration(
                                id,
                                organizationId,
                                fleetTypeId,
                                organizationKey,
                                nodeKey,
                                res.getFleetTypeKey(),
                                res.getRows(),
                                res.getColumns(),
                                res.getDriverRowSeats(),
                                res.getDoorRowSeats(),
                                res.getLastRowSeats(),
                                res.getPathColumn(),
                                doorRow
                        );

                        items.add(obj);
                    }
                }

                callback.onSeatsConfigurationsLoaded(items);
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, SeatsConfigurationResponse>>> call,
                                  Throwable t) {

            }
        });
    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }
}
