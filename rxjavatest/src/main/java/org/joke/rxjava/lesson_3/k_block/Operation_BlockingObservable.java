package org.joke.rxjava.lesson_3.k_block;

/**
 * BlockingObservable的方法
 * BlockingObservable 的方法不是将一个Observable变换为另一个，也不是过滤Observables，
 * 它们会打断Observable的调用链，会阻塞等待直到Observable发射了想要的数据，然后返回
 * 这个数据（而不是一个Observable）。
 * 要将一个Observable转换为一个 BlockingObservable ，你可以使
 * 用 Observable.toBlocking 或 BlockingObservable.from 方法。
 * Javadoc: Observable.toBlocking())
 * Javadoc: BlockingObservable.from(Observable))
 */

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.BlockingObservable;

/**
 * first
 * 要获取 BlockingObservable 的发射物，使用无参数的 first 方法。
 * Javadoc: BlockingObservable.first())
 *
 * first.p
 * 你也可以给 first 方法传递一个谓词函数用于获取满足条件的 BlockingObservable 。
 * Javadoc: BlockingObservable.first(Func1))
 *
 * firstOrDefault
 * 和过滤操作符一样，如果原始Observable没有数据， first 会抛出异
 * 常 NoSuchElementException ， firstOrDefault 会返回一个默认值。
 * Javadoc: BlockingObservable.firstOrDefault())
 *
 * firstOrDefault.p
 * firstOrDefault 同样也接受一个谓词函数作为参数，用于获取满足条件的第一项，如果没有
 * 满足条件的就返回默认值。
 * Javadoc: BlockingObservable.firstOrDefault(T, Func1))
 */

/**
 * takeFirst
 * takeFirst 与 first 类似，除了这一点：如果原始Observable没有发射任何满足条件的数
 * 据， first 会抛出一个 NoSuchElementException ， takeFist 会返回一个空的Observable（不
 * 调用 onNext() 但是会调用 onCompleted ）。
 * Javadoc: takeFirst(Func1))
 * single
 * single 操作符也与 first 类似，但是如果原始Observable在完成之前不是正好发射一次数
 * 据，它会抛出一个 NoSuchElementException 。
 * Javadoc: single())
 * single(Func1)
 * single 的变体接受一个谓词函数，发射满足条件的单个值，如果不是正好只有一个数据项满
 * 足条件，会以错误通知终止。
 * Javadoc: single(Func1))
 * singleOrDefault
 * 和 firstOrDefault 类似，但是如果原始Observable发射超过一个的数据，会以错误通知终
 * 止。
 * Javadoc: singleOrDefault(T))
 * singleOrDefault(T,Func1)
 * 和 firstOrDefault(T, Func1) 类似，如果没有数据满足条件，返回默认值；如果有多个数据满
 * 足条件，以错误通知终止。
 * Javadoc: singleOrDefault(Func1,T))
 * first系列的这几个操作符默认不在任何特定的调度器上执行。
 */

/**
 * next
 * next 操作符会阻塞直到 BlockingObservable 返回另外一个值，然后它返回那个值。你可以重
 * 复调用这个方法从 BlockingObservable 获取后续的数据项。以阻塞的方式高效的迭代获取它
 * 的发射物。
 * latest 操作符也是类似的，但是它不会阻塞等待下一个值，它立即返回最近发射的数据项，
 * 只在Observable还没有发射任何数据时会阻塞。
 * Javadoc: next())
 * Javadoc: latest())
 * mostRecent
 * mostRecent 操作符让你可以用类似的方式迭代一个 BlockingObservable ，但是它总是立即返
 * 回一个值，或者是默认值（如果 BlockingObservable 还没有发射任何数据），或者
 * 是 BlockingObservable 最近发射的数据项。
 * Javadoc: mostRecent(T))
 * forEach
 * BlockingObservable 类中也有一个类似的叫作 forEach 的方法。要使用这个方法，你首先需
 * 要使用 BlockingObservable.from 方法或 Observable.toBlocking 操作符将原始Observable转换
 * 为一个 BlockingObservable 。
 * BlockingObservable.forEach 接受单个函数作为参数，这个函数的作用类似于普通Observable
 * 订阅中的 onNext 函数。 forEach 自身会阻塞知道 BlockingObservable 完成，当它不阻塞时就
 * 是完成了，不是通过调用一个回调方法表示它完成了。如果遇到了错误它将抛出一
 * 个 RuntimeException （而不是调用一个类似于 onError 的回调方法）。
 * Javadoc: BlockingObservable.forEach(Action1))
 * 参见：
 * javadoc: BlockingObservable
 * javadoc: toBlocking()
 * javadoc: BlockingObservable.from()
 */
public class Operation_BlockingObservable {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
//        step_11();
        Observable.just(1).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "hahaha";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });

        Observable.just(1).compose(new Observable.Transformer<Integer, String>() {
            @Override
            public Observable<String> call(Observable<Integer> integerObservable) {

                return integerObservable.map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "what's the fuck";
                    }
                });
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });
        System.out.println();
    }

    //first()
    private static void step_1() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.first();
        System.out.println("first = " + first);
    }

    //first(func1)
    private static void step_2() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > -1;
            }
        });
        System.out.println("first = " + first);
    }

    //firstOrDefault(defaultValue)
    //返回默认值
    private static void step_3() {
        BlockingObservable<Integer> blocking = Observable.from(new ArrayList<Integer>()).toBlocking();
        Integer first = blocking.firstOrDefault(3);
        System.out.println("first = " + first);
    }

    //firstOrDefault(defaultValue,func1)
    //返回默认值
    private static void step_4() {
        BlockingObservable<Integer> blocking = Observable.from(new ArrayList<Integer>()).toBlocking();
        Integer first = blocking.firstOrDefault(3, new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > -1;
            }
        });
        System.out.println("first = " + first);
    }

    //single
    //如果不存在单个元素抛出异常
    private static void step_5() {
        ArrayList<Integer> iterable = new ArrayList<>(1);
        iterable.add(3);
        BlockingObservable<Integer> blocking = Observable.from(iterable).toBlocking();
        Integer first = blocking.single();
        System.out.println("first = " + first);
    }

    //single(func1)
    //判断发射的数据中是否只存在一个符合相应条件的元素
    private static void step_6() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.single(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 9;
            }
        });
        System.out.println("first = " + first);
    }

    //singleOrDefault(default,func1)
    private static void step_7() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.singleOrDefault(3,new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 9;
            }
        });
        System.out.println("first = " + first);
    }

    //next
    private static void step_8() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Iterable<Integer> next = blocking.next();
        for (Integer integer : next) {
            System.out.println("integer = " + integer);
        }
    }


    //latest
    private static void step_9() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Iterable<Integer> next = blocking.latest();
        for (Integer integer : next) {
            System.out.println("integer = " + integer);
        }
    }

    //mostRecent
    private static void step_10() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Iterable<Integer> integers = blocking.mostRecent(-1);
        for (Integer integer : integers) {
            System.out.println("integer = " + integer);
        }
    }

    //forEach
    private static void step_11() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        blocking.forEach(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
