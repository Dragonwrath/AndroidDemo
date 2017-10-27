package com.example;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.TreeSet;

class SomeObject {
    @SerializedName(value = "custom_naming",alternate = {"customnaming","name"})
    private final String someField;
    private final String someOtherField;

    public SomeObject(String a, String b) {
        this.someField = a;
        this.someOtherField = b;
    }
}
class Test{
    public static void main(String[] args) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()+ o1.length();
            }
        };
        TreeSet<String> strings = new TreeSet<>(comparator);
        strings.add("1");
        strings.add("12");
        strings.add("123");
        strings.add("1234");
        strings.add("12345");
        strings.add("123456");
        strings.add("1234567");
        strings.add("12345678");
        System.out.println("strings = " + strings);
//        SomeObject someObject = new SomeObject("first", "second");
//        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
//        String jsonRepresentation = gson.toJson(someObject);
//        System.out.println(jsonRepresentation);
    }
}