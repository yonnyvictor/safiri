package com.lighteye.safiri.data.source.sync;

import android.support.annotation.NonNull;

/**
 * Created by yonny on 7/21/16.
 */
public interface SyncContract {
    interface SyncComplete{
        void onComplete(int syncId, boolean success);
    }
    void syncTowns(@NonNull SyncComplete callback);
    void syncOrganizations(@NonNull SyncComplete callback);
    void syncOrganizationBranches(@NonNull SyncComplete callback);
    void syncFleetTypes(@NonNull SyncComplete callback);
    void syncRoutes(@NonNull SyncComplete callback);
    void syncSeatsConfiguration(@NonNull SyncComplete callback);
    void syncSeatTypes(@NonNull SyncComplete callback);
    void syncSeatCharges(@NonNull SyncComplete callback);
    void syncSeats(@NonNull SyncComplete callback);
    void syncAll();
}
