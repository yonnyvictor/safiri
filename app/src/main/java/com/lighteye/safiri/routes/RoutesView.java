package com.lighteye.safiri.routes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.bookingdetails.BookingDetailsActivity;
import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.utils.ScrollChildSwipeRefreshLayout;
import com.lighteye.safiri.utils.Utils;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/22/16.
 */
public class RoutesView extends ScrollChildSwipeRefreshLayout implements RoutesContract.View  {

    public interface RouteItemListener {
        void onRouteClick(Route route);
    }

    private RoutesContract.Presenter mPresenter;

    private boolean mActive;

    private String mDate;

    private View mNoTasksView;

    private ImageView mNoTaskIcon;

    private TextView mNoTaskMainView;

    private TextView mNoTaskAddView;

    private LinearLayout mTasksView;

    private RecyclerView mRoutesRecyclerView;

    private ListView mRoutesListView;

    private RoutesAdapter mRoutesAdapter;

    private RouteItemListener mRouteItemListener;



    public RoutesView(Context context) {
        super(context);
        initializeScreen();
    }

    public RoutesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
    }

    private void initializeScreen() {
        inflate(getContext(), R.layout.routes_view_content, this);

        mRouteItemListener = new RouteItemListener() {
            @Override
            public void onRouteClick(Route route) {
                mPresenter.openBookingUi(route.getId());
            }
        };

        mRoutesAdapter = new RoutesAdapter(getContext(), null, mRouteItemListener);

        mRoutesListView = (ListView)findViewById(R.id.routes_list_view);
        mRoutesListView.setAdapter(mRoutesAdapter);

        mRoutesListView.setEmptyView(findViewById(R.id.empty_route_element));

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = this;
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(mRoutesListView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadRoutes();
            }
        });

        mActive = true;
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
    public void showRoutes(Cursor routes) {
        mRoutesAdapter.setDate(mDate);
        mRoutesAdapter.swapCursor(routes);
    }

    @Override
    public void showLoadingRoutesError() {
        showMessage(getString(R.string.loading_organizations_error));
    }

    @Override
    public void showNoRoutes() {

    }

    @Override
    public void setDate(String date) {
        mDate = date;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(RoutesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showBookingsUi(int routeId, String date) {
        Intent intent = new Intent(getContext(), BookingDetailsActivity.class);
        intent.putExtra(BookingDetailsActivity.EXTRA_ROUTE_ID, String.valueOf(routeId));
        intent.putExtra(BookingDetailsActivity.EXTRA_TRAVEL_DATE, date);
        startActivity(intent);
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
        activity.startActivity(intent, Utils.activityTransition(getContext()));
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
