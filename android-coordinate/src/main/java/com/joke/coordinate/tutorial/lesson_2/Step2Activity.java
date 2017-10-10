package com.joke.coordinate.tutorial.lesson_2;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.joke.cordinate.R;

public class Step2Activity extends AppCompatActivity {
    private static final String TAG = "Step2Activity";
    private int lastOffset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);

        final TextView text = (TextView) findViewById(R.id.text);
        final AppBarLayout barLayout = (AppBarLayout) findViewById(R.id.barlayout);
        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    text.setText("heiheihei");
                } else {
                    text.setText("");
                }
                lastOffset = verticalOffset;
            }
        });

    }
}
