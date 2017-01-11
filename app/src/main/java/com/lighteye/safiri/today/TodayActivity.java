package com.lighteye.safiri.today;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lighteye.safiri.BaseActivity;
import com.lighteye.safiri.R;
import com.lighteye.safiri.bookings.BookingsActivity;
import com.lighteye.safiri.data.source.entities.routes.RoutesLoader;
import com.lighteye.safiri.data.source.entities.routes.RoutesRepository;
import com.lighteye.safiri.data.source.local.RoutesLocalDataSource;
import com.lighteye.safiri.organizations.OrganizationsActivity;
import com.lighteye.safiri.routes.RoutesPresenter;
import com.lighteye.safiri.routes.RoutesView;
import com.lighteye.safiri.searchbookings.SearchBookingsActivity;
import com.lighteye.safiri.utils.Utils;

import static com.google.common.base.Preconditions.checkNotNull;

public class TodayActivity extends BaseActivity {

    public static final String EXTRA_DATE = "DATE";


    private DrawerLayout mDrawerLayout;

    private RoutesView mRoutesView;

    private RoutesPresenter mRoutesPresenter;

    private String mDate;

    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);


        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(EXTRA_DATE)) {
            mDate = getIntent().getStringExtra(EXTRA_DATE);
        } else {


            mDate = Utils.todayString();
        }

        mTitle = "Today";

        ab.setTitle(mTitle);

        // Set up the navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        mRoutesView = (RoutesView) findViewById(R.id.routes_view);
//        mRoutesView.setDate(mDate);
        checkNotNull(mRoutesView, "mRoutesView not found in layout");

        // Create the presenter
        mRoutesPresenter = new RoutesPresenter(
                mRoutesView,
                RoutesRepository.getInstance(RoutesLocalDataSource.getInstance(getContentResolver())),
                getSupportLoaderManager(),
                new RoutesLoader(this),
                mDate
        );

    }

    @Override protected void onResume() {
        super.onResume();
        mRoutesPresenter.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.tasks_fragment_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Context context = TodayActivity.this;
                        Bundle bundle = Utils.activityTransition(context);
                        Intent intent;
                        switch (menuItem.getItemId()) {
                            case R.id.organizations_navigation_menu_item:
                                intent = new Intent(context, OrganizationsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent, bundle);
                                break;
                            case R.id.todays_navigation_menu_item:
                                //do nothing, we're already on that screen
                                break;
                            case R.id.bookings_navigation_menu_item:
                                intent = new Intent(context, BookingsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent, bundle);
                                break;
                            case R.id.search_navigation_menu_item:
                                intent = new Intent(context, SearchBookingsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent, bundle);
                                break;
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }



}
