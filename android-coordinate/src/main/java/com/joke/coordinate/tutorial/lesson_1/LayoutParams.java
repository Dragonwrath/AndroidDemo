package com.joke.coordinate.tutorial.lesson_1;


import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class LayoutParams extends ViewGroup.MarginLayoutParams {
    CoordinatorLayout.Behavior mBehavior;
    boolean mBehaviorResolved = false;
    public int gravity = Gravity.NO_GRAVITY;

    public int anchorGravity = Gravity.NO_GRAVITY;

    public int keyline = -1;

    int mAnchorId = View.NO_ID;

    public int insetEdge = Gravity.NO_GRAVITY;

    public int dodgeInsetEdges = Gravity.NO_GRAVITY;

    int mInsetOffsetX;
    int mInsetOffsetY;

    View mAnchorView;
    View mAnchorDirectChild;

    private boolean mDidBlockInteraction;
    private boolean mDidAcceptNestedScroll;
    private boolean mDidChangeAfterNestedScroll;

    final Rect mLastChildRect = new Rect();

    Object mBehaviorTag;



    public LayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
    }

    public LayoutParams(int width, int height) {
        super(width, height);
    }

    public LayoutParams(ViewGroup.MarginLayoutParams source) {
        super(source);
    }

    public LayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }
}
