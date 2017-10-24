package com.joker.tool.notification;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

	public static void defaultBigTextNotice(Context context,String summaryMessage,String bigTitle, String bigContent,@Nullable Intent intent) {
		NotificationCompat.Builder builder = getBuilder(context,summaryMessage,intent);
		NotificationCompat.BigTextStyle mBigTextStyle = new NotificationCompat.BigTextStyle(builder);
		mBigTextStyle.setBigContentTitle(bigTitle);
		mBigTextStyle.setSummaryText(summaryMessage);
		mBigTextStyle.bigText(bigContent);
		builder.setStyle(mBigTextStyle);
		sendNotification(context,builder);
	}

	@NonNull
	private static NotificationCompat.Builder getBuilder(Context context,String message,@Nullable Intent intent) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle(context.getResources().getString(R.string.app_name))
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
