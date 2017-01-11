package com.lighteye.safiri.organizationdetail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lighteye.safiri.BaseActivity;
import com.lighteye.safiri.R;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesLoader;
import com.lighteye.safiri.data.source.entities.organizationbranches.OrganizationBranchesRepository;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsLoader;
import com.lighteye.safiri.data.source.entities.organizations.OrganizationsRepository;
import com.lighteye.safiri.data.source.local.OrganizationBranchesLocalDataSource;
import com.lighteye.safiri.data.source.local.OrganizationsLocalDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class OrganizationDetailsActivity extends BaseActivity {

    public static final String EXTRA_ORGANIZATION_ID = "ORGANIZATION_ID";

    private OrganizationDetailsPresenter mOrganizationDetailsPresenter;
    private OrganizationDetailsView mOrganizationDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_details);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested organization id
        String organizationId = getIntent().getStringExtra(EXTRA_ORGANIZATION_ID);

        mOrganizationDetailsView = (OrganizationDetailsView) findViewById(R.id.organization_details_view);
        checkNotNull(mOrganizationDetailsView, "mOrganizationDetailsView not found in layout");

        mOrganizationDetailsPresenter = new OrganizationDetailsPresenter(
                OrganizationsRepository.getInstance(OrganizationsLocalDataSource.getInstance(getContentResolver())),
                OrganizationBranchesRepository.getInstance(OrganizationBranchesLocalDataSource.getInstance(getContentResolver())),
                mOrganizationDetailsView,
                new OrganizationsLoader(this),
                new OrganizationBranchesLoader(this),
                getSupportLoaderManager(),
                organizationId
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.taskdetail_fragment_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrganizationDetailsPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
