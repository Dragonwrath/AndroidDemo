package com.joke.bindservicedemo.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;

import static com.joke.bindservicedemo.jobscheduler.JobSchedulerActivity.MESSENGER_INTENT_KEY;
import static com.joke.bindservicedemo.jobscheduler.JobSchedulerTestActivity.TOAST_ONCE_FINISHED;

@RequiresApi(21)
public class JobSchedulerTestService extends JobService {

    private Messenger mActivityMessenger;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mActivityMessenger = intent.getParcelableExtra(MESSENGER_INTENT_KEY);
        return START_NOT_STICKY;
    }
    @Override
    public boolean onStartJob(JobParameters params){
        if (params != null) {
            new WorkThread(params).start();
            return true;
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private class WorkThread extends Thread {

        private JobParameters mParams;

        WorkThread(JobParameters params) {
            mParams = params;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Message obtain = Message.obtain();
                obtain.what = TOAST_ONCE_FINISHED;
                mActivityMessenger.send(obtain);
                jobFinished(mParams,false);
            } catch (InterruptedException | RemoteException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }



}
