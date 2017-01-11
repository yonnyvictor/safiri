package com.lighteye.safiri.searchresults;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lighteye.safiri.BaseActivity;
import com.lighteye.safiri.R;
import com.lighteye.safiri.data.source.entities.routes.RoutesLoader;
import com.lighteye.safiri.data.source.entities.routes.RoutesRepository;
import com.lighteye.safiri.data.source.local.RoutesLocalDataSource;
import com.lighteye.safiri.routes.RoutesPresenter;
import com.lighteye.safiri.routes.RoutesView;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchResultsActivity extends BaseActivity {

    public static final String EXTRA_ORIGIN_ID = "ORIGIN_ID";
    public static final String EXTRA_DESTINATION_ID = "DESTINATION_ID";
    public static final String EXTRA_TRAVEL_DATE = "TRAVEL_DATE";

    private RoutesView mRoutesView;

    private RoutesPresenter mRoutesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested origin id
        String originId = getIntent().getStringExtra(EXTRA_ORIGIN_ID);
        String destinationId = getIntent().getStringExtra(EXTRA_DESTINATION_ID);
        String travelDate = getIntent().getStringExtra(EXTRA_TRAVEL_DATE);

        mRoutesView = (RoutesView) findViewById(R.id.routes_view);
        checkNotNull(mRoutesView, "mRoutesView not found in layout");

        // Create the presenter
        mRoutesPresenter = new RoutesPresenter(
                mRoutesView,
                RoutesRepository.getInstance(RoutesLocalDataSource.getInstance(getContentResolver())),
                getSupportLoaderManager(),
                new RoutesLoader(this),
                travelDate,
                originId,
                destinationId
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRoutesPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
