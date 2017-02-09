package com.joke.enctryptedfile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    private AES mAES;

    private static final String TAG = "MainActivity";
    private FileAsync mFileAsync;

    private static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mTv = (TextView)findViewById(R.id.tv_main);
        mTv.setClickable(true);
        mTv.setOnClickListener(this);
        mFileAsync = new FileAsync();
        mAES = new AES();

        key = BuildConfig.KERNEL_KEY +
                RSAUtils.KERNEL_KEY +
                getResources().getString(R.string.kernel_key);

    }

    private boolean isGrantExternalRW(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity)context;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getApplication().checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                activity.requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);

                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        try {
            InputStream source = getAssets().open("1.txt");
            mFileAsync.execute(source);
            mFileAsync.get(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void query(View view) {
        isGrantExternalRW(this);
    }

    public class  FileAsync extends AsyncTask<InputStream, String, String>{
        @Override
        protected String doInBackground(InputStream... params)  {
            File file =null;
            InputStream source = params[0];
            BufferedInputStream in = new BufferedInputStream(source);
            StringBuilder stringBuilder = new StringBuilder();

            try {
                File filesDir = MainActivity.this.getDir("files",MODE_WORLD_WRITEABLE);
                file = new File(filesDir, "decrypted");
                Log.i(TAG, "--->"+file.getAbsolutePath());
                if (!file.exists())
                    file.createNewFile();
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                RSAUtils.decryptData(in,out,RSAUtils.loadPrivateKey(key));
                stringBuilder.append(getFileMD5(file));

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    source.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert file != null;
                if (file.exists()) {
                    boolean delete = file.delete();
                }
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "---> "+s);
            Log.i(TAG, "---> "+s.equals("dd75da7b8abb8d3e3aeb38b8b940a651"));

        }
    }

    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
