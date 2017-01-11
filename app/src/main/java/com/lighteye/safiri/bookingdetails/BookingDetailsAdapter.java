package com.lighteye.safiri.bookingdetails;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.BookingDetail;

/**
 * Created by yonny on 8/15/16.
 */
public class BookingDetailsAdapter extends CursorAdapter {

    private final boolean mEditableBooking;
    private final BookingDetailsView.BookingDetailItemListener mItemListener;

    public BookingDetailsAdapter(Context context, Cursor c, boolean editableBooking,
                                 BookingDetailsView.BookingDetailItemListener bookingDetailItemListener) {
        super(context, c, 0);
        mEditableBooking = editableBooking;
        mItemListener = bookingDetailItemListener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.booking_detail_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView tvTraveller = (TextView) view.findViewById(R.id.booking_detail_traveller);
        TextView tvSeat = (TextView) view.findViewById(R.id.booking_detail_seat);
        TextView tvPrice = (TextView) view.findViewById(R.id.booking_detail_price);
        TextView tvStatus = (TextView) view.findViewById(R.id.booking_detail_status);
        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_booking_detail_button);

        // Extract BookingDetail from cursor
        final BookingDetail bookingDetail = BookingDetail.from(cursor);

        // Populate fields with extracted properties
        tvTraveller.setText(bookingDetail.getTraveller());
        tvSeat.setText(bookingDetail.getSeatName());
        tvStatus.setText(bookingDetail.getStatus());
        tvPrice.setText(String.valueOf((int)bookingDetail.getSeatCharge()));

        String bookingStatus = bookingDetail.getStatus();

        if(bookingStatus.equals("Cancelled")){
            tvTraveller.setPaintFlags(tvTraveller.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvSeat.setPaintFlags(tvSeat.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvPrice.setPaintFlags(tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            deleteButton.setVisibility(ImageButton.GONE);
        }else{
            if(mEditableBooking){
                deleteButton.setVisibility(ImageButton.VISIBLE);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemListener.onBookingDetailClick(bookingDetail);
                    }
                });
            }
        }
    }
}
