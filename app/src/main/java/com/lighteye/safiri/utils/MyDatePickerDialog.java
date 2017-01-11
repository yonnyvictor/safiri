package com.lighteye.safiri.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by yonny on 7/26/16.
 */
public class MyDatePickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int mYear, mMonth, mDay;
    private SelectedDateCallback mSelectedDateCallback;

    public interface SelectedDateCallback{
        void onSelectedDate(int year, int month, int day);
    }

    public static MyDatePickerDialog newInstance(int year, int month, int day) {
        MyDatePickerDialog myDatePickerDialog = new MyDatePickerDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("day", day);
        myDatePickerDialog.setArguments(bundle);
        return myDatePickerDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mYear = getArguments().getInt("year");
        mMonth = getArguments().getInt("month");
        mDay = getArguments().getInt("day");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;

        mSelectedDateCallback.onSelectedDate(mYear, mMonth, mDay);
    }

    public void setSelectedDateCallback(SelectedDateCallback callback){
        mSelectedDateCallback = callback;
    }


}
