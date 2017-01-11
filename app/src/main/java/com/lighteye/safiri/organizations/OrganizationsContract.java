package com.lighteye.safiri.organizations;

import android.database.Cursor;

import com.lighteye.safiri.BasePresenter;
import com.lighteye.safiri.BaseView;

/**
 * Created by yonny on 7/15/16.
 */
public interface OrganizationsContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showOrganizations(Cursor organizations);
        void showOrganizationDetailsUi(int organizationId);
        void showLoadingOrganizationsError();
        void showNoOrganizations();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadOrganizations();
        void openOrganizationDetails(int organizationId);
    }
}
