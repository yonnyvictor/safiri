package com.lighteye.safiri.searchbookings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.lighteye.safiri.R;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;
import com.lighteye.safiri.searchresults.SearchResultsActivity;
import com.lighteye.safiri.utils.Constants;
import com.lighteye.safiri.utils.MyDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lighteye.safiri.utils.ActivityUtils.getActivity;

/**
 * Created by yonny on 7/25/16.
 */
public class SearchBookingsView extends LinearLayout implements SearchBookingsContract.View{

    private Spinner mOriginSpinner;
    private Spinner mDestinationSpinner;
    private ImageButton mTravelDateButton;
    private Button mSearchButton;
    private TextView mTravelDateInput;
    private Calendar myCalendar;

    private MyDatePickerDialog mMyDatePickerDialog;

    private int mYear, mMonth, mDay, mDayOfWeek;


    private SimpleCursorAdapter mTownsAdapter;

    private boolean mActive;

    private SearchBookingsContract.Presenter mPresenter;

    private FragmentManager mFragmentManager;


    public SearchBookingsView(Context context) {
        super(context);
        initializeScreen();
    }

    public SearchBookingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeScreen();
    }

    private void initializeScreen() {
        inflate(getContext(), R.layout.search_bookings_view_content, this);

        myCalendar = Calendar.getInstance();
        myCalendar.add(Calendar.DAY_OF_YEAR, 1);

        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        mDayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);

        mMyDatePickerDialog = MyDatePickerDialog.newInstance(mYear, mMonth, mDay);
        mMyDatePickerDialog.setSelectedDateCallback(new MyDatePickerDialog.SelectedDateCallback() {
            @Override
            public void onSelectedDate(int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;

                myCalendar.set(Calendar.YEAR, mYear);
                myCalendar.set(Calendar.MONTH, mMonth);
                myCalendar.set(Calendar.DAY_OF_MONTH, mDay);

                mDayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);

                updateDateInputLabel();
            }
        });


        mTownsAdapter = new SimpleCursorAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                null,
                new String[]{SafiriPersistenceContract.TownsEntry.COLUMN_NAME},
                new int[]{android.R.id.text1},
                0
        );


        mTownsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mOriginSpinner = (Spinner) findViewById(R.id.origin_spinner_input);
        mOriginSpinner.setAdapter(mTownsAdapter);
        mDestinationSpinner = (Spinner) findViewById(R.id.destination_spinner_input);
        mDestinationSpinner.setAdapter(mTownsAdapter);
        mTravelDateButton = (ImageButton) findViewById(R.id.select_travel_date_button);
        mTravelDateInput = (TextView) findViewById(R.id.travel_date_input);
        mSearchButton = (Button) findViewById(R.id.submit_search_button);
//        mSeatsInput = (EditText) findViewById(R.id.travel_seats_input);

        mTravelDateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        mTravelDateInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        mSearchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                long originId = mOriginSpinner.getSelectedItemId();
                long destinationId = mDestinationSpinner.getSelectedItemId();
                String travelDate = mTravelDateInput.getText().toString();

                if(originId > 0 && destinationId > 0 && travelDate != null){
                    submitSearchDetails(
                            String.valueOf(originId),
                            String.valueOf(destinationId),
                            travelDate
                    );
                }
            }
        });

        updateDateInputLabel();

        mActive = true;
    }

    @Override
    public void loadTowns(Cursor data) {
        mTownsAdapter.swapCursor(data);
        mDestinationSpinner.setSelection(1);
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
    public void showSearchForm() {

    }

    @Override
    public void showSearchFormError() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(SearchBookingsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void submitSearchDetails(String originId, String destinationId, String travelDate) {
        Intent intent = new Intent(getContext(), SearchResultsActivity.class);
        intent.putExtra(SearchResultsActivity.EXTRA_ORIGIN_ID, originId);
        intent.putExtra(SearchResultsActivity.EXTRA_DESTINATION_ID, destinationId);
        intent.putExtra(SearchResultsActivity.EXTRA_TRAVEL_DATE, travelDate);
        startActivity(intent);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    private void updateDateInputLabel(){

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        mTravelDateInput.setText(sdf.format(myCalendar.getTime()));
    }

    private void showDatePickerDialog(){
        mMyDatePickerDialog.show(mFragmentManager, "Travel Date Picker");
    }

    private void startActivity(Intent intent) {
        Activity activity = getActivity(this);
        activity.startActivity(intent);
    }

}
