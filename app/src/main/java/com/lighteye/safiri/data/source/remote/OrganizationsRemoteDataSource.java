package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.data.Timestamp;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsDataSource;
import com.lighteye.safiri.data.source.remote.response.OrganizationsResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/17/16.
 */
public class OrganizationsRemoteDataSource extends BaseRemoteDataSource implements OrganizationsDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, OrganizationsResponse>> call = mSafiriService.getOrganizations();
        call.enqueue(new Callback<Map<String, OrganizationsResponse>>() {
            @Override
            public void onResponse(Call<Map<String, OrganizationsResponse>> call, Response<Map<String, OrganizationsResponse>> response) {
                ArrayList<Organization> organizations = new ArrayList<Organization>();

                for (Map.Entry<String, OrganizationsResponse> entry : response.body().entrySet()) {
                    String nodeKey = entry.getKey();
                    OrganizationsResponse organizationsResponse = entry.getValue();

                    Timestamp created = new Timestamp(organizationsResponse.getTimestampCreated().getTimestamp());
                    Timestamp modified = new Timestamp(organizationsResponse.getTimestampLastChanged().getTimestamp());

                    Organization organization = new Organization(
                            0,
                            nodeKey,
                            organizationsResponse.getName(),
                            organizationsResponse.getType(),
                            organizationsResponse.getAddress(),
                            organizationsResponse.getContacts(),
                            0,
                            organizationsResponse.getTownKey(),
                            created,
                            modified
                    );

                    organizations.add(organization);
                }

                callback.onItemsLoaded(organizations);
            }

            @Override
            public void onFailure(Call<Map<String, OrganizationsResponse>> call, Throwable t) {
                Log.e("OrganizationRemote", t.getMessage());
            }
        });
    }

    @Override
    public void getItem(@NonNull String organizationId, @NonNull GetItemCallback callback) {

    }
}
