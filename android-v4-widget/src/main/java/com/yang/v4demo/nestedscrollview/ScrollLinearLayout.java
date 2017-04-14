package com.yang.v4demo.nestedscrollview;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class ScrollLinearLayout extends LinearLayout  implements NestedScrollingChild,NestedScrollingParent {

    private static final String TAG = "ScrollLinearLayout";
    private NestedScrollingChildHelper mChildHelper;
    private NestedScrollingParentHelper mParentHelper;

    public ScrollLinearLayout(Context context) {
        super(context);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean startNestedScroll(int axes){
        Log.e(TAG, "startNestedScroll: ---------->" );

        return  super.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        Log.e(TAG, "stopNestedScroll: ---------->" );

        super.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        Log.e(TAG, "hasNestedScrollingParent: ---------->" );

        return super.hasNestedScrollingParent();
    }


    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.e(TAG, "dispatchNestedPreScroll: ---------->" );

        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Log.e(TAG, "dispatchNestedScroll: ---------->" );

        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.e(TAG, "dispatchNestedPreFling: ---------->" );

        return super.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "dispatchNestedFling: ---------->" );

        return super.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerId = event.getPointerId(event.getPointerCount() - 1);
        float x = getX();
        Log.e(TAG, "onTouchEvent: ---------->"+ pointerId +" ---------->" + x );

        return super.onTouchEvent(event);
    }


}
