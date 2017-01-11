package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.Town;
import com.lighteye.safiri.data.source.entities.towns.TownsDataSource;
import com.lighteye.safiri.data.source.remote.response.TownsResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/16/16.
 */
public class TownsRemoteDataSource extends BaseRemoteDataSource implements TownsDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {

        Call<Map<String, TownsResponse>> call = mSafiriService.getTowns();
        call.enqueue(new Callback<Map<String,TownsResponse>>() {
            @Override
            public void onResponse(Call<Map<String, TownsResponse>> call, Response<Map<String, TownsResponse>> response) {
                ArrayList<Town> towns = new ArrayList<Town>();
                for (Map.Entry<String, TownsResponse> entry : response.body().entrySet()) {
                    String nodeKey = entry.getKey();
                    TownsResponse townsResponse = entry.getValue();
                    Town town = new Town(0, nodeKey, townsResponse.getName());
                    towns.add(town);
                }

                callback.onItemsLoaded(towns);
            }

            @Override
            public void onFailure(Call<Map<String, TownsResponse>> call, Throwable t) {

            }
        });
        //Observable<Map<String, TownsResponse>> towns = mSafiriService.getTowns();
    }

    @Override
    public void getItem(@NonNull String townKey, @NonNull GetItemCallback callback) {

    }

//    public Observable<List<Town>> getTowns(){
//        return mSafiriService.getTowns().map(new Func1<Map<String, TownsResponse>, List<Town>>() {
//            @Override
//            public List<Town> call(Map<String, TownsResponse> stringTownsResponseMap) {
//                ArrayList<Town> towns = new ArrayList<Town>();
//                for (Map.Entry<String, TownsResponse> entry : stringTownsResponseMap.entrySet()) {
//                    String nodeKey = entry.getKey();
//                    TownsResponse townsResponse = entry.getValue();
//                    Town town = new Town(0, nodeKey, townsResponse.getName());
//                    towns.add(town);
//                }
//
//                return towns;
//            }
//        });
//    }
//
//    public Observable<Integer> getNumberOfTowns(){
//        return getTowns().map(new Func1<List<Town>, Integer>() {
//            @Override
//            public Integer call(List<Town> towns) {
//                return towns.size();
//            }
//        });
//    }
}
