package com.example.tutorials.step_1;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class course_3 {
    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);
        System.out.println("usersJson = " + usersJson);
    }

}
class UserNaming {
    @SerializedName(value = "hehe",alternate = {"baibai"})
    String Name;
    String email_of_developer;
    boolean isDeveloper;
    int _ageOfDeveloper;

    public UserNaming(String norman, String s, boolean b, int i) {
        this.Name = norman;
        this.email_of_developer = s;
        this.isDeveloper = b;
        this._ageOfDeveloper = i;
    }
}
