package com.example.gson.tutorials.step_1;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

import jdk.nashorn.internal.runtime.Context;

//Gson Instance Creator
public class course_10 {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"age\": 26,\n" +
                "  \"email\": \"norman@fs.io\",\n" +
                "  \"isDeveloper\": true,\n" +
                "  \"name\": \"Norman\"\n" +
                "} ";

//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(
//                UserContext.class,
//                new UserContextInstanceCreator()
//        );
//        Gson customGson = gsonBuilder.create();
//
//        UserContext customObject = customGson.fromJson(json, UserContext.class);
//
//

    }

}
class UserContext {
    private String name;
    private String email;
    private boolean isDeveloper;
    private int age;

    // additional attribute, which is not part of the data model
    private Context context;

    public UserContext(Context context) {
        this.context = context;
    }

}
class UserContextInstanceCreator implements InstanceCreator<UserContext> {
    private Context context;

    public UserContextInstanceCreator(Context context) {
        this.context = context;
    }

    @Override
    public UserContext createInstance(Type type) {
        // create new object with our additional property
        UserContext userContext = new UserContext(context);

        // return it to gson for further usage
        return userContext;
    }
}