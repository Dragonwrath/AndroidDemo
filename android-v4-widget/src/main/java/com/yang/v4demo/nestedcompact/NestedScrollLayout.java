package com.yang.v4demo.nestedcompact;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class NestedScrollLayout extends LinearLayout {

    private static final String TAG = "NestedScrollLayout";
    private NestedScrollingChildHelper childHelper;
    private NestedScrollingParentHelper parentHelper;
    private SwipeRefreshLayout mSwipe;
    public NestedScrollLayout(Context context) {
        this(context,null,0);
    }

    public NestedScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NestedScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    /**
    * Initial this view method
    */
    private void init() {
        childHelper  = new NestedScrollingChildHelper(this);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        String name = target.getClass().getName();
        Log.d(TAG, "onNestedScroll: " + name);
        View head = getChildAt(0);
        Log.e(TAG, "onNestedScroll: --"+dxConsumed+"--"+dxUnconsumed+"--"+dyConsumed +"--"+dyUnconsumed );
        if (head instanceof ImageView){

        }
    }
}
