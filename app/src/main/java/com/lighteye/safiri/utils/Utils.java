package com.lighteye.safiri.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Patterns;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yonny on 7/15/16.
 */
public class Utils {

    /**
     * Format the date with SimpleDateFormat
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Context mContext = null;


    /**
     * Public constructor that takes mContext for later use
     */
    public Utils(Context con) {
        mContext = con;
    }


    public static boolean isEmailValid(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return isGoodEmail;
    }

    public static boolean isUserNameValid(String userName) {
        return true;
    }

    public static boolean isPasswordValid(String password) {
        boolean isValid = false;
        if(password.length() > 5)
            isValid = true;
        return isValid;
    }

    public static long convertTimeString(String stringTime){
        long timeInMilliseconds = -1;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        try {
            Date date = sdf.parse(stringTime);
            timeInMilliseconds = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }

    public static long convertDateString(String stringDate){
        long timeInMilliseconds = -1;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            Date date = sdf.parse(stringDate);
            timeInMilliseconds = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }

    public static String convertMilliSecondsToDateString(long milliSeconds){
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }

    public static String convertMilliSecondsToDateString(long milliSeconds, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }

    public static String millisOfDayString(int millisOfDay){
        LocalTime localTime = new LocalTime(millisOfDay, DateTimeZone.UTC);
        return localTime.toString(Constants.TIME_FORMAT);
    }

    public static int getDayOfWeek(String stringDate){
        int dayOfWeek = -1;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            Date date = sdf.parse(stringDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dayOfWeek;
    }

    public static String generateBookingPushId(String routeKey){
        String key = null;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_LOCATION_BOOKINGS)
                .child(routeKey);
        DatabaseReference newRef = ref.push();

        key = newRef.getKey();

        return key;
    }

    public static String getUserId(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String userId = sp.getString(Constants.KEY_UID, null);

        return userId;
    }

    public static Bundle activityTransition(Context context){
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeCustomAnimation(context,
                android.R.anim.fade_in, android.R.anim.fade_out);
        return activityOptions.toBundle();
    }

    public static String routeSelectionDay(String day){
        String selectionDay = "";
        int dayOfWeek = Utils.getDayOfWeek(day);
        switch (dayOfWeek){
            case Calendar.SUNDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_SUN + " = ?";
                break;
            case Calendar.MONDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_MON + " = ?";
                break;
            case Calendar.TUESDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_TUE + " = ?";
                break;
            case Calendar.WEDNESDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_WED + " = ?";
                break;
            case Calendar.THURSDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_THU + " = ?";
                break;
            case Calendar.FRIDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_FRI + " = ?";
                break;
            case Calendar.SATURDAY:
                selectionDay = SafiriPersistenceContract.RoutesEntry.COLUMN_SAT + " = ?";
                break;
        }
        return selectionDay;
    }

    public static String todayString(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String today = sdf.format(calendar.getTime());

        return today;
    }
}
