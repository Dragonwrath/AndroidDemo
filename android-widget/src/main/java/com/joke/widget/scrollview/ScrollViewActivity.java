package com.joke.widget.scrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.joke.widget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollViewActivity extends AppCompatActivity {

    @BindView(R.id.scrollView) ScrollView mScrollView;
    @BindView(R.id.activity_scroll_view) RelativeLayout mActivityScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        ButterKnife.bind(this);
    }
}
