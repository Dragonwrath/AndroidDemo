package com.yang.v4demo.nestedscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yang.v4demo.R;

import butterknife.ButterKnife;

public class NestedScrollViewActivity extends AppCompatActivity {

//    private static final String TAG = "NestedScrollViewActivity";
//    @BindView(R.id.activity_nested_scroll_view)
//    NestedScrollView main;
//    @BindView(R.id.list)
//    NoScrollListView list;
//    private
//    ScrollImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);
        ButterKnife.bind(this);

//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            strings.add(TAG+"-->"+i);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
//        ListView list = (ListView) findViewById(R.id.list);
//        list.setVerticalScrollBarEnabled(true);
//        list.setAdapter(adapter);
    }
}
