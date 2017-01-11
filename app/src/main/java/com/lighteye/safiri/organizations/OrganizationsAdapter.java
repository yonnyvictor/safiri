package com.lighteye.safiri.organizations;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Organization;
import com.lighteye.safiri.utils.CursorRecyclerViewAdapter;

/**
 * Created by yonny on 7/18/16.
 */
public class OrganizationsAdapter extends CursorRecyclerViewAdapter<OrganizationsAdapter.ViewHolder>  {

    private final OrganizationsView.OrganizationItemListener mItemListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final GridLayout container;
        private final TextView titleTextView;
        private final TextView addressTextView;
        private final TextView contactsTextView;
        private final TextView townTextView;
        public ViewHolder(CardView v){
            super(v);
            titleTextView = (TextView)v.findViewById(R.id.organization_title);
            addressTextView = (TextView)v.findViewById(R.id.organization_address);
            contactsTextView = (TextView)v.findViewById(R.id.organization_contacts);
            townTextView = (TextView)v.findViewById(R.id.organization_town);
            container = (GridLayout)v.findViewById(R.id.organization_row_container);
        }
    }

    public OrganizationsAdapter(Context context, Cursor cursor,
                                OrganizationsView.OrganizationItemListener itemListener) {
        super(context, cursor);
        mItemListener = itemListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        final int position = cursor.getPosition();
        final Organization organization = Organization.from(cursor);
        viewHolder.titleTextView.setText(organization.getName());
        viewHolder.addressTextView.setText(organization.getAddress());
        viewHolder.townTextView.setText(organization.getTown());
        viewHolder.contactsTextView.setText(organization.getContacts());

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onOrganizationClick(organization);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_organization, parent, false);
        return new ViewHolder(cv);
    }


}
