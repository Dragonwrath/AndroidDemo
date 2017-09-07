package com.example.dagger.subcomponent.factory;


///**
// * Factory 方法设置
// *  基本上与构建Component-Module-Entity关系一致
// *  首先定义自组件Subcomponent,定义Module，定义Entity
// *  之后在总组件上根据相应的自组件生产对应的子组件，例如
// *  // @Component
// *  // interface PersonComponent {
// *  // SubPersonComponent newRequestComponent(PersonModule requestModule);
// *  // }
// *  即可完成相应的注入
// */
public class PersonTest {
    public static void main(String[] args) {
        buildSubComponent();
    }

    private static void buildSubComponent() {
        Person person = DaggerPersonComponent.create().newRequestComponent(new PersonModule("5", "6")).inject();
        System.out.println(person.getNameAndAge());
    }

}