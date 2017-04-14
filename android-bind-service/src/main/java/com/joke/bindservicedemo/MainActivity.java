package com.joke.bindservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v4.widget.PopupMenuCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.progress)
    SeekBar mProgress;

    MusicController mController;

    TextViewCompat mTextViewCompat;
    PopupMenuCompat mPopupMenuCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, MusicService.class);

        startService(intent);
        ControllerConn conn = new ControllerConn();
        bindService(intent,conn,BIND_AUTO_CREATE);

        mProgress.setOnSeekBarChangeListener(this);
    }


    

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mController.seekTo(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void play(View view) {
        mController.play();
    }

    public void stop(View view) {
        mController.stop();
    }

    class ControllerConn  implements ServiceConnection{

        String TAG = MusicService.MusicControllerImpl.class.getName();

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: ---->" +name.getClassName() );
            if (name.getClassName().equals(MusicService.class.getName())) {
                mController = (MusicController) service;
                Log.e(TAG, "onServiceConnected:  ---->" );
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (name.getClassName().equals(MusicService.MusicControllerImpl.class.getName())) {
                Log.e(TAG, "onServiceDisconnected:  ---->");
            }
        }
    }

}
