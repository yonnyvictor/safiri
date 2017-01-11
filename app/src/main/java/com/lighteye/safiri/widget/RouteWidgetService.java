package com.lighteye.safiri.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by yonny on 8/12/16.
 */
public class RouteWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this, intent);
    }
}
