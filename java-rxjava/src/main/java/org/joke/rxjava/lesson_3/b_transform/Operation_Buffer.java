package org.joke.rxjava.lesson_3.b_transform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * 定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一
 * 个值。
 *
 * Buffer
 * Buffer 操作符将一个Observable变换为另一个，原来的Observable正常发射数据，变换产生
 * 的Observable发射这些数据的缓存集合。 Buffer 操作符在很多语言特定的实现中有很多种变
 * 体，它们在如何缓存这个问题上存在区别。
 * 注意：如果原来的Observable发射了一个 onError 通知， Buffer 会立即传递这个通知，而不
 * 是首先发射缓存的数据，即使在这之前缓存中包含了原始Observable发射的数据。
 * Window 操作符与 Buffer 类似，但是它在发射之前把收集到的数据放进单独的Observable，
 * 而不是放进一个数据结构。

 * buffer(count)
 * 以列表(List)的形式发射非重叠的缓存，每一个缓存至多包含来自原始
 * Observable的count项数据（最后发射的列表数据可能少于count项）
 * Javadoc: buffer(int))

 * buffer(count, skip)
 * 从原始Observable的第一项数据开始创建新的缓存，此后每当收
 * 到 skip 项数据，用 count 项数据填充缓存：开头的一项和后续的 count-1 项，它以列表
 * (List)的形式发射缓存，取决于 count 和 skip 的值，这些缓存可能会有重叠部分（比如skip <
 * count时），也可能会有间隙（比如skip > count时）。
 * Javadoc: buffer(int,int))
 *
 * buffer(bufferClosingSelector)
 * 当它订阅原来的Observable时， buffer(bufferClosingSelector) 开始将数据收集到一
 * 个 List ，然后它调用 bufferClosingSelector 生成第二个Observable，当第二个Observable
 * 发射一个 TClosing 时， buffer 发射当前的 List ，然后重复这个过程：开始组装一个新
 * 的 List ，然后调用 bufferClosingSelector 创建一个新的Observable并监视它。它会一直这
 * 样做直到原来的Observable执行完成。
 * Javadoc: buffer(Func0))
 *
 * buffer(boundary)
 * buffer(boundary) 监视一个名叫 boundary 的Observable，每当这个Observable发射了一个
 * 值，它就创建一个新的 List 开始收集来自原始Observable的数据并发射原来的 List 。
 * Javadoc: buffer(Observable))
 * Javadoc: buffer(Observable,int))
 *
 * buffer(bufferOpenings, bufferClosingSelector)
 * buffer(bufferOpenings, bufferClosingSelector) 监视这个叫 bufferOpenings 的
 * Observable（它发射 BufferOpening 对象），每当 bufferOpenings 发射了一个数据时，它就
 * 创建一个新的 List 开始收集原始Observable的数据，并将 bufferOpenings 传递
 * 给 closingSelector 函数。这个函数返回一个Observable。 buffer 监视这个Observable，当
 * 它检测到一个来自这个Observable的数据时，就关闭 List 并且发射它自己的数据（之前的那
 * 个List）。
 * Javadoc: buffer(Observable,Func1))
 *
 * buffer(timespan, unit[, scheduler])
 * buffer(timespan, unit) 定期以 List 的形式发射新的数据，每个时间段，收集来自原始
 * Observable的数据（从前面一个数据包裹之后，或者如果是第一个数据包裹，从有观察者订
 * 阅原来的Observale之后开始）。还有另一个版本的 buffer 接受一个 Scheduler 参数，默认
 * 情况下会使用 computation 调度器。
 * Javadoc: buffer(long,TimeUnit))
 * Javadoc: buffer(long,TimeUnit,Scheduler))
 *
 *
 * buffer(timespan, unit, count[, scheduler])
 * 每当收到来自原始Observable的count项数据，或者每过了一段指定的时间
 * 后， buffer(timespan, unit, count) 就以 List 的形式发射这期间的数据，即使数据项少于
 * count项。还有另一个版本的 buffer 接受一个 Scheduler 参数，默认情况下会使
 * 用 computation 调度器。
 * Javadoc: buffer(long,TimeUnit,int))
 * Javadoc: buffer(long,TimeUnit,int,Scheduler))
 *
 * buffer(timespan, timeshift, unit[, scheduler])
 * buffer(timespan, timeshift, unit) 在每一个 timeshift 时期内都创建一个新的 List ,然后用
 * 原始Observable发射的每一项数据填充这个列表（在把这个 List 当做自己的数据发射前，从
 * 创建时开始，直到过了 timespan 这么长的时间）。如果 timespan 长于 timeshift ，它发射的
 * 数据包将会重叠，因此可能包含重复的数据项。
 * 还有另一个版本的 buffer 接受一个 Scheduler 参数，默认情况下会使用 computation 调度
 * 器。
 * Javadoc: buffer(long,long,TimeUnit))
 * Javadoc: buffer(long,long,TimeUnit,Scheduler))
 *
 *
 * buffer-backpressure
 * 你可以使用 Buffer 操作符实现反压 backpressure （意思是，处理这样一个Observable：它
 * 产生数据的速度可能比它的观察者消费数据的速度快）。
 * Buffer操作符可以将大量的数据序列缩减为较少的数据缓存序列，让它们更容易处理。例如，
 * 你可以按固定的时间间隔，定期关闭和发射来自一个爆发性Observable的数据缓存。这相当
 * 于一个缓冲区。
 * 示例代码
 * Observable<List<Integer>> burstyBuffered = bursty.buffer(500, TimeUnit.MILLISECONDS);
 * 或者，如果你想更进一步，可以在爆发期将数据收集到缓存，然后在爆发期终止时发射这些
 * 数据，使用 Debounce 操作符给 buffer 操作符发射一个缓存关闭指示器( buffer closing
 * indicator )可以做到这一点。
 * 代码示例：
 * // we have to multicast the original bursty Observable so we can use it
 * // both as our source and as the source for our buffer closing selector:
 * Observable<Integer> burstyMulticast = bursty.publish().refCount();
 * // burstyDebounced will be our buffer closing selector:
 * Observable<Integer> burstyDebounced = burstyMulticast.debounce(10, TimeUnit.MILLISECON
 * DS);
 * // and this, finally, is the Observable of buffers we're interested in:
 * Observable<List<Integer>> burstyBuffered = burstyMulticast.buffer(burstyDebounced);
 *
 */

public class Operation_Buffer {
    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        init();
        step_2();
    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    //buffer(count)
    private static void step_1() {
        Observable.just(1,2,3,4,5,6).buffer(3)
                .subscribe(new Subscriber<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted ");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        System.out.println(integers.getClass().getSimpleName()+"integers.toString() = " + integers.toString());
                    }
                });
    }


    //buffer(bufferClosingSelector)
    @SuppressWarnings("unchecked")
    private static void step_2() {
        Observable.from(list).buffer(Observable.interval(2,0, TimeUnit.SECONDS))
                .subscribe(new Subscriber<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted ");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        System.out.println(integers.getClass().getSimpleName()+"integers.toString() = " + integers.toString());
                    }
                });
    }


}
