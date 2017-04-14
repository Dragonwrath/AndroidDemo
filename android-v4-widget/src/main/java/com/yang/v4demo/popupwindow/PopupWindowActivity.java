package com.yang.v4demo.popupwindow;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.yang.v4demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopupWindowActivity extends AppCompatActivity implements PopupWindow.OnDismissListener {


    private static final String TAG = "PopupWindowActivity";
    @BindView(R.id.activity_popup_window) RelativeLayout mWindow;
    @BindView(R.id.button) Button mButton;
    private PopupWindow mPopupWindow;
    private float mDensity;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        ButterKnife.bind(this);

//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    Log.e(TAG, "onCreate: -------->"+i );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initPopupWindow(mButton);

            }
        },2000);

    }

    public void pop(View view) {

        mDensity = getResources().getDisplayMetrics().density;

        float parentX = view.getLeft();
        float parentY = view.getTop();

        int offsetX = mPopupWindow.getContentView().getWidth();
        int offsetY = mPopupWindow.getContentView().getHeight();

        Log.e(TAG, "mPopupWindow:offsetY ----->" + offsetY);
        float x = parentX + view.getWidth() / 2 - offsetX / 2;

        int end = (int) (parentY - offsetY + getStatusBarHeight());
        mPopupWindow.showAtLocation(view, Gravity.TOP | Gravity.CENTER, 0, end);

//        mPopupWindow.showAtLocation(view,Gravity.TOP|Gravity.LEFT, (int) x, (int) (parentY-offsetY +getStatusBarHeight()));
//        mPopupWindow.showAsDropDown(view ,0 ,0 ,Gravity.TOP);
    }

    private void initPopupWindow(View view) {
        View contentView = getLayoutInflater().inflate(R.layout.menu_popup, mWindow, false);
        mPopupWindow = new PopupWindow();
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setOnDismissListener(this);
        mPopupWindow.showAtLocation(view, Gravity.TOP | Gravity.CENTER, 0, 0);
//        mPopupWindow.dismiss();
    }

    @Override
    public void onDismiss() {
        Log.e(TAG, "-------->Dismis");
    }

    private int getStatusBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
}
