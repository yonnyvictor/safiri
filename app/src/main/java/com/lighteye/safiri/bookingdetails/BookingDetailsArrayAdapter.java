package com.lighteye.safiri.bookingdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.BookingDetail;

import java.util.List;

/**
 * Created by yonny on 7/28/16.
 */
public class BookingDetailsArrayAdapter extends ArrayAdapter<BookingDetail> {
    // View lookup cache
    private static class ViewHolder {
        TextView traveller;
        TextView seat;
        TextView charge;
        TextView status;
        ImageButton deleteButton;
    }

    private final BookingDetailsView.BookingDetailItemListener mItemListener;

    public BookingDetailsArrayAdapter(Context context, List<BookingDetail> details,
                                      BookingDetailsView.BookingDetailItemListener bookingDetailItemListener) {
        super(context, 0, details);
        mItemListener = bookingDetailItemListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final BookingDetail bookingDetail = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.booking_detail_item, parent, false);
            viewHolder.traveller = (TextView) convertView.findViewById(R.id.booking_detail_traveller);
            viewHolder.seat = (TextView) convertView.findViewById(R.id.booking_detail_seat);
            viewHolder.charge = (TextView) convertView.findViewById(R.id.booking_detail_price);
            viewHolder.deleteButton = (ImageButton) convertView.findViewById(R.id.delete_booking_detail_button);
            viewHolder.status = (TextView) convertView.findViewById(R.id.booking_detail_status);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.traveller.setText(bookingDetail.getTraveller());
        viewHolder.seat.setText(bookingDetail.getSeatName());
        viewHolder.status.setText(bookingDetail.getStatus());
        viewHolder.charge.setText(String.valueOf((int)bookingDetail.getSeatCharge()));

        viewHolder.deleteButton.setVisibility(ImageButton.VISIBLE);

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onBookingDetailClick(bookingDetail);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
