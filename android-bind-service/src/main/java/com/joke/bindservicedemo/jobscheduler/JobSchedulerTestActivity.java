package com.joke.bindservicedemo.jobscheduler;

import android.app.Activity;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.joke.bindservicedemo.BuildConfig;
import com.joke.bindservicedemo.R;
import com.joke.bindservicedemo.remote_messenger.MessengerActivity;
import com.joke.bindservicedemo.remote_messenger.MessengerService;

import java.io.FileDescriptor;
import java.lang.ref.WeakReference;

@RequiresApi(21)
public class JobSchedulerTestActivity extends AppCompatActivity {

    public static final String PERMISSION = BuildConfig.APPLICATION_ID + ".JobSchedulerTest";

    public static final String MESSENGER_INTENT_KEY
            = BuildConfig.APPLICATION_ID + ".MESSENGER_INTENT_KEY";

    private static final int TOAST_TEST = 1;
    public static final int TOAST_ONCE_FINISHED = 2;
    public static final int TOAST_INFINITY = 3;

    ComponentName mJobServiceName;
    JobService mJobService;
    int jobId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler_test);

        //开启服务
        startJobService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //终止服务
        stopJobService();

    }

    private void startJobService(){
        mJobServiceName = new ComponentName(this,JobSchedulerTestService.class);
        Intent intent = new Intent(this, JobSchedulerTestService.class);
        IncomingHandler handler = new IncomingHandler(this);
        Messenger messenger = new Messenger(handler);
        intent.putExtra(MESSENGER_INTENT_KEY, messenger);
        startService(intent);

    }

    private void stopJobService() {
        Intent intent = new Intent(this, JobSchedulerTestService.class);
        stopService(intent);
    }

    private void bindJobService(){

    }

    private void unbindJobService() {
    }



    private JobInfo.Builder generateJob() {

        JobInfo.Builder builder = new JobInfo.Builder(jobId,mJobServiceName);
        builder.setMinimumLatency(5000);// 任务最少延迟时间

        builder.setPersisted(false); //是否在开机启动时进行重启启动任务
//        builder.setPeriodic(5000);  //设置任务默认运行间隔
        //JobInfo.NETWORK_TYPE_ANY 任何网络都可以运行，主要用于判断目前代码没只作用，只要不设置NONE即可
        //JobInfo.NETWORK_TYPE_UNMETERED 设置在WIFI网络下可以运行
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        //设置工作时间的最后生效事件，即使工作条件不满足（无空闲线程可调用），该工作依然会执行。
        //在定时任务中该项设置无效,该项功能与setPeriodic互斥，两个方法不能同时存在
        builder.setOverrideDeadline(15000);
        //指定运行作业时，是否需要插入设备，默认为false
        builder.setRequiresCharging(false);
        //设置是否运行在空闲模式
        builder.setRequiresDeviceIdle(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            builder.setPeriodic(24*60*60*1000,1000); //设置任务间隔时间，以及窗口时间
            //设置相应内容的Uri变化
            JobInfo.TriggerContentUri exUri = new JobInfo.TriggerContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ,1);
            JobInfo.TriggerContentUri inUri = new JobInfo.TriggerContentUri(MediaStore.Images.Media.INTERNAL_CONTENT_URI
                    ,1);
            builder.addTriggerContentUri(exUri);
            builder.addTriggerContentUri(inUri);
            builder.setTriggerContentUpdateDelay(1000);
            builder.setTriggerContentMaxDelay(1000);
        }
        return builder;
    }

    public void cancelAllJobs(View view) {
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancelAll();
    }

    public void startSingleTask(View view) {
        JobInfo.Builder builder = new JobInfo.Builder(jobId++,mJobServiceName);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        PersistableBundle extras = new PersistableBundle();

        builder.setExtras(extras);

        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(builder.build());
    }

    public void startRepeatTask(View view) {
        JobInfo.Builder builder = new JobInfo.Builder(jobId++,mJobServiceName);
        PersistableBundle extras = new PersistableBundle();

        builder.setExtras(extras);
        builder.setPeriodic(5000);

        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(builder.build());
    }

    private static class IncomingHandler extends Handler {
        private WeakReference<Activity> mReference;

        IncomingHandler(Activity act) {
            mReference = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case TOAST_ONCE_FINISHED:
                    Toast.makeText(mReference.get(),"Job Finished",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
