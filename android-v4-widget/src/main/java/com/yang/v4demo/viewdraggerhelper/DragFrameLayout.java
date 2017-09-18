package com.yang.v4demo.viewdraggerhelper;


import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class DragFrameLayout extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;
    private int mWidth;

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mMainView == child;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return dx > 0 ? left : 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mMainView.getLeft() < 500) {
                mViewDragHelper.smoothSlideViewTo(mMainView, 0 ,0);
                ViewCompat.postInvalidateOnAnimation(DragFrameLayout.this);
            } else {
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(DragFrameLayout.this);
            }
        }
    };

    public DragFrameLayout(Context context) {
        this(context, null, 0);
    }

    public DragFrameLayout(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public DragFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMainView = getChildAt(1);
        mMenuView = getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
