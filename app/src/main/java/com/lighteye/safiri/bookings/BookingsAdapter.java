package com.lighteye.safiri.bookings;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.utils.Constants;
import com.lighteye.safiri.utils.Utils;
import com.twotoasters.sectioncursoradapter.adapter.SectionCursorAdapter;
import com.twotoasters.sectioncursoradapter.adapter.viewholder.ViewHolder;

/**
 * Created by yonny on 7/30/16.
 */
public class BookingsAdapter extends SectionCursorAdapter<String, BookingsAdapter.SectionViewHolder, BookingsAdapter.ItemViewHolder> {

    private final BookingsView.BookingItemListener mItemListener;

    public static class SectionViewHolder extends ViewHolder {
        private final TextView mSectionName;
        public SectionViewHolder(View rootView) {
            super(rootView);
            mSectionName = (TextView) rootView.findViewById(R.id.listview_header_text);
        }
    }

    public static class ItemViewHolder extends ViewHolder{
        private final TextView organizationTextView;
        private final TextView originTextView;
        private final TextView destinationTextView;
        private final TextView totalCostTextView;
        private final GridLayout containerLayout;

        public ItemViewHolder(View rootView) {
            super(rootView);
            originTextView = (TextView)rootView.findViewById(R.id.booking_route_origin);
            destinationTextView = (TextView)rootView.findViewById(R.id.booking_route_destination);
            organizationTextView = (TextView)rootView.findViewById(R.id.booking_route_organization_name);
            totalCostTextView = (TextView)rootView.findViewById(R.id.booking_total_cost);
            containerLayout = (GridLayout)rootView.findViewById(R.id.booking_container);
        }
    }

    public BookingsAdapter(Context context, Cursor cursor,
                           BookingsView.BookingItemListener mItemListener) {
        super(context, cursor, 0, R.layout.listview_header, R.layout.card_booking);
        this.mItemListener = mItemListener;
    }

    @Override
    protected String getSectionFromCursor(Cursor cursor) {
        final Booking booking = Booking.from(cursor);
        return Utils.convertMilliSecondsToDateString(booking.getTravelDate(), Constants.DISPLAY_DATE_FORMAT);
    }

    @Override
    protected SectionViewHolder createSectionViewHolder(View sectionView, String section) {
        return new SectionViewHolder(sectionView);
    }

    @Override
    protected void bindSectionViewHolder(int position, SectionViewHolder sectionViewHolder,
                                         ViewGroup parent, String section) {
        sectionViewHolder.mSectionName.setText(section);
    }

    @Override
    protected ItemViewHolder createItemViewHolder(Cursor cursor, View itemView) {
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void bindItemViewHolder(ItemViewHolder itemViewHolder, Cursor cursor,
                                      ViewGroup parent) {
        final int position = cursor.getPosition();
        final Booking booking = Booking.from(cursor);

        itemViewHolder.organizationTextView.setText(booking.getOrganizationName());
        itemViewHolder.originTextView.setText(booking.getOriginName());
        itemViewHolder.destinationTextView.setText(booking.getDestinationName());

        if(booking.getStatus().equals("Cancelled")){
            /* Add a strike-through */
            //textViewItemName.setPaintFlags(textViewItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            itemViewHolder.organizationTextView.setPaintFlags(itemViewHolder.organizationTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            itemViewHolder.originTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            itemViewHolder.destinationTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            itemViewHolder.totalCostTextView.setText("Cancelled");
            //itemViewHolder.totalCostTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            itemViewHolder.totalCostTextView.setText(String.valueOf((int)booking.getTotalCharge()));
        }

        itemViewHolder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onBookingClick(booking);
            }
        });
    }

    @Override
    protected int getMaxIndexerLength() {
        return 1;
    }
}
