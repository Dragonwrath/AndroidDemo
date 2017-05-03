package com.joke.recyclerviewdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.joke.recyclerviewdemo.recycler.CartAdapter;
import com.joke.recyclerviewdemo.recycler.CartBean;
import com.joke.recyclerviewdemo.recycler.DefaultItemAnimator;
import com.joke.recyclerviewdemo.recycler.DividerItemDecoration;
import com.joke.recyclerviewdemo.recycler.RecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewListener {

    private static final String TAG = "CartAdapter";


    RecyclerView mRecyclerView;
    private CartAdapter mAdapter;
    private ArrayList<CartBean> list;
    private boolean selectAll;

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
        DefaultItemAnimator animator = new DefaultItemAnimator();
        //设置相应的动画的时间
        animator.setRemoveDuration(1000);
        animator.setChangeDuration(1000);
        animator.setMoveDuration(1000);
        animator.setAddDuration(1000);
        //指定相应的分割线
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(this,
                manager.getOrientation());
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.getPaint().setColor(Color.YELLOW);
        shapeDrawable.setIntrinsicWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        shapeDrawable.setIntrinsicHeight(15);
        mDividerItemDecoration.setDrawable(shapeDrawable);
        //添加相应的分割下
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        //设置相应的Item的动画
        mRecyclerView.setItemAnimator(animator);
        mAdapter.setOnListener(this);
    }


    public void edit(View view) {
        mAdapter.setEdited();
        mAdapter.notifyDataSetChanged();
    }


    public void select_all(View view) {
        selectAll = !selectAll;
        mAdapter.setSelectAll(selectAll);
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
        //mRecyclerView 只能使用notifyItemXX的方法进行相应的操作，否则，无法加载相应的动画
        mAdapter.notifyItemRemoved(position);
        //使用下面的notifyDataSetChanged则无法进行相应的动画刷新
        mAdapter.notifyDataSetChanged();
    }


    public void delete(View view) {
        List<Integer> positions = new ArrayList<>();
        int count = mRecyclerView.getChildCount();

        if (count > 0)
            for (int i = 0; i < count; i++) {
                View childView = mRecyclerView.getChildAt(i);
                CheckBox box = (CheckBox) childView.findViewById(R.id.check);
                if (box.isChecked()) {
                    positions.add(i);
                }
            }
        if (selectAll) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.remove(0);
                mAdapter.notifyItemRemoved(0);
            }
        } else {
            for (int i = 0, num = 0; i < positions.size(); i++, num++) {
                int index = positions.get(i);
                list.remove(index);
                mAdapter.notifyItemRemoved(index - num);
            }
        }
    }
}
