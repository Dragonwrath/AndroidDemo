package com.yang.v4demo.viewdraggerhelper;


import android.content.Context;
import android.graphics.ColorMatrix;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 测试ViewDragHelper
 * Step-1
 *
 *  @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
         public boolean onTouchEvent(MotionEvent event) {
         mDragHelper.processTouchEvent(event);
         return true;
    }
 *
 *
 */
public class DragViewGroup extends ViewGroup {

    public ViewDragHelper mDragHelper;

    public DragViewGroup(Context context) {
        this(context, null, 0);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            /**
             * 根据指定的chil，以及相应的手势的动作id，来进行判断相应的事件,之后
             * @param child 对应的View
             * @param pointerId 相应的触控点Id
             * @return true 代表进行移动
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return false;
            }


            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child, left, dx);
            }

            /**
             * 垂直方向上计算移动的方法
             * @param child 对应的View
             * @param top 垂直方向上child移动的距离
             * @param dy  比较前一次的增量
             * @return 根据相应的返回值进行移动，默认为0，即不发生移动。
             * 通常情况下，返回相应的top/left即可，但如果存在padding，需要进行相应的计算
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return super.clampViewPositionVertical(child, top, dy);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);

            }

        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final ColorMatrix matrix = new ColorMatrix();
    }


    @Override
    public void computeScroll() {
        if(mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

}
