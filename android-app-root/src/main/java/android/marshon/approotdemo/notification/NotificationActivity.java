package android.marshon.approotdemo.notification;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.marshon.approotdemo.R;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        bindNoticeService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindNoticeService();
    }

    private Messenger mMessenger;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(mMessenger == null)
                mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (mMessenger != null)
                mMessenger = null;
        }
    };
    private void bindNoticeService() {
        Intent intent = new Intent(this, NotificationService.class);
        bindService(intent,mConnection, BIND_AUTO_CREATE);
    }

    private void unbindNoticeService() {
        unbindService(mConnection);
    }

    public void one(View view) {
        Message message = Message.obtain(null, 1);
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void two(View view) {
    }
}
