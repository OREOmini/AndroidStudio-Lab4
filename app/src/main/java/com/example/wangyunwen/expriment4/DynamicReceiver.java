package com.example.wangyunwen.expriment4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import layout.NewAppWidget;

/**
 * Created by wangyunwen on 16/10/20.
 */
public class DynamicReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("dynamic_receiver")) {
            Bundle bundle = intent.getExtras();

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

            Resources res = context.getResources();
            Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.dynamic);

            builder.setContentTitle("动态广播")
                    .setContentText(bundle.getString("msg"))
                    .setTicker("notification")
                    .setSmallIcon(R.drawable.dynamic)
                    .setLargeIcon(bmp);

            Intent intent1 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
            builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            manager.notify(0, notification);

            //widget
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            remoteViews.setTextViewText(R.id.widget_name, bundle.getString("msg"));
            remoteViews.setImageViewResource(R.id.widget_image, R.drawable.dynamic);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, NewAppWidget.class), remoteViews);
        }
    }
}
