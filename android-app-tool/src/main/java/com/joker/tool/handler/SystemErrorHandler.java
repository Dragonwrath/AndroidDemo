package com.joker.tool.handler;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.joker.tool.MainActivity;
import com.sun.mail.smtp.SMTPMessage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

//系统错误控制类，当发生异常时，重启系统程序
//需要在主线程启动的时候，添加以下的内容
//SystemErrorHandler  eh= new SystemErrorHandler(this);
//Thread.setDefaultUncaughtExceptionHandler(eh);
public class SystemErrorHandler  implements Thread.UncaughtExceptionHandler{
    private static Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "SystemErrorHandler";
    private Application application;

    public SystemErrorHandler(Application application) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        int lastExitTime = 1;
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            File file = new File(application.getCacheDir().getAbsolutePath() + "playlist");
            if (file.exists()) {
                //noinspection all
                file.delete();
            }
            //设置主启动页面
            Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
            if (System.currentTimeMillis() - lastExitTime < 10000) {
                Process.killProcess(Process.myPid());
                return;
            }
            PendingIntent restartIntent = PendingIntent.getActivity(
                    application.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //退出程序
            AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
            // 1秒钟后重启应用
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 300,
                    restartIntent);
            setExitTime(application);
            Process.killProcess(Process.myPid());
        }
    }

    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }

        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        File file = new File(application.getCacheDir().getAbsolutePath() + "/err/");
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            PrintWriter writer = new PrintWriter(application.getCacheDir().getAbsolutePath() + "/err/" + System.currentTimeMillis() + ".log");
            ex.printStackTrace(writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //错误控制，可以用来发送相应的错误到指定的邮箱,或者到指定的后台
                sendErrorToMail(ex);
            }
        }).start();

        return true;
    }


    //使用mail.jar 帮助传递错误信息到指定的邮箱中。
    private boolean sendErrorToMail(Throwable ex) {
        String title = "error from " + "123456789";//获取相应的UUID
        String content = getDeviceInfo() + "  " +  Log.getStackTraceString(ex);
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.163.com");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, null);
            Transport transport = session.getTransport("smtp");

            transport.connect("smtp.163.com", 25, "remusic_log@163.com",
                    "remusiclog1");
            Message mailMessage = new SMTPMessage(session);
            Address from = new InternetAddress("remusic_log@163.com");
            mailMessage.setFrom(from);
            Address to = new InternetAddress("remusic_log@163.com");
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            mailMessage.setSubject(title);
            mailMessage.setSentDate(new Date());
            mailMessage.setText(content);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getDeviceInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("MODEL = ").append(Build.MODEL).append("\n");
        builder.append("PRODUCT = ").append(Build.PRODUCT).append("\n");
        builder.append("TAGS = ").append(Build.TAGS).append("\n");
        builder.append("CPU_ABI").append(Build.CPU_ABI).append("\n");
        builder.append("BOARD = ").append(Build.BOARD).append("\n");
        builder.append("BRAND = ").append(Build.BRAND).append("\n");
        builder.append("DEVICE = ").append(Build.DEVICE).append("\n");
        builder.append("DISPLAY = ").append(Build.DISPLAY).append("\n");
        builder.append("ID = ").append(Build.ID).append("\n");
        builder.append("VERSION.RELEASE = ").append(Build.VERSION.RELEASE).append("\n");
        builder.append("Build.VERSION.SDK_INT = ").append(Build.VERSION.SDK_INT).append("\n");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.append("VERSION.BASE_OS = ").append(Build.VERSION.BASE_OS).append("\n");
        }
        builder.append("Build.VERSION.SDK = ").append(Build.VERSION.SDK).append("\n");
        builder.append("APP.VERSION = ").append(getAPPVersionCode(application)).append("\n");
        builder.append("\n" + "log:" + "\n");

        return builder.toString();
    }

    private int getAPPVersionCode(Context ctx) {
        int currentVersionCode = 0;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            String appVersionName = info.versionName; // 版本名
            currentVersionCode = info.versionCode; // 版本号
            System.out.println(currentVersionCode + " " + appVersionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentVersionCode;
    }

    public void setExitTime(Application app) {
        //设置系统退出时间保存到SP中
    }
}
