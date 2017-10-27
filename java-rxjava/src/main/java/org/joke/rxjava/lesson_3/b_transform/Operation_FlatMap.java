package org.joke.rxjava.lesson_3.b_transform;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * FlatMap 将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并
 * 后放进一个单独的Observable
 * FlatMap 操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这
 * 个函数返回一个本身也发射数据的Observable，然后 FlatMap 合并这些Observables发射的数
 * 据，最后将合并后的结果当做它自己的数据序列发射。
 * 这个方法是很有用的，例如，当你有一个这样的Observable：它发射一个数据序列，这些数
 * 据本身包含Observable成员或者可以变换为Observable，因此你可以创建一个新的
 * Observable发射这些次级Observable发射的数据的完整集合。
 *
 ********************************************************************************
 * 注意： FlatMap 对这些Observables发射的数据做的是合并( merge )操作，因此它们可能是交
 * 错的。
 ********************************************************************************
 *
 * 在许多语言特定的实现中，还有一个操作符不会让变换后的Observables发射的数据交错，它
 * 按照严格的顺序发射这些数据，这个操作符通常被叫作 ConcatMap 或者类似的名字。
 * RxJava将这个操作符实现为 flatMap 函数。
 *
 ********************************************************************************
 * 注意：如果任何一个通过这个 flatMap 操作产生的单独的Observable调用 onError 异常终止
 * 了，这个Observable自身会立即调用 onError 并终止。
 ********************************************************************************
 *
 * 这个操作符有一个接受额外的 int 参数的一个变体。这个参数设置 flatMap 从原来的
 * Observable映射Observables的最大同时订阅数。当达到这个限制时，它会等待其中一个终止
 * 然后再订阅另一个。
 * Javadoc: flatMap(Func1))
 * Javadoc: flatMap(Func1,int))
 *
 *
 * 还有一个版本的 flatMap 为原始Observable的每一项数据和每一个通知创建一个新的
 * Observable（并对数据平坦化）。
 * 它也有一个接受额外 int 参数的变体。
 * Javadoc: flatMap(Func1,Func1,Func0))
 * Javadoc: flatMap(Func1,Func1,Func0,int))
 *
 *
 * 还有一个版本的 flatMap 会使用原始Observable的数据触发的Observable组合这些数据，然
 * 后发射这些数据组合。它也有一个接受额外 int 参数的版本。
 * Javadoc: flatMap(Func1,Func2))
 * Javadoc: flatMap(Func1,Func2,int))
 *
 *
 * flatMapIterable
 * flatMapIterable 这个变体成对的打包数据，然后生成Iterable而不是原始数据和生成的
 * Observables，但是处理方式是相同的。
 * Javadoc: flatMapIterable(Func1))
 * Javadoc: flatMapIterable(Func1,Func2))
 *
 *
 * concatMap
 * 还有一个 concatMap 操作符，它类似于最简单版本的 flatMap ，但是它按次序连接而不是合
 * 并那些生成的Observables，然后产生自己的数据序列。
 * Javadoc: concatMap(Func1))
 *
 *
 * switchMap
 * RxJava还实现了 switchMap 操作符。它和 flatMap 很像，除了一点：当原始Observable发射
 * 一个新的数据（Observable）时，它将取消订阅并停止监视产生执之前那个数据的
 * Observable，只监视当前这一个。
 * Javadoc: switchMap(Func1))
 *
 *
 * split
 * 在特殊的 StringObservable 类（默认没有包含在RxJava中）中还有一个 split 操作符。它将
 * 一个发射字符串的Observable转换为另一个发射字符串的Observable，只不过，后者将原始
 * 的数据序列当做一个数据流，使用一个正则表达式边界分割它们，然后合并发射分割的结
 * 果。
 *
 ********************************************************************************
 * 注意：
 * flatMapXxx方法的输出并没有相应的次序，因为它会将所有的输出结果合并起来
 * concatMap 方法的会根据输入的次数，进行相应的输出，并不会产生次序的错乱
 * switchMap 方法当新的数据进来的时候，如果前一个数据的转换并没有执行完，
 *              那么就会中断之前的操作
 ********************************************************************************
 */

public class Operation_FlatMap {
    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        init();
        step_4();
    }

    private static void init() {
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
    }

    //flatmap
    //其中第一个参数的func1 相当于onNext，发射数据，
    //   第二个参数的func1 相当于onError，处理错误数据
    //   第三个参数的fun0 相当于onComplete，完成
    private static void step_1() {
        final Observable<Integer> error = Observable.just(-1);
        final Observable<Integer> complete = Observable.just(-2);

        Observable<Integer> observable = Observable.from(list).flatMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.just(integer);
            }
        }, new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                return error;
            }
        }, new Func0<Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call() {
                return complete;
            }
        });

        observable.buffer(3,3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = " + integers);
            }
        });

    }

    //flatMap(Func1,Func2,int))
    //Func1 想当输入的原始数据之后，做出了第一次变换
    //Func2 相当于作出了第二次变换，
    private static void step_2() {

        Observable<String> observable = Observable.from(list).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just("--->" + integer);
            }
        }, new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return s;
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String integers) {
                System.out.println(integers);
            }
        });

    }


    //flatMapIterable
    //Func1，将输入的数据类型，进行转换，添加到集合类型中，在onNext之后，再从集合中取出所有的数据
    //Func2，将集合中的数据取出做第二次变换，可以获取到的的第一个参数为原始的参数
    private static void step_3() {
        Observable.from(list).flatMapIterable(new Func1<Integer, Iterable<Integer>>() {
            private final ArrayList<Integer> mSource = new ArrayList<>();

            @Override
            public Iterable<Integer> call(Integer integer) {
                mSource.add(integer+100);
                return mSource;
            }
        }, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer integer, Integer integer2) {
                return String.valueOf(integer+"--->"+integer2);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //concatMap
    private static void step_4() {
        Observable.from(list).concatMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.just(integer);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //switchMap
    //与flatMap类似，唯一的区别就是，当新数据过来之后，如果前一个数据的变换还没完成，
    //那么就只输出新的输出
    //也就是说并不会像flatMap一样合并相应的输出，不会丢失数据
    private static void step_5() {
        Observable.from(list).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.just(integer);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //split
    //在特殊的 StringObservable 类（默认没有包含在RxJava中）中还有一个 split 操作符。它将
    //一个发射字符串的Observable转换为另一个发射字符串的Observable，只不过，后者将原始
    //的数据序列当做一个数据流，使用一个正则表达式边界分割它们，然后合并发射分割的结
    //果。
    private static void step_6() {
    }
}
