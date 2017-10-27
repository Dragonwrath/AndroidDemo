package com.joke.bindservicedemo;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

public class MusicService extends Service{
    private static final String TAG = "MusicService";
    Context mController;
    private final MediaPlayer mPlayer = new MediaPlayer();
    public MusicService(Context context) {
        mController = context;
    }

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicControllerImpl();
    }

    class MusicControllerImpl extends Binder implements MusicController{

        SeekBar mSeekBar;

        MusicControllerImpl() {
        }

        MusicControllerImpl(Context context){
            if (context instanceof Activity){
                mSeekBar = (SeekBar) ((Activity) context).findViewById(R.id.progress);
            }
        }

        @Override
        public void play() {
            Log.e(TAG, "play:  ---->");
        }

        @Override
        public void seekTo(int progress) {
            Log.e(TAG, "seekTo:  ---->"+progress);
        }

        @Override
        public void stop() {
            Log.e(TAG, "stop:  ---->");
        }
    }


}
