package com.joker.tool.notification;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.joker.tool.R;

public class NotificationFactory{

	public final static int DEFAULT_NOTIFICATION_ID = 198;

	public static void defaultNotice(Context context,String message,@Nullable Intent intent) {
		NotificationCompat.Builder builder = getBuilder(context,message,intent);
		sendNotification(context,builder);
	}

	public static void defaultProgressNotice(Context context,String message, int max, int progress, @Nullable Intent intent) {
		NotificationCompat.Builder builder;
		if(progress >= max) {
			builder = getBuilder(context,message,intent);
		} else {
			builder = getBuilder(context, message, null);
		}
		builder.setProgress(max, progress, false);
		sendNotification(context,builder);
	}


	public static void defaultBigTextNotice(Context context,String message,@Nullable Intent intent) {
		NotificationCompat.Builder builder = getBuilder(context,message,intent);
		NotificationCompat.BigTextStyle mBigTextStyle = new NotificationCompat.BigTextStyle(builder);
		mBigTextStyle.setBigContentTitle("This is BigContentTitle");
		mBigTextStyle.setSummaryText("This is BigSummaryText");
		mBigTextStyle.bigText("This is BigText \n 如果。所有的伤痕都能够痊愈。 如果。所有的真心都能够换来真意。 如果。所有的相信都能够坚持。如果。所有的情感都能够完美。如果。依然能相遇在某座城。单纯的微笑。微微的幸福。肆意的拥抱。 该多好。可是真的只是如果。");
		builder.setStyle(mBigTextStyle);
		sendNotification(context,builder);
	}
	@NonNull
	private static NotificationCompat.Builder getBuilder(Context context,String message,@Nullable Intent intent) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setSmallIcon(R.mipmap.ic_launcher).
				setContentTitle(context.getResources().getString(R.string.app_name))
				.setContentText(message);
		if(intent != null) {
			PendingIntent pendingIntent = PendingIntent.getActivity(context,
					DEFAULT_NOTIFICATION_ID,
					intent,PendingIntent.FLAG_UPDATE_CURRENT);
			builder.setContentIntent(pendingIntent);
		}
		return builder;
	}

	private static void sendNotification(Context context,NotificationCompat.Builder builder) {
		Notification notification = builder.build();
		NotificationManagerCompat manager = NotificationManagerCompat.from(context);
		manager.notify(DEFAULT_NOTIFICATION_ID, notification);
	}
}
