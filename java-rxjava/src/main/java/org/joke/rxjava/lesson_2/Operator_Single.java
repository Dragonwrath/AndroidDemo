package org.joke.rxjava.lesson_2;


/*
订阅Single只需要两个方法：
onSuccess - Single发射单个的值到这个方法
onError - 如果无法发射需要的值，Single发射一个Throwable对象到这个方法
Single只会调用这两个方法中的一个，而且只会调用一次，调用了任何一个方法之后，订阅关
系终止
*/
public class Operator_Single {
    public static void main(String[] args) {

    }
}
