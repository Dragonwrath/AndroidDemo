package com.example.dagger.step_2;


public class PersonTest {
    public static void main(String[] args) {
        buildSubComponent();
    }

    private static void buildSubComponent() {
        Person person = DaggerPersonComponent.create().newRequestComponent(new PersonModule("5","6")).inject();
        System.out.println(person.getNameAndAge());
    }

//    private static void buildDagger() {
//        final DaggerPersonComponent.Builder builder = DaggerPersonComponent.builder();
//        Person person = builder.personModule(new PersonModule("2","3")).build().inject();
//        System.out.println("person.getNameAndAge() = " + person.getNameAndAge());
//
//        person = builder.personModule(new PersonModule("23","33")).build().inject();
//        System.out.println("person.getNameAndAge() = " + person.getNameAndAge());
//
//        Person1Thread t1 = new Person1Thread(builder);
//        Person2Thread t2 = new Person2Thread(builder);
//        t1.start();
//        t2.start();
//    }

}
//
//class Person1Thread extends Thread{
//    DaggerPersonComponent.Builder builder;
//
//    public Person1Thread(DaggerPersonComponent.Builder builder) {
//        this.builder = builder;
//    }
//
//    @Override
//    public void run() {
//        super.run();
//        for (int i = 0; i < 100; i++) {
//            Person person = builder.personModule(new PersonModule("1","1")).build().inject();
//            System.out.println("person.getNameAndAge() = " + person.toString());
//        }
//    }
//}
//
//class Person2Thread extends Thread{
//    DaggerPersonComponent.Builder builder;
//
//    public Person2Thread(DaggerPersonComponent.Builder builder) {
//        this.builder = builder;
//    }
//
//    @Override
//    public void run() {
//        super.run();
//        for (int i = 0; i < 100; i++) {
//            Person person = builder.personModule(new PersonModule("2","2")).build().inject();
//            System.out.println("person.getNameAndAge() = " + person.toString());
//        }
//    }
//}
