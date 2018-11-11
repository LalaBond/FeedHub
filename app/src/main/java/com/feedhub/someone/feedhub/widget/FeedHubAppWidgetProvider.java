package com.feedhub.someone.feedhub.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.feedhub.someone.feedhub.MainActivity;
import com.feedhub.someone.feedhub.R;

/**
 * Implementation of App Widget functionality.
 */
public class FeedHubAppWidgetProvider extends AppWidgetProvider {

    private static RemoteViews views;

//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//                                int appWidgetId) {
//
//        CharSequence widgetText = context.getString(R.string.appwidget_text);
//        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.feed_hub_app_widget);
//        views.setTextViewText(R.id.titleTV, widgetText);
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);

            try {
              // Create an Intent to launch ExampleActivity
                Intent intent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                views = new RemoteViews(context.getPackageName(), R.layout.feed_hub_app_widget);
                Intent serviceIntent = new Intent(context, FeedhubWidgetViewService.class);

                views.setRemoteAdapter(R.id.widget_list, serviceIntent);
                // views.setOnClickPendingIntent(R.id.button, pendingIntent);

                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views);

            } catch (Exception e) {

                Log.e("$lala error -> ", e.toString());
            }

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

