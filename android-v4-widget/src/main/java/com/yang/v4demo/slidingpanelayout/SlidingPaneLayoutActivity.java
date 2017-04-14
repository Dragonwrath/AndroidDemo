package com.yang.v4demo.slidingpanelayout;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yang.v4demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlidingPaneLayoutActivity extends AppCompatActivity {

    @BindView(R.id.panel)
    ListView mPanel;
    @BindView(R.id.content)
    ListView mContent;
    @BindView(R.id.activity_sliding_pane_layout)
    SlidingPaneLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_pane_layout);
        ButterKnife.bind(this);

        List<String> panels = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            panels.add("panel-->" + i);
            contents.add("content-->" + i);
        }
        ArrayAdapter<String> panelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, panels);
        ArrayAdapter<String> contentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contents);
        mPanel.setAdapter(panelAdapter);
        mContent.setAdapter(contentAdapter);
    }
}
