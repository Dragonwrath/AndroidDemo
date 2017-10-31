package com.example.gson.tutorials.step_1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class course_6 {
    public static void main(String[] args) {
        UserDayEnum userObject = new UserDayEnum("Norman", "norman@fs.io", true, 26, Day.SATURDAY);

        Gson gson = new Gson();
        String userWithEnumJson = gson.toJson(userObject);
        System.out.println("userWithEnumJson = " + userWithEnumJson);

        String s = "{" +
                "  \"email\": \"norman@futurestud.io\"," +
                "  \"age\": 26," +
                "  \"day\": LazyDay1" +
                "}";
        GsonBuilder builder = new GsonBuilder();
        UserDayEnum userDayEnum = gson.fromJson(s, UserDayEnum.class);
        System.out.println("userDayEnum = " + userDayEnum.day.getClass());
    }
}

class UserDayEnum {
    private String _name;
    private String email;
    private boolean isDeveloper;
    private int age;

    public Day day = Day.FRIDAY;

    public UserDayEnum(String _name, String email, boolean isDeveloper, int age, Day day) {
        this._name = _name;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.age = age;
        this.day = day;
    }
}
enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    @SerializedName("LazyDay1")
    SATURDAY,
    SUNDAY
}