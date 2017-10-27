package com.joke.ftpserverdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;


public class MainActivity extends AppCompatActivity {

    private FtpServer mFtpServer;
    private String ftpConfigDir= Environment.getExternalStorageDirectory().getAbsolutePath()+"/ftpConfig/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=(TextView)findViewById(R.id.tvText);
        String info="请通过浏览器或者我的电脑访问以下地址\n"+"ftp://"+getLocalIpAddress()+":2221\n";
        tv.setText(info);


    }

    public String getLocalIpAddress() {
        String strIP=null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        strIP= inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("msg", ex.toString());
        }
        return strIP;
    }

    private void copyResourceFile(int rid, String targetFile) throws IOException {
        Properties pro = new Properties();
        InputStream fin = getResources().openRawResource(rid);
        pro.load(fin);
        Object homedirectory = pro.get("ftpserver.user.admin.homedirectory");
        if (homedirectory != null)
            pro.setProperty("ftpserver.user.admin.homedirectory",ftpConfigDir);

        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(targetFile);
            pro.store(fos,"users.properties");

//            byte[] buffer = new byte[1024];
//            while( (length = fin.read(buffer)) != -1){
//                fos.write(buffer,0,length);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fin!=null){
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void Config1(){
//      Now, let's configure the port on which the default listener waits for connections.

        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();

//        String[] str ={"mkdir", ftpConfigDir};
//        try {
//            Process ps = Runtime.getRuntime().exec(str);
//            try {
//                ps.waitFor();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

        String filename=ftpConfigDir+"users.properties";//"/sdcard/users.properties";
        File files=new File(filename);

        userManagerFactory.setFile(files);
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        // set the port of the listener
        factory.setPort(2221);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        FtpServer server = serverFactory.createServer();
        this.mFtpServer = server;
        try {
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }

    void Config2(){
//      Now, let's make it possible for a client to use FTPS (FTP over SSL) for the default listener.


        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();

        // set the port of the listener
        factory.setPort(2221);

        // define SSL configuration
        SslConfigurationFactory ssl = new SslConfigurationFactory();
        ssl.setKeystoreFile(new File(ftpConfigDir+"ftpserver.jks"));
        ssl.setKeystorePassword("password");

        // set the SSL configuration for the listener
        factory.setSslConfiguration(ssl.createSslConfiguration());
        factory.setImplicitSsl(true);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File(ftpConfigDir+"users.properties"));

        serverFactory.setUserManager(userManagerFactory.createUserManager());

        // start the server
        FtpServer server = serverFactory.createServer();
        this.mFtpServer = server;
        try {
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(null != mFtpServer) {
            mFtpServer.stop();
            mFtpServer = null;
        }
    }

    private boolean isGrantExternalRW(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity)context;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
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

    public void config(View view) {
        isGrantExternalRW(this);
    }

    public void start(View view) {
        File f=new File(ftpConfigDir);
        if(!f.exists())
            f.mkdir();
        File file = new File(ftpConfigDir + "users.properties");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            copyResourceFile(R.raw.users, ftpConfigDir+"users.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config1();
    }
}
