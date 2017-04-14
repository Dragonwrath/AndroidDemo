package com.joke.httpsdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


public class ProgressHUD extends Dialog {
    public ProgressHUD(Context context) {
        super(context);
    }

    public ProgressHUD(Context context, int theme) {
        super(context, theme);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    public static ProgressHUD show(Context context,
                     CharSequence message,
                     boolean immediate,
                     boolean cancelable,
                     OnCancelListener cancelListener) {
        ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
        dialog.setTitle("");
        dialog.setContentView(R.layout.ui_progress_hud);
        if (message == null | message.length() == 0) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView) dialog.findViewById(R.id.message);
            txt.setText(message);
        }
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.setCanceledOnTouchOutside(false);//触摸不取消
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.dimAmount = 0.2f;
            window.setAttributes(lp);
        }

        if(isActivityAlive(context)) {
            dialog.show();
        }
        return dialog;
    }

    private static  boolean isActivityAlive(Context context) {
        if (context == null)
            return false;
        if (context instanceof Activity) {
            Activity act = (Activity) context;
            if ( Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                if (act.isDestroyed() | act.isFinishing()) {
                    return false;
                }
            } else {
                if (act.isFinishing())
                    return false;
            }
        }
        return true;
    }
}
