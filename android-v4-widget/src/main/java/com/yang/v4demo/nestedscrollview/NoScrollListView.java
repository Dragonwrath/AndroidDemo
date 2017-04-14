package com.yang.v4demo.nestedscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class   NoScrollListView extends ListView {
    private static final String TAG = "NoScrollListView";
    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerId = event.getPointerId(event.getPointerCount() - 1);
        int position = getLastVisiblePosition();
        View view = getChildAt(0);
        float x = view.getX();
        int scrollX = view.getScrollX();
        Log.e(TAG, "onTouchEvent: ---------->"+ pointerId +" ---------->" + x + " ---------->" + scrollX + " ---------->" + position );

        return super.onTouchEvent(event);
    }
}