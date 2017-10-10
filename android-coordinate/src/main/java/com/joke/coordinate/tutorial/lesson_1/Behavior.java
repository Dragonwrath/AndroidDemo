//package com.joke.coordinate.tutorial.lesson_1;
//
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.Rect;
//import android.os.Parcelable;
//import android.support.annotation.ColorInt;
//import android.support.annotation.FloatRange;
//import android.support.annotation.NonNull;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.v4.view.NestedScrollingParent;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.WindowInsetsCompat;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewParent;
//
///**
// * CoordinateLayout 的child view的互动行为插件
// * 用户能够使用一个Behavior来实现一个或者多个互动效果，
// * 这些互动效果包含拖动，切换，滑动或者其他
// */
//public abstract class Behavior<V extends View> {
//
//    public Behavior() {
//
//    }
//
//    /**
//     * 可以通过该构造函数来定义一些特有的行为的xml属性
//     * @param context 上下文
//     * @param attrs 属性
//     */
//    public Behavior(Context context, AttributeSet attrs) {
//    }
//
//    /**
//     * 在这个Behavior在一个LayoutParams的被实例化后使用
//     * 这个将在LayoutParams实例化之后，被调用，可以用来修改。
//     * @param params
//     */
//    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
//    }
//
//    /**
//     * 当Behaviro被它持有的LayoutParams实例销毁时调用
//     * 这将会在Behavior被LayoutParams实例通过{@link CoordinatorLayout.LayoutParams#setBehavior(CoordinatorLayout.Behavior)}
//     * 移除时候调用
//     * 但是并不会在持有的该Behavior的View被从CoordinatorLayout上移除时调用
//     */
//    public void onDetachedFromLayoutParams() {
//    }
//
//    /**
//     * 响应CoordinatorLayout 在它传递给子view之前
//     * Behaviors会使用该方法来监控在区域内的点击事件，直到其中的一个决定中止剩余的事件流
//     * 并且来执行响应的动作，在它被分配的子View上。
//     * 该方法会根据期望的条件返回相应的结果，一旦Behavior，那么其余的事件都将传递个{@link #onTouchEvent}方法
//     * 这个方法一般来说会被调用，而不管它的可见的behavior的child
//     * 如果你只是希望控制touch 事件 当child是可见的时候，你应该增加一个判断{@link View#isShown()}在指定的child上
//     * @param parent the parent view currently receiving this touch event
//     * @param child the child view associated with this Behavior
//     * @param ev the MotionEvent describing the touch event being processed
//     * @return true if this Behavior would like to intercept and take over the event stream.
//     */
//    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
//        return false;
//    }
//
//    /**
//     * 响应CoordinatorLayout的touch事件在相应的 {@link #onInterceptTouchEvent intercepting} 终止相应的传递的时候
//     * Behaviors可能会终止相应的touch事件来帮助CoordinatorLayout来操作它的子view。
//     * 举例来说一个Behavior允许用户开启或者关闭UI版块。
//     * 这个方法应该表现的是view布局的实际的突变状态
//     * 这个方法会被调用不管被分配这个behavior的child的可见状态。
//     * 如果你只希望控制touch事件，当child是可见的时候，你应该增加一个判断{@link View#isShown()}在child上
//     * @param parent the parent view currently receiving this touch event
//     * @param child the child view associated with this Behavior
//     * @param ev the MotionEvent describing the touch event being processed
//     * @return true if this Behavior handled this touch event and would like to continue
//     *         receiving events in this stream. The default always returns false.
//     *
//     */
//    public boolean onTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
//        return false;
//    }
//
//    /**
//     * 提供一个纱幕颜色，将被绘制在被分配的child后面。
//     * 一个纱幕被用来指示在这个view下面的其他元素不具备交互性和可操作性，使得用户的关注点和注意力在
//     * 纱幕之上
//     * 默认返回黑色
//     * @param parent the parent view of the given child
//     * @param child the child view above the scrim
//     * @return the desired scrim color in 0xAARRGGBB format. The default return value is
//     *         {@link Color#BLACK}.
//     * @see #getScrimOpacity(CoordinatorLayout, android.view.View)
//     *
//     */
//    @ColorInt
//    public int getScrimColor(CoordinatorLayout parent, V child) {
//        return Color.BLACK;
//    }
//
//
//    /**
//     * 指定当前纱幕的透明度
//     *
//     * <p>The default implementation returns 0.0f.</p>
//     *
//     * @param parent the parent view of the given child
//     * @param child the child view above the scrim
//     * @return the desired scrim opacity from 0.0f to 1.0f. The default return value is 0.0f.
//     *
//     */
//    @FloatRange(from = 0, to = 1)
//    public float getScrimOpacity(CoordinatorLayout parent, V child) {
//        return 0.f;
//    }
//
//    /**
//     * 决定给定的child view的交互命令是否应该被阻断
//     *
//     * <p>The default implementation returns true if
//     * {@link #getScrimOpacity(CoordinatorLayout, android.view.View)} would return > 0.0f.</p>
//     *
//     * @param parent the parent view of the given child
//     * @param child the child view to test
//     * @return true if {@link #getScrimOpacity(CoordinatorLayout, android.view.View)} would
//     *         return > 0.0f.
//     */
//
//    public boolean blocksInteractionBelow(CoordinatorLayout parent, V child) {
//        return getScrimOpacity(parent, child) > 0.f;
//    }
//
//    /**
//     * 确定提供的子视图是否具有另一个特定的兄弟视图作为一个布局的依赖
//     * 这个方法至少被调用一次用来相应布局请求。
//     * 如果它给予指定的child和依赖的view对返回true，父CoordinatorLayout会永远在依赖的child布局完成后，
//     * 在分布这个child，而不管child指令
//     * 在依赖的view布局或者位置发生变化时，调用{@link #onDependentViewChanged}
//     *
//     * @param parent the parent view of the given child
//     * @param child the child view to test
//     * @param dependency the proposed dependency of child
//     * @return true if child's layout depends on the proposed dependency's layout,
//     *         false otherwise
//     *
//     * @see #onDependentViewChanged(CoordinatorLayout, android.view.View, android.view.View)
//     */
//    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
//        return false;
//    }
//
//
//    /**
//     *
//     * 相应一个child依赖的view的改变
//     * 如果依赖view尺寸或者位置发生了改变，一个Behavior将使用这个方法来适当的更新这个child。
//     * 一个view的依赖决定于{@link #layoutDependsOn(CoordinatorLayout, android.view.View, android.view.View)}
//     * 或者如果child设置了其他的view作为他的锚点
//     * 注意：如果一个behavior通过这个方法改变了一个child的布局，它也能够重新构造正确的位置
//     * 通过@link #onLayoutChild(CoordinatorLayout, android.view.View, int) onLayoutChild}方法.
//     * onDependentViewChanged在每个child布局阶段，用户永远会产生依赖命令的过程开始，该方法都不会被调用
//     * 如果Behavior改变了child的尺寸或者位置，它将返回true
//     * 默认返回false
//     * @param parent the parent view of the given child
//     * @param child the child view to manipulate
//     * @param dependency the dependent view that changed
//     * @return true if the Behavior changed the child view's size or position, false otherwise
//     *
//     */
//    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
//        return false;
//    }
//
//    /**
//     * 在依赖view被移除的时候响应
//     * Behavior将会在这个时候调用该方法适当的跟新子View
//     * 一个view依赖于{@link #layoutDependsOn(CoordinatorLayout, android.view.View, android.view.View)}
//     * 或者它的child是另一个view的锚点的话
//     * @param parent the parent view of the given child
//     * @param child the child view to manipulate
//     * @param dependency the dependent view that has been removed
//     */
//    public void onDependentViewRemoved(CoordinatorLayout parent, V child, View dependency) {
//    }
//
//    /**
//     * 在父CoordinatorLayout在在测量给定的child的时候调用
//     * 这个方法一般被用于展示自定义或者修改一个子view的测量。
//     * 如果返回false，Behavior的实现将委托给标准的CoordinatorLayout的测量行为，在调用@link CoordinatorLayout#onMeasureChild(android.view.View, int, int, int, int)
//     * parent.onMeasureChild}.
//     * @param parent the parent CoordinatorLayout
//     * @param child the child to measure
//     * @param parentWidthMeasureSpec the width requirements for this view
//     * @param widthUsed extra space that has been used up by the parent
//     *        horizontally (possibly by other children of the parent)
//     * @param parentHeightMeasureSpec the height requirements for this view
//     * @param heightUsed extra space that has been used up by the parent
//     *        vertically (possibly by other children of the parent)
//     * @return true if the Behavior measured the child view, false if the CoordinatorLayout
//     *         should perform its default measurement
//     *
//     */
//    public boolean onMeasureChild(CoordinatorLayout parent, V child,
//                                  int parentWidthMeasureSpec, int widthUsed,
//                                  int parentHeightMeasureSpec, int heightUsed) {
//        return false;
//    }
//
//
//    /**
//     * 在父CoordinatorLayout 容器摆放给定的子View的时候调用
//     * 默认情况下返回false，交给父容器
//     * 如果一个Behavior实现了{@link #onDependentViewChanged(CoordinatorLayout, android.view.View, android.view.View)}
//     * 来改变view的位置，用来相应一个依赖view的改变。它同样应该实现onLayoutChild，来摆放相应依赖view
//     * onLayoutChild永远会在一个依赖view摆放好之后调用
//     * @param parent the parent CoordinatorLayout
//     * @param child child view to lay out
//     * @param layoutDirection the resolved layout direction for the CoordinatorLayout, such as
//     *                        {@link ViewCompat#LAYOUT_DIRECTION_LTR} or
//     *                        {@link ViewCompat#LAYOUT_DIRECTION_RTL}.
//     * @return true if the Behavior performed layout of the child view, false to request
//     *         default layout behavior
//     */
//    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
//        return false;
//    }
//
//    // Utility methods for accessing child-specific, behavior-modifiable properties.
//
//    /**
//     * 给相应的Behavior分配指定的tag，这个tag将被存储在child view的LayoutParams中
//     * @param child
//     * @param tag
//     */
//    public static void setTag(View child, Object tag) {
//        final CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        lp.mBehaviorTag = tag;
//    }
//
//    public static Object getTag(View child) {
//        final CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        return lp.mBehaviorTag;
//    }
//
//
//    /**
//     * 在一个CoordinatorLayout的后代尝试初始化一个nested scroll的时候调用
//     * 任何Behavior分配给一个CoordinatorLayout的直接child可能都会响应这个事件
//     * 并且返回true来代表这个CoordinatorLayout应该表现出作为一个可折叠的滚动的父容器。
//     * 只有在返回true的Behavior上才会接受之后的折叠滚动事件，
//     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
//     *                          associated with
//     * @param child the child view of the CoordinatorLayout this Behavior is associated with
//     * @param directTargetChild the child view of the CoordinatorLayout that either is or
//     *                          contains the target of the nested scroll operation
//     * @param target the descendant view of the CoordinatorLayout initiating the nested scroll
//     * @param nestedScrollAxes the axes that this nested scroll applies to. See
//     *                         {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
//     *                         {@link ViewCompat#SCROLL_AXIS_VERTICAL}
//     * @return true if the Behavior wishes to accept this nested scroll
//     *
//     * @see NestedScrollingParent#onStartNestedScroll(View, View, int)
//     */
//    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
//                                       V child, View directTargetChild, View target, int nestedScrollAxes) {
//        return false;
//    }
//
//    /**
//     * 当CoordinatorLayout已经接受了相应的折叠滚动之后调用
//     * 任何Behavior分配给CoordinatorLayout的任何直接子View都会选择接受这折叠滚动事件，作为 {@link #onStartNestedScroll}
//     * 的一部分。
//     * 每个返回true的Behavior 将接收随后的折叠滚动事件用来折叠滚动。
//     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
//     *                          associated with
//     * @param child the child view of the CoordinatorLayout this Behavior is associated with
//     * @param directTargetChild the child view of the CoordinatorLayout that either is or
//     *                          contains the target of the nested scroll operation
//     * @param target the descendant view of the CoordinatorLayout initiating the nested scroll
//     * @param nestedScrollAxes the axes that this nested scroll applies to. See
//     *                         {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
//     *                         {@link ViewCompat#SCROLL_AXIS_VERTICAL}
//     *
//     * @see NestedScrollingParent#onNestedScrollAccepted(View, View, int)
//     */
//    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, V child,
//                                       View directTargetChild, View target, int nestedScrollAxes) {
//        // Do nothing
//    }
//
//
//    /**
//     *  在一个折叠滚动终结的时候调用
//     * 任何Behavior分配给CoordinatorLayout的任何直接子View都会选择接受这折叠滚动事件，作为 {@link #onStartNestedScroll}
//     * 的一部分。
//     *  onStopNestedScroll 标记着一个单词折叠滚动事件的结束。
//     *  这是一个很好地地方来清楚所有的设计到折叠滚动状态的地方
//     *
//     * @param coordinatorLayout
//     * @param child
//     * @param target
//     */
//    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target) {
//        // Do nothing
//    }
//
//
//    /**
//     * 当一个折叠滚动正在发生的时候 ，也就是说当目标正在滚动或者尝试滚动的时候调用
//     *  任何Behavior分配给CoordinatorLayout的任何直接子View都会选择接受这折叠滚动事件，作为 {@link #onStartNestedScroll}
//     * 的一部分。
//     * 每个返回true的Behavior 将接收随后的折叠滚动事件用来折叠滚动。
//     *
//     * onNestedScroll每次都会回折叠滚动的child更新的小猴调用。
//     * 伴随着消费和不消费滚动组件，以像素提供
//     * 每个滚动事件响应将会接收到同同样的值
//     *
//     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
//     *                          associated with
//     * @param child the child view of the CoordinatorLayout this Behavior is associated with
//     * @param target the descendant view of the CoordinatorLayout performing the nested scroll
//     * @param dxConsumed horizontal pixels consumed by the target's own scrolling operation
//     * @param dyConsumed vertical pixels consumed by the target's own scrolling operation
//     * @param dxUnconsumed horizontal pixels not consumed by the target's own scrolling
//     *                     operation, but requested by the user
//     * @param dyUnconsumed vertical pixels not consumed by the target's own scrolling operation,
//     *                     but requested by the user
//     *
//     * @see NestedScrollingParent#onNestedScroll(View, int, int, int, int)
//     *
//     */
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target,
//                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        // Do nothing
//    }
//
//    /**
//     * 当目标消耗任何滚动距离之前，当正在进行的嵌套滚动即将更新时调用。
//     *  任何Behavior分配给CoordinatorLayout的任何直接子View都会选择接受这折叠滚动事件，作为 {@link #onStartNestedScroll}
//     * 的一部分。
//     * 每个返回true的Behavior 将接收随后的折叠滚动事件用来折叠滚动。
//     *
//     * onNestedPreScroll 将在每次折叠滚动发生时更新，通过折叠滚动的child，在这折叠滚动的child将消费掉响应的距离之前
//     * 每个相应的折叠滚动的Behavior都将接收到相同的值。
//     * CoordinatorLayout 将报告一次在每个Behavior相应的最大像素的消耗
//     * @param coordinatorLayout
//     * @param child
//     * @param target
//     * @param dx
//     * @param dy
//     * @param consumed
//     */
//    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target,
//                                  int dx, int dy, int[] consumed) {
//        // Do nothing
//    }
//
//
//    /**
//     * 在一次折叠滚动的child将开始fling状态或者一个动作将是fling状态时调用
//     *  任何Behavior分配给CoordinatorLayout的任何直接子View都会选择接受这折叠滚动事件，作为 {@link #onStartNestedScroll}
//     * 的一部分。
//     * 每个返回true的Behavior 将接收随后的折叠滚动事件用来折叠滚动。
//     *
//     * onNestedFling会被调用在当前折叠滚动的childview决定一次达到fling的条件的时候。
//     * 它会报告如果child本身消费了这个fling，
//     * 如果它没有，这个child就希望现实一个过度滚动的只是，
//     * 如果消费了fling将会返回true，如果child无法响应这个行为，可能选在不显示过度fling状态
//     *
//     *
//     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
//     *                          associated with
//     * @param child the child view of the CoordinatorLayout this Behavior is associated with
//     * @param target the descendant view of the CoordinatorLayout performing the nested scroll
//     * @param velocityX horizontal velocity of the attempted fling
//     * @param velocityY vertical velocity of the attempted fling
//     * @param consumed true if the nested child view consumed the fling
//     * @return true if the Behavior consumed the fling
//     *
//     * @see NestedScrollingParent#onNestedFling(View, float, float, boolean)
//     *
//     *
//     */
//    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, V child, View target,
//                                 float velocityX, float velocityY, boolean consumed) {
//        return false;
//    }
//
//    /**
//     * 当一个折叠滚动child将开始fling的时候调用
//     *  任何Behavior分配给CoordinatorLayout的任何直接子View都会选择接受这折叠滚动事件，作为 {@link #onStartNestedScroll}
//     * 的一部分。
//     * 每个返回true的Behavior 将接收随后的折叠滚动事件用来折叠滚动。
//     *
//     * onNestedPreFling会在目前折叠滚动child 具备fling条件的时候调用，但是它不能够是已经在fling状态。
//     * 一个Behavior将返回true，来表示它消耗了fling事件
//     * 如果至少有一个behavior返回true，这个fling将不会被这个child所展示出来
//     *
//     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
//     *                          associated with
//     * @param child the child view of the CoordinatorLayout this Behavior is associated with
//     * @param target the descendant view of the CoordinatorLayout performing the nested scroll
//     * @param velocityX horizontal velocity of the attempted fling
//     * @param velocityY vertical velocity of the attempted fling
//     * @return true if the Behavior consumed the fling
//     *
//     * @see NestedScrollingParent#onNestedPreFling(View, float, float)
//     *
//     */
//    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V child, View target,
//                                    float velocityX, float velocityY) {
//        return false;
//    }
//
//    /**
//     * 当窗口插入发生变化时，调用
//     * 任何分配给CoordinatorLayout的child的behavior，都可以选择控制分配给他们的view上的窗口改变事件
//     *
//     * @param coordinatorLayout
//     * @param child
//     * @param insets
//     * @return
//     */
//    @NonNull
//    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout,
//                                                  V child, WindowInsetsCompat insets) {
//        return insets;
//    }
//
//
//    /**
//     * 当分配个child view的behavior需要屏幕的特殊区域来摆放的时候调用
//     * 这个关联的方法，同{@link ViewParent#requestChildRectangleOnScreen(View, Rect, boolean)}.</p>
//     * 一致
//     * @param coordinatorLayout
//     * @param child
//     * @param rectangle
//     * @param immediate
//     * @return
//     */
//    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout,
//                                                   V child, Rect rectangle, boolean immediate) {
//        return false;
//    }
//
//
//    public void onRestoreInstanceState(CoordinatorLayout parent, V child, Parcelable state) {
//        // no-op
//    }
//
//    public Parcelable onSaveInstanceState(CoordinatorLayout parent, V child) {
//        return View.BaseSavedState.EMPTY_STATE;
//    }
//
//    /**
//     * 当一个view设置在躲避视图里面
//     * 这个方法允许一个behavior来更新需要躲避的区域。
//     * 这个区域应该在父容器的协调者系统中，同时在这个子child的区域中
//     * 如果不是，将抛出a {@link IllegalArgumentException}
//     * @param parent
//     * @param child
//     * @param rect
//     * @return
//     */
//    public boolean getInsetDodgeRect(@NonNull CoordinatorLayout parent, @NonNull V child,
//                                     @NonNull Rect rect) {
//        return false;
//    }
//
//}
