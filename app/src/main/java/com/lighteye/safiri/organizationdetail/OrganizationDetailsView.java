package com.lighteye.safiri.organizationdetail;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Organization;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yonny on 7/19/16.
 */
public class OrganizationDetailsView extends LinearLayout implements OrganizationDetailsContract.View {

    private boolean mActive;

    private OrganizationDetailsContract.Presenter mPresenter;

    private TextView mOrganizationTitle;
    private TextView mOrganizationTown;
    private TextView mOrganizationAddress;
    private TextView mOrganizationContacts;
    private RecyclerView mOrganizationBranchesRecyclerView;

    private OrganizationBranchesAdapter mOrganizationBranchesAdapter;

    public OrganizationDetailsView(Context context) {
        super(context);
        init();
    }

    public OrganizationDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.organizationdetails_view_content, this);

        mOrganizationBranchesAdapter = new OrganizationBranchesAdapter(getContext(), null);

        mOrganizationTitle = (TextView) findViewById(R.id.organization_details_title);
        mOrganizationTown = (TextView) findViewById(R.id.organization_details_town);
        mOrganizationContacts = (TextView) findViewById(R.id.organization_details_contacts);
        mOrganizationAddress = (TextView) findViewById(R.id.organization_details_address);

        mOrganizationBranchesRecyclerView = (RecyclerView) findViewById(R.id.organization_branches_recycler);
        mOrganizationBranchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mOrganizationBranchesRecyclerView.setAdapter(mOrganizationBranchesAdapter);

        mActive = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
    }

    @Override
    public void setLoadingOrganizationIndicator(boolean active) {

    }

    @Override
    public void setLoadingOrganizationBranchesIndicator(boolean active) {

    }

    @Override
    public void showOrganization(Organization organization) {
        mOrganizationTitle.setText(organization.getName());
        mOrganizationTown.setText(organization.getTown());
        mOrganizationAddress.setText(organization.getAddress());
        mOrganizationContacts.setText(organization.getContacts());
    }

    @Override
    public void showOrganizationBranches(Cursor branches) {
        mOrganizationBranchesAdapter.swapCursor(branches);
    }

    @Override
    public void setPresenter(OrganizationDetailsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
