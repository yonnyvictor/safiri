package com.lighteye.safiri.addeditbookingdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.seats.SeatsActivity;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/29/16.
 */
public class AddEditBookingDetailView extends LinearLayout implements AddEditBookingDetailContract.View{

    private boolean mActive;

    private boolean isSeatSelected;

    private TextView mSelectedSeatNameTextView;
    private EditText mTravellerEditText;
    private ImageButton mSelectSeatButton;
    private Button mSaveBookingDetailButton;

    private AddEditBookingDetailContract.Presenter mPresenter;

    public AddEditBookingDetailView(Context context) {
        super(context);
        initializeScreen();
    }

    public AddEditBookingDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
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
    public void showEmptySeatError() {

    }

    @Override
    public void showBookingDetailUi() {
        Activity activity = getActivity(this);
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    @Override
    public void showBookingDetailUi(BookingDetail bookingDetail) {
        Activity activity = getActivity(this);
        Intent returnIntent = activity.getIntent();
        returnIntent.putExtra("bookingDetail", bookingDetail);
        activity.setResult(Activity.RESULT_OK, returnIntent);
        activity.finish();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(AddEditBookingDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void openSeatsUi(String organizationId, String fleetTypeId, String routeKey, String travelDate) {
        Intent intent = new Intent(getContext(), SeatsActivity.class);
        intent.putExtra(SeatsActivity.EXTRA_ORGANIZATION_ID, String.valueOf(organizationId));
        intent.putExtra(SeatsActivity.EXTRA_FLEET_TYPE_ID, String.valueOf(fleetTypeId));
        intent.putExtra(SeatsActivity.EXTRA_ROUTE_KEY, String.valueOf(routeKey));
        intent.putExtra(SeatsActivity.EXTRA_TRAVEL_DATE, String.valueOf(travelDate));
        startActivityForResult(intent, SeatsActivity.REQUEST_SELECT_SEAT);
    }

    @Override
    public void showSelectedSeat(Seat seat) {
        mSelectedSeatNameTextView.setText(seat.getName());
        isSeatSelected = true;
    }

    @Override
    public void validateBookingDetail() {

    }

    private void initializeScreen(){
        inflate(getContext(), R.layout.add_edit_booking_detail_view_content, this);

        mSelectedSeatNameTextView = (TextView) findViewById(R.id.booking_detail_seat_input);
        mTravellerEditText = (EditText) findViewById(R.id.booking_detail_traveller_input);
        mSaveBookingDetailButton = (Button) findViewById(R.id.add_booking_detaill_button);
        mSelectSeatButton = (ImageButton) findViewById(R.id.select_seat_button);

//        mSelectedSeatNameTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                mPresenter.showSeatsUi();
//            }
//        });

        mSelectedSeatNameTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showSeatsUi();
            }
        });

        mSaveBookingDetailButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if a seat is selected
                if(isSeatSelected){
                    //check if traveller names have been added
                    String traveller = mTravellerEditText.getText().toString();
                    if(traveller.equals("")){
                        mTravellerEditText.setError(getResources().getString(R.string.error_cannot_be_empty));
                        return;
                    }else{
                        mPresenter.loadSeatCharge(traveller);
                    }
                }else{
                    showMessage(getResources().getString(R.string.error_message_select_seat));
                }


            }
        });

        mActive = true;
    }

    private void startActivity(Intent intent) {
        Activity activity = getActivity(this);
        activity.startActivity(intent);
    }

    private void startActivityForResult(Intent intent, int requestCode) {
        Activity activity = getActivity(this);
        activity.startActivityForResult(intent, requestCode);
    }

    private void showMessage(String message){
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show();
    }
}