package com.joke.rxjavademo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.joke.rxjavademo.module.elementary_1.ElementaryFragment;
import com.joke.rxjavademo.module.map_2.MapFragment;
import com.joke.rxjavademo.module.token_4.TokenFragment;
import com.joke.rxjavademo.module.zip_3.ZipFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(android.R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolBar);

        mViewPager.setAdapter(new PageFragmentAdapter(getSupportFragmentManager()));
        mTabs.setupWithViewPager(mViewPager);

        ValueAnimator animator = new ValueAnimator();
        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 1f);
        animation.setDuration(1000);
        animation.start();

    }

    class PageFragmentAdapter extends FragmentStatePagerAdapter {
        public PageFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ElementaryFragment();
                case 1:
                    return new MapFragment();
                case 2:
                    return new ZipFragment();
                case 3:
                    return new TokenFragment();
//                case 4:
//                    return new TokenAdvancedFragment();
//                case 5:
//                    return new CacheFragment();
                default:
                    return new ElementaryFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_elementary);
                case 1:
                    return getString(R.string.title_map);
                case 2:
                    return getString(R.string.title_zip);
                case 3:
                    return getString(R.string.title_token);
                case 4:
                    return getString(R.string.title_token_advanced);
                case 5:
                    return getString(R.string.title_cache);
                default:
                    return getString(R.string.title_elementary);
            }
        }
    }
}
