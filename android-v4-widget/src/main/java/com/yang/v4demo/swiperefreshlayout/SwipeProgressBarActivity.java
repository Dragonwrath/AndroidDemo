package com.yang.v4demo.swiperefreshlayout;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.yang.v4demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeProgressBarActivity extends AppCompatActivity implements  AbsListView.OnScrollListener {

    private static final String TAG = "SwipeProgressBarActivity";
    @BindView(R.id.activity_swipe_progeress_bar)
    SwipeRefreshLayout main;
//    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.list)
    ListView mListView;

    private List<String> datas;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_progeress_bar);
        RecyclerView
        ButterKnife.bind(this);
        datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            datas.add(TAG+"--->"+i);
        }
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        mListView.setOnScrollListener(this);

        mListView.setAdapter(mAdapter);

        //设置刷新时动画的颜色，可以设置4个
        main.setProgressBackgroundColorSchemeResource(android.R.color.white);
        main.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        main.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources()
                        .getDisplayMetrics()));
        main.setRefreshing(true);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        String string = "";
        switch (scrollState) {
            case SCROLL_STATE_IDLE:         string = "SCROLL_STATE_IDLE";  break;
            case SCROLL_STATE_TOUCH_SCROLL: string = "SCROLL_STATE_TOUCH_SCROLL"; break;
            case SCROLL_STATE_FLING:        string = "SCROLL_STATE_FLING"; break;
        }
        Log.e(TAG,"scrollState---->" + string);
        if (view instanceof ListView) {
            ListView list = (ListView) view;
            int lastPosition = list.getLastVisiblePosition();
            int size = datas.size();
            if (lastPosition == datas.size()-1 ) {
                ImageView v = new ImageView(this);
                v.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                RotateAnimation animation = new RotateAnimation(0,1080L);
                animation.setDuration(5000);
                animation.setInterpolator(new LinearInterpolator());
                v.setAnimation(animation);

                list.addFooterView(v);

                v.getAnimation().start();
//                for (int i = size; i < size + 10 ; i++) {
//                    datas.add(TAG+"--->"+i);
//                }
//                mAdapter.notifyDataSetChanged();
            }

            switch (scrollState) {
                case SCROLL_STATE_IDLE:         string = "SCROLL_STATE_IDLE";  break;
                case SCROLL_STATE_TOUCH_SCROLL: string = "SCROLL_STATE_TOUCH_SCROLL";
//                    View footView = list.getChildAt(list.getChildCount() - 1);
//                    Animation animation = footView.getAnimation();
//                    while (animation.hasEnded()) {
//                        list.removeFooterView(footView);
//                    }
                    break;
                case SCROLL_STATE_FLING:        string = "SCROLL_STATE_FLING"; break;
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public void setRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }
}
