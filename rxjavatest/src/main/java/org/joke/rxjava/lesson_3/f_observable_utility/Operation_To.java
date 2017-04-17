package org.joke.rxjava.lesson_3.f_observable_utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.BlockingObservable;

/**
 * To
 * 将Observable转换为另一个对象或数据结构
 * ReactiveX的很多语言特定实现都有一种操作符让你可以将Observable或者Observable发射的
 * 数据序列转换为另一个对象或数据结构。它们中的一些会阻塞直到Observable终止，然后生
 * 成一个等价的对象或数据结构；另一些返回一个发射那个对象或数据结构的Observable。
 * 在某些ReactiveX实现中，还有一个操作符用于将Observable转换成阻塞式的。一个阻塞式的
 * Ogbservable在普通的Observable的基础上增加了几个方法，用于操作Observable发射的数据
 * 项。
 * getIterator
 * getIterator 操作符只能用于 BlockingObservable 的子类，要使用它，你首先必须把原始的
 * Observable转换为一个 BlockingObservable 。可以使用这两个操作
 * 符： BlockingObservable.from 或 the Observable.toBlocking 。
 * 这个操作符将Observable转换为一个 Iterator ，你可以通过它迭代原始Observable发射的数
 * 据集。
 * Javadoc: BlockingObservable.getIterator())
 *
 *
 * toFuture 操作符也是只能用于 BlockingObservable 。这个操作符将Observable转换为一个返
 * 回单个数据项的 Future ，如果原始Observable发射多个数据项， Future 会收到一
 * 个 IllegalArgumentException ；如果原始Observable没有发射任何数据， Future 会收到一
 * 个 NoSuchElementException 。
 * 如果你想将发射多个数据项的Observable转换为 Future ，可以这样
 * 用： myObservable.toList().toBlocking().toFuture() 。
 * Javadoc: BlockingObservable.toFuture())
 * toIterable
 * toFuture 操作符也是只能用于 BlockingObservable 。这个操作符将Observable转换为一
 * 个 Iterable ，你可以通过它迭代原始Observable发射的数据集。
 * Javadoc: BlockingObservable.toIterable())
 *
 *
 * toList
 * 通常，发射多项数据的Observable会为每一项数据调用 onNext 方法。你可以用 toList 操作
 * 符改变这个行为，让Observable将多项数据组合成一个 List ，然后调用一次 onNext 方法传
 * 递整个列表。
 * 如果原始Observable没有发射任何数据就调用了 onCompleted ， toList 返回的Observable会
 * 在调用 onCompleted 之前发射一个空列表。如果原始Observable调用了 onError ， toList 返
 * 回的Observable会立即调用它的观察者的 onError 方法。
 * toList 默认不在任何特定的调度器上执行。
 * Javadoc: toList())
 *
 *
 * toMap
 * toMap 收集原始Observable发射的所有数据项到一个Map（默认是HashMap）然后发射这个
 * Map。你可以提供一个用于生成Map的Key的函数，还可以提供一个函数转换数据项到Map存
 * 储的值（默认数据项本身就是值）。
 * toMap 默认不在任何特定的调度器上执行。
 * Javadoc: toMap(Func1))
 * Javadoc: toMap(Func1,Func1))
 * Javadoc: toMap(Func1,Func1,Func0))
 *
 *
 * toMultiMap
 * toMultiMap 类似于 toMap ，不同的是，它生成的这个Map同时还是一个 ArrayList （默认是
 * 这样，你可以传递一个可选的工厂方法修改这个行为）。
 * toMultiMap 默认不在任何特定的调度器上执行。
 * Javadoc: toMultiMap(Func1))
 * Javadoc: toMultiMap(Func1,Func1))
 * Javadoc: toMultiMap(Func1,Func1,Func0))
 * Javadoc: toMultiMap(Func1,Func1,Func0,Func1))
 *
 *
 * toSortedList
 * toSortedList 类似于 toList ，不同的是，它会对产生的列表排序，默认是自然升序，如果发
 * 射的数据项没有实现 Comparable 接口，会抛出一个异常。然而，你也可以传递一个函数作为
 * 用于比较两个数据项，这是 toSortedList 不会使用 Comparable 接口。
 * toSortedList 默认不在任何特定的调度器上执行。
 * Javadoc: toSortedList())
 * Javadoc: toSortedList(Func2))
 *
 *
 * nest
 * nest 操作符有一个特殊的用途：将一个Observable转换为一个发射这个Observable的
 * Observable。
 */
public class Operation_To {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    //BlockingObservable.getIterator())
    public static void main(String[] args) {
        init();
        step_6();

        System.out.println();
    }

    //通过toBlocking方法可以获取到相应的BlockingObservable，之后可以通过获取
    //BlockingObservable.getIterator()方法可以获取到相应的可迭代数据集
    private static void step_1() {
        Iterator<Integer> iterator = Observable.from(list).toBlocking().getIterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("next = " + next);
        }
    }

    //BlockingObservable.toFuture())
    //同样的还可以使用toFuture()的方法，
    private static void step_2() {
        Future<Integer> future = Observable.from(list).toBlocking().toFuture();
        try {
            Integer integer = future.get();
            System.out.println("integer = " + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //BlockingObservable.toIterable())
    //获取到相应的可迭代对象
    private static void step_3() {
        Iterable<Integer> integers = Observable.from(list).toBlocking().toIterable();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("next = " + next);
        }
    }

    //toList()
    //将多项数据的Observable组合成一个List，然后调用onNext方法传递整个列表
    private static void step_4() {
        Observable<List<Integer>> listObservable = Observable.from(list).toList();
        listObservable.subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = " + integers);
            }
        });
    }

    //toMap()
    //将多项数据的Observable组合成一个Observable，然后调用onNext方法发射数据
    //根据相应的key设置相应的Map
    private static void step_5() {
        Observable.from(list).toMap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {

                return integer % 2 == 0 ? "偶数": "奇数";
            }
        }).subscribe(new Action1<Map<String, Integer>>() {
            @Override
            public void call(Map<String, Integer> stringIntegerMap) {
                Set<String> strings = stringIntegerMap.keySet();
                for (String string : strings) {
                    Integer integer = stringIntegerMap.get(string);
                    System.out.println("integer = " + integer);
                }
            }
        });
    }

    //toMultiMap
    //根据相应的key ，然后提取相应的数据，获取到相应的集合
    private static void step_6() {
        Observable.from(list).toMultimap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {

                return integer % 2 == 0 ? "偶数": "奇数";
            }
        }).subscribe(new Action1<Map<String, Collection<Integer>>>() {
            @Override
            public void call(Map<String, Collection<Integer>> stringCollectionMap) {
                Set<String> strings = stringCollectionMap.keySet();
                for (String string : strings) {
                    Collection<Integer> integers = stringCollectionMap.get(string);
                    System.out.println("integers = " + integers);
                }
            }
        });
    }

    //toSortedList
    //根据相应的获取到的数据，转换成相应的排序的集合
    private static void step_7() {
        Observable<List<Integer>> observable = Observable.from(list).toSortedList();
        observable.subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = " + integers);
            }
        });
    }

    //nest
    private static void step_8() {
        Observable.from(list).nest().subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> integerObservable) {
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = " + integer);
                    }
                });
            }
        });
    }
}
