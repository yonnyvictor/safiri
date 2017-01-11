package com.lighteye.safiri.organizations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.organizationdetail.OrganizationDetailsActivity;
import com.lighteye.safiri.utils.ScrollChildSwipeRefreshLayout;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/18/16.
 */
public class OrganizationsView extends ScrollChildSwipeRefreshLayout implements OrganizationsContract.View {

    private OrganizationsContract.Presenter mPresenter;

    private boolean mActive;

    private View mNoTasksView;

    private ImageView mNoTaskIcon;

    private TextView mNoTaskMainView;

    private TextView mNoTaskAddView;

    private LinearLayout mTasksView;

    private RecyclerView mOrganizationsRecyclerView;

    private OrganizationsAdapter mOrganizationsAdapter;

    private OrganizationItemListener mItemListener = new OrganizationItemListener() {
        @Override
        public void onOrganizationClick(Organization clickedOrganization) {
            mPresenter.openOrganizationDetails(clickedOrganization.getId());
        }
    };


    public interface OrganizationItemListener {
        void onOrganizationClick(Organization clickedOrganization);
    }


    public OrganizationsView(Context context) {
        super(context);
        initializeScreen();
    }

    public OrganizationsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
    }

    private void initializeScreen() {
        inflate(getContext(), R.layout.organizations_view_content, this);

        mOrganizationsAdapter = new OrganizationsAdapter(getContext(), null, mItemListener);

        mOrganizationsRecyclerView = (RecyclerView)findViewById(R.id.organizations_recycler);

        mOrganizationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mOrganizationsRecyclerView.setAdapter(mOrganizationsAdapter);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = this;
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(mOrganizationsRecyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadOrganizations();
            }
        });

        mActive = true;
    }

    @Override
    public void setPresenter(OrganizationsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        final SwipeRefreshLayout srl = this;

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showOrganizations(Cursor organizations) {
        mOrganizationsAdapter.swapCursor(organizations);
    }

    @Override
    public void showOrganizationDetailsUi(int organizationId) {
        Intent intent = new Intent(getContext(), OrganizationDetailsActivity.class);
        intent.putExtra(OrganizationDetailsActivity.EXTRA_ORGANIZATION_ID, String.valueOf(organizationId));
        startActivity(intent);
    }

    @Override
    public void showLoadingOrganizationsError() {
        showMessage(getString(R.string.loading_organizations_error));
    }

    @Override
    public void showNoOrganizations() {

    }

    @Override
    public boolean isActive() {
        return mActive;
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

    private void startActivity(Intent intent) {
        Activity activity = getActivity(this);
        activity.startActivity(intent);
    }

    private void startActivityForResult(Intent intent, int requestCode) {
        Activity activity = getActivity(this);
        activity.startActivityForResult(intent, requestCode);
    }

    private void showMessage(String message) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show();
    }

    private String getString(@StringRes int resId) {
        return getContext().getString(resId);
    }
}
