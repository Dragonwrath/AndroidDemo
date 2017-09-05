package com.joke.mvpdemo.welcome;

import android.graphics.ImageFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.joke.mvpdemo.R;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static com.google.common.base.Preconditions.checkNotNull;

public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View, View.OnLayoutChangeListener {

    private static final String TAG = "WelcomeActivity";

    WelcomeContract.Presenter mPresenter;
    public View mWindow;

    public boolean hide =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mWindow = getWindow().getDecorView();

        mWindow.addOnLayoutChangeListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WelcomeManager manager = new WelcomeManager(RemoteDataSource.getInstance(), LocalDataSource.getInstance());
        mPresenter = new WelcomePresenter(manager,this);
        final View container = findViewById(R.id.act_welcome);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    if (v.getSystemUiVisibility() == View.SYSTEM_UI_FLAG_VISIBLE) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE
                                    |SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                        }
                    } else {
                        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }

            }
        };

        container.setOnClickListener(listener);

    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        addStringBuilder("left",left);
        addStringBuilder("top",top);
        addStringBuilder("right",right);
        addStringBuilder("bottom",bottom);
        addStringBuilder("oldLeft",oldLeft);
        addStringBuilder("oldTop",oldTop);
        addStringBuilder("oldRight",oldRight);
        addStringBuilder("oldBottom",oldBottom);

        Log.e(TAG, "onLayoutChange: " + "\r\n" + builder.toString() );

        builder.delete(0,builder.length());
    }

    private StringBuilder builder = new StringBuilder();
    private void addStringBuilder(String attr, int attrV) {
        builder.append(attr)
                .append("--->")
                .append(attrV)
                .append("\r\n");
    }
}
