package com.joke.picassodemo;


import android.content.Context;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

public class Test4 {
    private Context mContext;
    private final Object tag = new Object();

    public Test4(Context context) {
        mContext = context;
    }

    public void initView() {
        ImageView imageView = new ImageView(mContext);
        ListView listView = new ListView(mContext);
        final Context context = mContext;
        Picasso.with(mContext)
                .load(Test2.eatFoodyImages[0])
                .tag(tag) //可以是任意对象
                .into(imageView);
        class SampleScrollListener implements AbsListView.OnScrollListener {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Picasso picasso = Picasso.with(context);
                picasso.resumeTag(tag);
                picasso.cancelTag(tag);
                picasso.pauseTag(tag);
                if (scrollState == SCROLL_STATE_IDLE ||
                        scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    picasso.resumeTag(tag); //通过相应的状态判断，picasso是否加载图片
                } else {
                    picasso.pauseTag(tag);//通过相应的状态判断，picasso是否加载图片
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        }
        listView.setOnScrollListener(new SampleScrollListener());

    }

}
