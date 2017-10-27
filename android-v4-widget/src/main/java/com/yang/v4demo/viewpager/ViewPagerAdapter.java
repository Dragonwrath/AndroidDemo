package com.yang.v4demo.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;

    public ViewPagerAdapter(FragmentManager fm , List<String> list) {
        super(fm);
        titles = list;
    }

    @Override
    public Fragment getItem(int position) {
        return ContentFragment.newInstance(titles.get(position));
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


}
