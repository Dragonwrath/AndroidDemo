package com.joke.picassodemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

/**
 * Created by JunWei on 2017/2/15.
 */

public class Test3 {
    private static final int NOTIFICATION_ID = 1;
    private Context mContext;

    public Test3(Context context) {
        mContext = context;
    }

    public void initView(){
        // create RemoteViews
        final RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.remoteview_notification);

        // build notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher) //必须设置smallIcon
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setContent(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_MIN);

        final Notification notification = mBuilder.build();

        // set big content view for newer androids
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = remoteViews;
        }

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void intoPicassoView(){
        final RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.remoteview_notification);

        // build notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_MIN);

        final Notification notification = mBuilder.build();

        Picasso.with(mContext)
                .load(Test2.eatFoodyImages[0])
                .resize(100,100) //如果图片不符合要求，将抛出异常，最好重新设置图片的宽高
                .into(remoteViews, R.id.remoteview_notification_icon, NOTIFICATION_ID, notification);

    }


}
