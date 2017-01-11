package com.lighteye.safiri.organizationdetail;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;
import com.lighteye.safiri.data.Organization;

/**
 * Created by yonny on 7/15/16.
 */
public interface OrganizationDetailsContract {
    interface View extends BaseView<Presenter> {
        void setLoadingOrganizationIndicator(boolean active);
        void setLoadingOrganizationBranchesIndicator(boolean active);
        void showOrganization(Organization organization);
        void showOrganizationBranches(Cursor branches);
    }

    interface Presenter extends BasePresenter {
        void loadOrganizationBranches(String organizationId);
    }
}
