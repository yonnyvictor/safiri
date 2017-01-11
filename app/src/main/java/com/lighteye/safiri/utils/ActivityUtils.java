package com.lighteye.safiri.utils;

/**
 * Created by yonny on 7/18/16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.view.View;

public class ActivityUtils {

    public static @NonNull Activity getActivity(View view) {
        Context context = view.getContext();

        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        throw new IllegalStateException("View " + view + " is not attached to an Activity");
    }

}
