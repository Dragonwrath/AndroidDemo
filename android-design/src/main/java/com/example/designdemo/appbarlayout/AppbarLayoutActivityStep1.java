package com.example.designdemo.appbarlayout;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.designdemo.R;

import java.util.ArrayList;

public class AppbarLayoutActivityStep1 extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    CollapsingToolbarLayout mCollapsingToolbarLayout;
    Toolbar mToolbar;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_layout_step1);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("item---->" + i);
        }
        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter(this, list);
        mRecyclerView.setAdapter(adapter);

    }

    private void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_toolbar);
        mToolbar.setOnMenuItemClickListener(this);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(this, "增加",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_search:
                Toast.makeText(this, "搜索",Toast.LENGTH_SHORT).show();

                return true;
        }
        return false;
    }
}
