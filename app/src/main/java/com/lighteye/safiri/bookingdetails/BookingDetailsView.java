package com.lighteye.safiri.bookingdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.addeditbookingdetail.AddEditBookingDetailActivity;
import com.lighteye.safiri.bookings.BookingsActivity;
import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Route;
import com.lighteye.safiri.utils.Utils;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/27/16.
 */
public class BookingDetailsView extends LinearLayout implements BookingDetailsContract.View {

    public interface BookingDetailItemListener {
        void onBookingDetailClick(BookingDetail bookingDetail);
    }

    private boolean mActive;
    //private ArrayList<BookingDetail> mBookingDetailsList;

    private TextView mOrganizationTextView;
    private TextView mRouteTextView;
    private TextView mOriginTextView;
    private TextView mDestinationTextView;
    private TextView mDepartureTextView;
    private TextView mTravelDateTextView;
    private ListView mBookingDetailsListView;


    private BookingDetailsContract.Presenter mPresenter;

    private BookingDetailsArrayAdapter mBookingDetailsArrayAdapter;

    private BookingDetailsAdapter mBookingDetailsAdapter;

    private BookingDetailItemListener mBookingDetailItemListener;

    public BookingDetailsView(Context context) {
        super(context);
        initializeScreen();
    }

    public BookingDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
    }

    private void initializeScreen(){
        inflate(getContext(), R.layout.bookingdetails_view_content, this);

        mOrganizationTextView = (TextView) findViewById(R.id.booking_detail_organization_textview);
        mRouteTextView = (TextView) findViewById(R.id.booking_detail_route_textview);
        mOriginTextView = (TextView) findViewById(R.id.booking_detail_origin_textview);
        mDestinationTextView = (TextView) findViewById(R.id.booking_detail_destination_textview);
        mDepartureTextView = (TextView) findViewById(R.id.booking_detail_departure_textview);
        mTravelDateTextView = (TextView) findViewById(R.id.booking_detail_travel_date_textview);
        mBookingDetailsListView = (ListView) findViewById(R.id.booking_details_list_view);

        mBookingDetailsListView.setEmptyView(findViewById(R.id.empty_booking_detail_element));

        mBookingDetailItemListener = new BookingDetailItemListener() {
            @Override
            public void onBookingDetailClick(BookingDetail bookingDetail) {
                mPresenter.removeBookingDetail(bookingDetail);
            }
        };

        mActive = true;
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

    @Override
    public void showRoute(Route route, String travelDate) {
        mOrganizationTextView.setText(route.getOrganizationName());
        mRouteTextView.setText(route.getName());
        mOriginTextView.setText(route.getOriginName());
        mDestinationTextView.setText(route.getDestinationName());
        mDepartureTextView.setText(Utils.millisOfDayString(route.getDepartureTime()));
        mTravelDateTextView.setText(travelDate);
    }

    @Override
    public void showBookingsList() {
        Activity activity = getActivity(this);
        activity.setResult(Activity.RESULT_OK);
        activity.finish();

        Intent intent = new Intent(getContext(), BookingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showBookingsError() {

    }

    @Override
    public void showBooking(Booking booking) {
        mTravelDateTextView.setText(Utils.convertMilliSecondsToDateString(booking.getTravelDate()));
    }

    @Override
    public void showBookingDetails(Cursor details, boolean editable) {
        mBookingDetailsAdapter = new BookingDetailsAdapter(getContext(), details, editable,
                mBookingDetailItemListener);
        mBookingDetailsListView.setAdapter(mBookingDetailsAdapter);
    }

    @Override
    public void setPresenter(BookingDetailsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showBookingDetailsList() {
        ArrayList<BookingDetail> bookingDetailsList;
        if(mPresenter.getBookingDetailsList() != null){
            bookingDetailsList = mPresenter.getBookingDetailsList();
        }else{
            bookingDetailsList = new ArrayList<BookingDetail>();
        }

        mBookingDetailsArrayAdapter = new BookingDetailsArrayAdapter(getContext(),
                bookingDetailsList, mBookingDetailItemListener);

        mBookingDetailsListView.setAdapter(mBookingDetailsArrayAdapter);
    }

    @Override
    public void addBookingDetail(BookingDetail bookingDetail) {
        mBookingDetailsArrayAdapter.add(bookingDetail);
    }

    @Override
    public void removeBookingDetail(BookingDetail bookingDetail) {
        mBookingDetailsArrayAdapter.remove(bookingDetail);
    }



    @Override
    public void validateNewBooking() {
        if(!mBookingDetailsArrayAdapter.isEmpty()){
            //retrieve adapter data
            ArrayList<BookingDetail> bookingDetailArrayList = new ArrayList<>();
            for(int i = 0 ; i < mBookingDetailsArrayAdapter.getCount() ; i++){
                bookingDetailArrayList.add(mBookingDetailsArrayAdapter.getItem(i));
            }

            //pass these details to presenter to persist
            mPresenter.createBooking(bookingDetailArrayList);
        }
    }

    @Override
    public void showBookingDetailForm(String organizationId, String fleetTypeId, String routeKey, String travelDate) {
        Intent intent = new Intent(getContext(), AddEditBookingDetailActivity.class);
        intent.putExtra(AddEditBookingDetailActivity.EXTRA_ORGANIZATION_ID, String.valueOf(organizationId));
        intent.putExtra(AddEditBookingDetailActivity.EXTRA_FLEET_TYPE_ID, String.valueOf(fleetTypeId));
        intent.putExtra(AddEditBookingDetailActivity.EXTRA_ROUTE_KEY, routeKey);
        intent.putExtra(AddEditBookingDetailActivity.EXTRA_TRAVEL_DATE, travelDate);
        startActivityForResult(intent, AddEditBookingDetailActivity.REQUEST_ADD_BOOKING_DETAIL);
        //startActivity(intent);
    }

    private void startActivity(Intent intent) {
        Activity activity = getActivity(this);
        activity.startActivity(intent, Utils.activityTransition(getContext()));
    }

    private void startActivityForResult(Intent intent, int requestCode) {
        Activity activity = getActivity(this);
        activity.startActivityForResult(intent, requestCode);
    }

    public int countAdapterItems(){
        return mBookingDetailsAdapter.getCount();
    }

}
