package com.example.wangyunwen.expriment4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by wangyunwen on 16/10/19.
 */
public class StaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("static")) {
            Bundle bundle = intent.getExtras();

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);

            Resources res = context.getResources();
            Bitmap    bmp = BitmapFactory.decodeResource(res, bundle.getInt("src"));

            builder.setContentTitle("静态广播")
                    .setContentText(bundle.getString("name"))
                    .setLargeIcon(bmp);

            Intent intent1 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
            builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            manager.notify(0, notification);
        }
    }
}
