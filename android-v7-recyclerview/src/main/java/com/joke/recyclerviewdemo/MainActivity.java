package com.joke.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.joke.recyclerviewdemo.recycler.CartAdapter;
import com.joke.recyclerviewdemo.recycler.CartBean;
import com.joke.recyclerviewdemo.recycler.RecyclerViewListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewListener {

    private static final String TAG = "CartAdapter";


    RecyclerView mRecyclerView;
    private CartAdapter mAdapter;
    private ArrayList<CartBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CartBean cartBean = new CartBean();
            cartBean.title = "title----"+i;
            cartBean.description = "description----"+i;
            list.add(cartBean);
        }
        mAdapter = new CartAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnListener(this);
    }


    public void edit(View view) {
        mAdapter.setEdited();
        mAdapter.notifyDataSetChanged();
    }


    public void select_all(View view) {
        mAdapter.setSelectAll();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(View view , int position) {
        CheckBox box = (CheckBox) view.findViewById(R.id.check);
        box.setChecked(!box.isChecked());
    }

    @Override
    public void onItemLongClickListener(int position) {
        list.remove(position);
        mAdapter.notifyDataSetChanged();
    }


}
