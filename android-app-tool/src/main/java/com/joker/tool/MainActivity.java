package com.joker.tool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.joker.tool.utils.PhoneUtils;
import com.joker.tool.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text1) TextView mText1;
    @BindView(R.id.text2) TextView mText2;
    @BindView(R.id.text3) TextView mText3;
    @BindView(R.id.text4) TextView mText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int i = -1;
        mText1.setText("屏幕高度  " + ScreenUtils.getScreenHeight(this));
        mText2.setText("屏幕宽度  " + ScreenUtils.getScreenWidth(this));
        mText3.setText("状态栏高度  " + ScreenUtils.getStatusBarHeight(this));
        mText4.setText("虚拟栏高度  " + ScreenUtils.getVisualBarHeight(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
