package com.lighteye.safiri.organizationdetail;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.OrganizationBranch;
import com.lighteye.safiri.utils.CursorRecyclerViewAdapter;

/**
 * Created by yonny on 7/19/16.
 */
public class OrganizationBranchesAdapter extends CursorRecyclerViewAdapter<OrganizationBranchesAdapter.ViewHolder> {

    public OrganizationBranchesAdapter(Context context, Cursor cursor){
        super(context,cursor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBranchTownTextView;
        public TextView mBranchAddressTextView;
        public TextView mBranchContactsTextView;
        public ViewHolder(View view) {
            super(view);
            mBranchTownTextView = (TextView)view.findViewById(R.id.branch_town);
            mBranchAddressTextView = (TextView)view.findViewById(R.id.branch_address);
            mBranchContactsTextView = (TextView)view.findViewById(R.id.branch_contacts);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        final OrganizationBranch branch = OrganizationBranch.from(cursor);
        viewHolder.mBranchTownTextView.setText(branch.getTown());
        viewHolder.mBranchAddressTextView.setText(branch.getAddress());
        viewHolder.mBranchContactsTextView.setText(branch.getContacts());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.organization_branch_list, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }
}
