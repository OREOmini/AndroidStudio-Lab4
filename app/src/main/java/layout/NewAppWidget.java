package layout;

import android.app.PendingIntent;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.wangyunwen.expriment4.MainActivity;
import com.example.wangyunwen.expriment4.R;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent clickInt = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickInt, 0);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        remoteViews.setOnClickPendingIntent(R.id.widget_image, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.new_app_widget);
        Bundle bundle = intent.getExtras();

        if(intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            remoteViews.setTextViewText(R.id.widget_name, bundle.getString("name"));
            remoteViews.setImageViewResource(R.id.widget_image, bundle.getInt("src"));
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(new ComponentName(context, NewAppWidget.class), remoteViews);
        }
        if(intent.getAction().equals("dynamic_widget")) {
            remoteViews.setTextViewText(R.id.widget_name, bundle.getString("msg"));
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(new ComponentName(context, NewAppWidget.class), remoteViews);
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

