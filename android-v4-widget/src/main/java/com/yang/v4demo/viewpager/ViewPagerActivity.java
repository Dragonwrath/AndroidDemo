package com.yang.v4demo.viewpager;

import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yang.v4demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity implements PlusOneFragment.OnFragmentInteractionListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tab_host) TabLayout mTabHost;
    @BindView(R.id.container) ViewPager mContainer;

    private static final String TAG = "ViewPagerActivity";
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        FragmentManager manager = getSupportFragmentManager();
//        PlusOneFragment fragment = PlusOneFragment.newInstance("param-->1", "param-->2");
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.container, fragment);
//        transaction.show(fragment);
//        transaction.commit();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            titles.add("Title-" + i);
        }
        mPagerAdapter = new ViewPagerAdapter(manager, titles);
        mContainer.setAdapter(mPagerAdapter);

        mContainer.setPageTransformer(false,new ZoomOutPageTransformer());
        mContainer.addOnPageChangeListener(this);
        mTabHost.setupWithViewPager(mContainer);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, String.format(Locale.getDefault(),"onPageScrolled --position--%d--positionOffset--%f--positionOffsetPixels--%d",position,positionOffset,positionOffsetPixels));
    }

    private int currentPosition;
    @Override
    public void onPageSelected(int position) {
        currentPosition = position;


        Log.e(TAG, String.format(Locale.getDefault(),"onPageSelected --position--%d",position));

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        String stateStr = "";
        switch (state){
            case ViewPager.SCROLL_STATE_SETTLING:
                stateStr = "SCROLL_STATE_SETTLING";
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                stateStr = "SCROLL_STATE_IDLE";
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                stateStr = "SCROLL_STATE_DRAGGING";
                break;
        }
        Log.e(TAG, String.format(Locale.getDefault(),"onPageScrollStateChanged --state--%s",stateStr));
    }
}
