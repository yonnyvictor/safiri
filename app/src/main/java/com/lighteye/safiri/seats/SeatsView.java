package com.lighteye.safiri.seats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.SeatsConfiguration;
import com.lighteye.safiri.data.source.remote.response.BookingsResponse;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/28/16.
 */
public class SeatsView extends LinearLayout implements SeatsContract.View{

//    public interface SelectedSeatCallback{
//        void onSelectedSeat(Seat seat);
//    }
//
//    private SelectedSeatCallback mSelectedSeatCallback;

    private boolean mActive;

    GridLayout mGridLayout;

    private SeatsContract.Presenter mPresenter;

    public SeatsView(Context context) {
        super(context);
        initializeScreen();
    }

    public SeatsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
    }

    private void initializeScreen(){
        inflate(getContext(), R.layout.seats_view_content, this);

        mGridLayout = (GridLayout)findViewById(R.id.seats_grid_layout);

        mActive = true;

        //checkSeatBooking("sdfsdfs");
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
    public void showSeats(Cursor details, SeatsConfiguration seatsConfiguration) {
        renderSeats(details, seatsConfiguration);
    }

    @Override
    public void setPresenter(SeatsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showAddEditSeatUi(Seat seat) {
        Activity activity = getActivity(this);
        Intent returnIntent = activity.getIntent();
        returnIntent.putExtra("selectedSeat", seat);
        activity.setResult(Activity.RESULT_OK, returnIntent);
        activity.finish();
    }

    private void renderSeats(Cursor seats, SeatsConfiguration config){

        mGridLayout.removeAllViews();

        mGridLayout.setColumnCount(config.getColumns());
        mGridLayout.setRowCount(config.getRows());

        int offset = 1;

        if(seats.moveToFirst()){
            do {
                final Seat seat = Seat.from(seats);

                Button btnTag = new Button(getContext());
//                ImageView oImageView = new ImageView(getContext());
//                oImageView.setImageResource(R.drawable.logo);
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
//                param.height = LayoutParams.WRAP_CONTENT;
//                param.width = LayoutParams.WRAP_CONTENT;
                param.height = 100;
                param.width = 100;
                param.rightMargin = 1;
                param.topMargin = 1;
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(seat.getColumn() - offset);
                param.rowSpec = GridLayout.spec(seat.getRow() - offset);


                btnTag.setText(seat.getName());
                btnTag.setLayoutParams(param);

                btnTag.setBackgroundColor(getResources().getColor(R.color.dark_green));

                checkSeatBooking(seat, btnTag);

                btnTag.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showAddEditSeatUi(seat);
                    }
                });

                mGridLayout.addView(btnTag);
            }while (seats.moveToNext());
        }
    }

//    public void setSelectedSeatCallback(SelectedSeatCallback selectedSeatCallback) {
//        this.mSelectedSeatCallback = selectedSeatCallback;
//    }

    private void checkSeatBooking(final Seat seat, final Button btnTag){
        Observable<Map<String, BookingsResponse>> observable = mPresenter.getSeatBooking(seat.getNodeKey());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, BookingsResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.e("SeatsViewOnError", e.toString());
                    }

                    @Override
                    public void onNext(Map<String, BookingsResponse> stringBookingsResponseMap) {
                        //Log.e("SeatsViewOnNext", stringBookingsResponseMap.toString());
                        //String nodeKey = stringBookingsResponseMap.
                        if(stringBookingsResponseMap != null) {
                            for (Map.Entry<String, BookingsResponse> entry : stringBookingsResponseMap.entrySet()) {
                                String nodeKey = entry.getKey();
                                BookingsResponse bookingsResponse = entry.getValue();

                                if(bookingsResponse.getSeatKey().equals(seat.getNodeKey())){
                                    btnTag.setBackgroundColor(getResources().getColor(R.color.accent));
                                    btnTag.setOnClickListener(null);
                                }
                            }
                        }
                    }
                });
    }

}
