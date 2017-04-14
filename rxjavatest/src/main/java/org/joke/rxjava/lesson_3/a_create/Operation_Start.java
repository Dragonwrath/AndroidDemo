package org.joke.rxjava.lesson_3.a_create;


import rx.Observable;
import rx.Subscriber;


//以下的内容属于可选包：rxjava-async
/**
 * 返回一个Observable，它发射一个类似于函数声明的值
 * 编程语言有很多种方法可以从运算结果中获取值，它们的名字一般叫 functions, futures,
 * actions, callables, runnables 等等。在 Start 目录下的这组操作符可以让它们表现得像
 * Observable，因此它们可以在Observables调用链中与其它Observable搭配使用。
 * Start 操作符的多种RxJava实现都属于可选的 rxjava-async 模块。
 * rxjava-async 模块包含 start 操作符，它接受一个函数作为参数，调用这个函数获取一个
 * 值，然后返回一个会发射这个值给后续观察者的Observable。
 * 注意：这个函数只会被执行一次，即使多个观察者订阅这个返回的Observable。
 */
public class Operation_Start {
    public static void main(String[] args) {
    }

    private static void step_1 () {
        Observable.just(1,2).startWith(2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }
                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }
}
