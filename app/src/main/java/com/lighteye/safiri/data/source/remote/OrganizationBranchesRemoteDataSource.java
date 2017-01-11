package com.lighteye.safiri.data.source.remote;

import android.support.annotation.NonNull;

import com.lighteye.safiri.data.OrganizationBranch;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesDataSource;
import com.lighteye.safiri.data.source.remote.response.OrganizationBranchesResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yonny on 7/18/16.
 */
public class OrganizationBranchesRemoteDataSource extends BaseRemoteDataSource
        implements OrganizationBranchesDataSource {

    @Override
    public void getItems(@NonNull final GetItemsCallback callback) {
        Call<Map<String, Map<String, OrganizationBranchesResponse>>> call =
                mSafiriService.getOrganizationBranches();

        call.enqueue(new Callback<Map<String, Map<String, OrganizationBranchesResponse>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, OrganizationBranchesResponse>>> call,
                                   Response<Map<String, Map<String, OrganizationBranchesResponse>>> response) {
                ArrayList<OrganizationBranch> branches = new ArrayList<OrganizationBranch>();

                for (Map.Entry<String, Map<String, OrganizationBranchesResponse>> entry : response.body().entrySet()) {
                    String organizationKey = entry.getKey();
                    Map<String, OrganizationBranchesResponse> branchMap = entry.getValue();

                    for (Map.Entry<String, OrganizationBranchesResponse> child : branchMap.entrySet()) {
                        String nodeKey = child.getKey();
                        OrganizationBranchesResponse organizationBranchesResponse = child.getValue();

                        OrganizationBranch branch = new OrganizationBranch(
                                0,
                                nodeKey,
                                organizationBranchesResponse.getContacts(),
                                organizationBranchesResponse.getAddress(),
                                organizationKey,
                                organizationBranchesResponse.getTownKey(),
                                0,
                                0,
                                "None"
                        );

                        branches.add(branch);
                    }
                }

                callback.onItemsLoaded(branches);
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, OrganizationBranchesResponse>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getItems(@NonNull String organizationId,
                         @NonNull final GetItemsCallback callback) {
    }
}
