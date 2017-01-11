package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.data.Timestamp;
import com.lighteye.safiri.data.source.entities.routes.RoutesDataSource;
import com.lighteye.safiri.data.source.remote.response.RoutesResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/20/16.
 */
public class RoutesRemoteDataSource extends BaseRemoteDataSource implements RoutesDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, Map<String, RoutesResponse>>> call = mSafiriService.getRoutes();
        call.enqueue(new Callback<Map<String, Map<String, RoutesResponse>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, RoutesResponse>>> call,
                                   Response<Map<String, Map<String, RoutesResponse>>> response) {
                ArrayList<Route> items = new ArrayList<Route>();

                for (Map.Entry<String, Map<String, RoutesResponse>> entry : response.body().entrySet()) {
                    String organizationKey = entry.getKey();
                    Map<String, RoutesResponse> childMap = entry.getValue();

                    for (Map.Entry<String, RoutesResponse> child : childMap.entrySet()) {
                        String nodeKey = child.getKey();
                        RoutesResponse res = child.getValue();
                        int id = 0;
                        int organizationId = 0;
                        int fleetTypeId = 0;
                        int origin = 0;
                        int destination = 0;
                        Timestamp created = new Timestamp(res.getTimestampCreated().getTimestamp());
                        Timestamp modified = new Timestamp(res.getTimestampLastChanged().getTimestamp());

                        Route obj = new Route(
                                id,
                                nodeKey,
                                organizationKey,
                                res.getFleetTypeKey(),
                                res.getName(),
                                res.getOrigin(),
                                res.getDestination(),
                                res.getDepartureTime(),
                                res.getArrivalTime(),
                                organizationId,
                                fleetTypeId,
                                res.isMon(),
                                res.isTue(),
                                res.isWed(),
                                res.isThu(),
                                res.isFri(),
                                res.isSat(),
                                res.isSun(),
                                created,
                                modified,
                                res.getStatus(),
                                origin,
                                destination
                        );

                        items.add(obj);
                    }
                }

                callback.onRoutesLoaded(items);
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, RoutesResponse>>> call, Throwable t) {

            }
        });

    }

    @Override
    public void getItem(@NonNull String itemKey, @NonNull GetItemCallback callback) {

    }

    @Override
    public void searchItems(@NonNull String originKey,
                            @NonNull String destinationKey,
                            @NonNull String day,
                            @NonNull SearchItemsCallback callback) {

    }
}
