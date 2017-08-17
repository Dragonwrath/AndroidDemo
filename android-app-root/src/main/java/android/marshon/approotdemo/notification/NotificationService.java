package android.marshon.approotdemo.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.marshon.approotdemo.MainActivity;
import android.marshon.approotdemo.R;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import java.lang.ref.WeakReference;

public class NotificationService extends Service {
    private final static int NOTIFICATION_START = 1;
    private final static int NOTIFICATION_CANCEL = 2;


    private static Notification notification;

    private Messenger mMessenger ;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (mMessenger == null)
            mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }

    private static class IncomingHandler extends Handler {

        private WeakReference<Service> service;

        private IncomingHandler(NotificationService service) {
            this.service = new WeakReference<Service>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NOTIFICATION_START:
                    buildRemoteViewNotification(service);
                    break;
                case NOTIFICATION_CANCEL:
                    cancelAllNotice(service);
                    break;

            }
        }

        private void buildNotification(WeakReference<Service> service) {
            Service context = service.get();
            Context app = context.getApplicationContext();

            NotificationManagerCompat manager = NotificationManagerCompat.from(context);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

            Intent intent = new Intent(app, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(app, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("MyNotifcation")
                    .setContentTitle("Notification");

            builder.setContentIntent(pendingIntent);
            notification = builder.build();

            manager.cancelAll();
            manager.notify(110, notification);
        }

        private void buildRemoteViewNotification(WeakReference<Service> service) {
            Service context = service.get();
            Context app = context.getApplicationContext();

            NotificationManagerCompat manager = NotificationManagerCompat.from(context);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);


            Intent intent = new Intent(app, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(app, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            final RemoteViews remoteViews = new RemoteViews(app.getPackageName(), R.layout.custome_notification);

            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("标题");
            builder.setContentText("内容");
            builder.setCustomContentView(remoteViews);
            remoteViews.setOnClickPendingIntent(R.id.notice_icon, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.notice_content_title, pendingIntent);
            notification = builder.build();

            manager.cancelAll();
            manager.notify(110, builder.build());
        }

        private void cancelAllNotice(WeakReference<Service> service) {
            Service context = service.get();
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            manager.cancelAll();
        }
    }

}
