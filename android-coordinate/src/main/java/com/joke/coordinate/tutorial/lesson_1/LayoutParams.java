//package com.joke.coordinate.tutorial.lesson_1;
//
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Rect;
//import android.support.annotation.IdRes;
//import android.support.annotation.Nullable;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.view.ViewCompat;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import java.lang.reflect.Constructor;
//import java.util.HashMap;
//import java.util.Map;
//
//public class LayoutParams extends ViewGroup.MarginLayoutParams {
//    CoordinatorLayout.Behavior mBehavior;
//    boolean mBehaviorResolved = false;
//    public int gravity = Gravity.NO_GRAVITY;
//
//    public int anchorGravity = Gravity.NO_GRAVITY;
//
//    /**
//     * 指定在父容器中水平标注的位置，以便child可以根据相应的情况警醒摆放
//     * 如果是这了{@link #setAnchorId(int)anchor} 那么该参数将被忽略
//     */
//    public int keyline = -1;
//
//    int mAnchorId = View.NO_ID;
//
//    /**
//     * 表明一个子view是使用什么样的{@link Gravity}值来插入到CoordinatorLayout中
//     * 其他的子view将会被设置为避免相同的插入边间，从而避免view的互相覆盖
//     */
//    public int insetEdge = Gravity.NO_GRAVITY;
//
//    public int dodgeInsetEdges = Gravity.NO_GRAVITY;
//
//    int mInsetOffsetX;
//    int mInsetOffsetY;
//
//    View mAnchorView;
//    View mAnchorDirectChild;
//
//    private boolean mDidBlockInteraction;
//    private boolean mDidAcceptNestedScroll;
//    private boolean mDidChangeAfterNestedScroll;
//
//    final Rect mLastChildRect = new Rect();
//
//    Object mBehaviorTag;
//
//
//    public LayoutParams(CoordinatorLayout.LayoutParams p) {
//        super(p);
//    }
//
//    public LayoutParams(ViewGroup.MarginLayoutParams p) {
//        super(p);
//    }
//
//    public LayoutParams(ViewGroup.LayoutParams p) {
//        super(p);
//    }
//
//    public LayoutParams(int width, int height) {
//        super(width, height);
//    }
//
//
//    @SuppressLint("PrivateResource")
//    LayoutParams(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//        final TypedArray a = context.obtainStyledAttributes(attrs,
//                android.support.design.R.styleable.CoordinatorLayout_Layout);
//
//        this.gravity = a.getInteger(
//                android.support.design.R.styleable.CoordinatorLayout_Layout_android_layout_gravity,
//                Gravity.NO_GRAVITY);
//        mAnchorId = a.getResourceId(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_anchor,
//                View.NO_ID);
//        this.anchorGravity = a.getInteger(
//                android.support.design.R.styleable.CoordinatorLayout_Layout_layout_anchorGravity,
//                Gravity.NO_GRAVITY);
//
//        this.keyline = a.getInteger(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_keyline,
//                -1);
//
//        insetEdge = a.getInt(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
//        dodgeInsetEdges = a.getInt(
//                android.support.design.R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
//        mBehaviorResolved = a.hasValue(
//                android.support.design.R.styleable.CoordinatorLayout_Layout_layout_behavior);
//        if (mBehaviorResolved) {
//            mBehavior = parseBehavior(context, attrs, a.getString(
//                    android.support.design.R.styleable.CoordinatorLayout_Layout_layout_behavior));
//        }
//        a.recycle();
//
//        if (mBehavior != null) {
//            // If we have a Behavior, dispatch that it has been attached
//            mBehavior.onAttachedToLayoutParams(this);
//        }
//    }
//
//    public int getAnchorId() {
//        return mAnchorId;
//    }
//
//    public void setAnchorId(@IdRes int id) {
//        invalidateAnchor();
//        mAnchorId = id;
//    }
//
//    @Nullable
//    public CoordinatorLayout.Behavior getBehavior() {
//        return mBehavior;
//    }
//
//    public void setBehavior(@Nullable CoordinatorLayout.Behavior behavior) {
//        if (mBehavior != behavior) {
//            if (mBehavior != null) {
//                // First detach any old behavior
//                mBehavior.onDetachedFromLayoutParams();
//            }
//
//            mBehavior = behavior;
//            mBehaviorTag = null;
//            mBehaviorResolved = true;
//
//            if (behavior != null) {
//                // Now dispatch that the Behavior has been attached
//                behavior.onAttachedToLayoutParams(this);
//            }
//        }
//    }
//
//    void setLastChildRect(Rect r) {
//        mLastChildRect.set(r);
//    }
//
//    Rect getLastChildRect() {
//        return mLastChildRect;
//    }
//
//
//    boolean checkAnchorChanged() {
//        return mAnchorView == null && mAnchorId != View.NO_ID;
//    }
//
//
//    /**
//     * 返回true的情况是，如果分配的Behavior之前阻止了在指定child下面的view的交互
//     * 从相应的触摸behavior是最后 {@link #resetTouchBehaviorTracking() reset}
//     *
//     * @see #isBlockingInteractionBelow(CoordinatorLayout, android.view.View)
//     * @return
//     */
//    boolean didBlockInteraction() {
//        if (mBehavior == null) {
//            mDidBlockInteraction = false;
//        }
//        return mDidBlockInteraction;
//    }
//
//    /**
//     * 确认是否已经分配的Behavior需要阻止在child view以下内容的交互
//     * 给定的子view应该是这个LayoutParams分配的对象
//     * 一旦交互被阻止，它将一直保持阻止状态，直到 {@link #resetTouchBehaviorTracking() reset}
//     * 被调用
//     * @param parent
//     * @param child
//     * @return
//     */
//    boolean isBlockingInteractionBelow(CoordinatorLayout parent, View child) {
//        if (mDidBlockInteraction) {
//            return true;
//        }
//
//        return mDidBlockInteraction |= mBehavior != null
//                ? mBehavior.blocksInteractionBelow(parent, child)
//                : false;
//    }
//
//    void resetTouchBehaviorTracking() {
//        mDidBlockInteraction = false;
//    }
//
//
//    void resetNestedScroll() {
//        mDidAcceptNestedScroll = false;
//    }
//
//    void acceptNestedScroll(boolean accept) {
//        mDidAcceptNestedScroll = accept;
//    }
//
//
//    boolean isNestedScrollAccepted() {
//        return mDidAcceptNestedScroll;
//    }
//
//    boolean getChangedAfterNestedScroll() {
//        return mDidChangeAfterNestedScroll;
//    }
//
//    void setChangedAfterNestedScroll(boolean changed) {
//        mDidChangeAfterNestedScroll = changed;
//    }
//
//    void resetChangedAfterNestedScroll() {
//        mDidChangeAfterNestedScroll = false;
//    }
//
//
//    /**
//     * 确认一个子view是否依赖于CoordinatorLayout中的其他view
//     * @param parent
//     * @param child
//     * @param dependency
//     * @return
//     */
//    boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
//        return dependency == mAnchorDirectChild
//                || shouldDodge(dependency, ViewCompat.getLayoutDirection(parent))
//                || (mBehavior != null && mBehavior.layoutDependsOn(parent, child, dependency));
//    }
//
//    /**
//     * 将相应的缓存的锚点view和直接子view置空，如果需要被重新使用，调用{@link #findAnchorView(CoordinatorLayout, android.view.View) found}
//     * 之后再使用
//     */
//    void invalidateAnchor() {
//        mAnchorView = mAnchorDirectChild = null;
//    }
//
//
//    /**
//     * 通过{@link #setAnchorId(int) anchor id} 来获取分配的锚点view
//     * 或者返回一直的缓存锚点
//     */
//    View findAnchorView(CoordinatorLayout parent, View forChild) {
//        if (mAnchorId == View.NO_ID) {
//            mAnchorView = mAnchorDirectChild = null;
//            return null;
//        }
//
//        if (mAnchorView == null || !verifyAnchorView(forChild, parent)) {
//            resolveAnchorView(forChild, parent);
//        }
//        return mAnchorView;
//    }
//
//    /**
//     * Determine the anchor view for the child view this LayoutParams is assigned to.
//     * Assumes mAnchorId is valid.
//     */
//    private void resolveAnchorView(final View forChild, final CoordinatorLayout parent) {
//        mAnchorView = parent.findViewById(mAnchorId);
//        if (mAnchorView != null) {
//            if (mAnchorView == parent) {
//                if (parent.isInEditMode()) {
//                    mAnchorView = mAnchorDirectChild = null;
//                    return;
//                }
//                throw new IllegalStateException(
//                        "View can not be anchored to the the parent CoordinatorLayout");
//            }
//
//            View directChild = mAnchorView;
//            for (ViewParent p = mAnchorView.getParent();
//                 p != parent && p != null;
//                 p = p.getParent()) {
//                if (p == forChild) {
//                    if (parent.isInEditMode()) {
//                        mAnchorView = mAnchorDirectChild = null;
//                        return;
//                    }
//                    throw new IllegalStateException(
//                            "Anchor must not be a descendant of the anchored view");
//                }
//                if (p instanceof View) {
//                    directChild = (View) p;
//                }
//            }
//            mAnchorDirectChild = directChild;
//        } else {
//            if (parent.isInEditMode()) {
//                mAnchorView = mAnchorDirectChild = null;
//                return;
//            }
//            throw new IllegalStateException("Could not find CoordinatorLayout descendant view"
//                    + " with id " + parent.getResources().getResourceName(mAnchorId)
//                    + " to anchor view " + forChild);
//        }
//    }
//
//    /**
//     * Verify that the previously resolved anchor view is still valid - that it is still
//     * a descendant of the expected parent view, it is not the child this LayoutParams
//     * is assigned to or a descendant of it, and it has the expected id.
//     */
//    private boolean verifyAnchorView(View forChild, CoordinatorLayout parent) {
//        if (mAnchorView.getId() != mAnchorId) {
//            return false;
//        }
//
//        View directChild = mAnchorView;
//        for (ViewParent p = mAnchorView.getParent();
//             p != parent;
//             p = p.getParent()) {
//            if (p == null || p == forChild) {
//                mAnchorView = mAnchorDirectChild = null;
//                return false;
//            }
//            if (p instanceof View) {
//                directChild = (View) p;
//            }
//        }
//        mAnchorDirectChild = directChild;
//        return true;
//    }
//
//    /**
//     * Checks whether the view with this LayoutParams should dodge the specified view.
//     */
//    private boolean shouldDodge(View other, int layoutDirection) {
//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) other.getLayoutParams();
//        final int absInset = GravityCompat.getAbsoluteGravity(lp.insetEdge, layoutDirection);
//        return absInset != Gravity.NO_GRAVITY && (absInset &
//                GravityCompat.getAbsoluteGravity(dodgeInsetEdges, layoutDirection)) == absInset;
//    }
//
//
//
//    CoordinatorLayout.Behavior parseBehavior(Context context, AttributeSet attrs, String name) {
//        return null;//    CoordinatorLayout内部方法调用相应的
//    }
//}
