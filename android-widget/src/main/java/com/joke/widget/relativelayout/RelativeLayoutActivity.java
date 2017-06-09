package com.joke.widget.relativelayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.joke.widget.MainActivity;
import com.joke.widget.R;


/*
 * ------------------
 * |  view1         |
 * |-----------------
 * |  view2  | view3|
 * ------------------
 * 当view3设置如下属性，
 *      android:layout_width="96dp"
 *      android:layout_below="@id/view1"
 *      android:layout_alignParentRight="true"
 *
 * 当view 2 设置了如下属性时
 *
 *  android:layout_alignParentLeft="true"
 *  android:layout_toLeftOf="@id/view3"
 *  android:layout_below="@id/name"
 *
 *  我们就可以认为，由于View3指定了相应的宽度，以及固定为右边的位置，
 *  同时view2 固定左边的位置，在View1 之下，固定在View3 的左边
 *  因此无论view2 设置的宽度是多少，
 *  他都将占据这一行的除了分配给View3的其余空间，具体的内容设置到参考源码
 *
 */
public class RelativeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buildNotification();

            }
        },2000);
    }

    void buildNotification() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
         // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(RelativeLayoutActivity.this, MainActivity.class);
//
//        // The stack builder object will contain an artificial back stack for the
//         // started Activity.
//         // This ensures that navigating backward from the Activity leads out of
//         // your application to the Home screen.
//            TaskStackBuilder stackBuilder = TaskStackBuilder.create(RelativeLayoutActivity.this);
//    // Adds the back stack for the Intent (but not the Intent itself)
//            stackBuilder.addParentStack(RelativeLayoutActivity.class);
//    // Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );

        PendingIntent resultPendingIntent= PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    // mId allows you to update the notification later on.

        mBuilder.setOngoing(true);
        Notification notification = mBuilder.build();

        mNotificationManager.notify(101, notification);



    }

    TextView name;
    private int num;
    public void update(View view) {
        num++;
        if(name == null) {
            name = (TextView)findViewById(R.id.custom_date);
        }
        name.setText(num+"");
    }
}
