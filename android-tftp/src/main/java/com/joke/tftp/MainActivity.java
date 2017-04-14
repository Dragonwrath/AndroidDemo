package com.joke.tftp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.joke.tftp.tftp.TFTPImpl;

import org.apache.commons.net.tftp.TFTP;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String EXTRA_STORAGE = Environment.getExternalStorageDirectory().getAbsolutePath();

    private final TFTP mTFTP = new TFTP();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        Looper mainLooper = Looper.getMainLooper();
        Message message = handler.obtainMessage();
        Messenger messenger = new Messenger(handler);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String localFilePath = EXTRA_STORAGE + File.separator + "root.zip";
//                File file = new File(localFilePath);
//                if (file.exists()) {
//                    boolean idontknowwhere = TFTPUtil.uploadFile("192.168.0.111", "idontknowwhere", localFilePath, 60000);
//                    Log.i(TAG, "run:-----> "+idontknowwhere);
//                }
//            }
//        }).start();
    }


    private TFTPServer mTFTPServer;




    public void start(View view) {
        String state = Environment.getExternalStorageState();

        String absolutePath = Environment.getRootDirectory().getAbsolutePath();
        Log.i(TAG, "getRootDirectory: "+absolutePath);
        String absolutePath1 = Environment.getDataDirectory().getAbsolutePath();
        Log.i(TAG, "getDataDirectory: "+absolutePath1);

        String localFilePath = EXTRA_STORAGE;
        File file = new File(localFilePath);

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            file = Environment.getExternalStorageDirectory();
        } else {
            file =  new File(getFilesDir().getAbsolutePath());
        }
        boolean b = file.canRead();
        boolean a = file.canWrite();
        File[] list = file.listFiles();
        for (File s : list) {
            if (s.isFile())
                Log.i(TAG, "fileName: " +s.getAbsolutePath());
        }
        if (b & a )
            try {
                mTFTPServer = new TFTPServer(file,file, TFTPServer.ServerMode.GET_AND_PUT);
                mTFTPServer.launch();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void close(View view) {

        File filesDir = getFilesDir();

        File externalFilesDir = getExternalFilesDir(null);

        if (filesDir != null)
        Log.i(TAG, "getFilesDir---->"+filesDir.getAbsolutePath());

        boolean b = filesDir.isDirectory();
        if (b){
            File[] files = filesDir.listFiles();
            for (File file : files) {
                Log.i(TAG, "getFilesDir---->"+file.getAbsolutePath());
            }
        }

        if (mTFTPServer != null)
            try {
                mTFTPServer.finalize();

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
    }


    public void download(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String localFilePath = EXTRA_STORAGE + File.separator;
//
//                boolean idontknowwhere = TFTPUtil.downloadFile("192.168.0.111", localFilePath + "AML_AP2","AML_AP2", 60000);
//                Log.i(TAG, "run:-----> "+idontknowwhere);
//                File file1 = new File(localFilePath + "AML_AP2");
//                Log.i(TAG, "file exists:-----> "+file1.exists());
            new TFTPImpl("192.168.3.10",localFilePath + "AML_AP2","AML_AP2", 60000).receiveFile();

            }
        }).start();
    }

    public void upload(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String localFilePath = EXTRA_STORAGE + File.separator;
//                File file = new File(localFilePath);
//                if (file.exists()) {
//                    boolean idontknowwhere = TFTPUtil.uploadFile("192.168.0.111", "idontknowwhere", localFilePath, 60000);
//                    Log.i(TAG, "run:-----> "+idontknowwhere);
//                }
                new TFTPImpl("192.168.3.10",localFilePath + "root.zip","idontknowwhere", 60000).sendFile();

            }
        }).start();
    }

    public void requestPermission(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent, 42);
        }
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
//            }
//        }
    }


}
