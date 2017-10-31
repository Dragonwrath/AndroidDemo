package com.example.gson.tutorials.step_1;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

public class course_9 {
    public static void main(String[] args) {
        
        String userJson ="{\n" +
                "    \"year\": 116,\n" +
                "    \"month\": 5,\n" +
                "    \"day\": 21,\n" +
                "    \"age\": 26,\n" +
                "    \"email\": \"norman@futurestud.io\",\n" +
                "    \"isDeveloper\": true,\n" +
                "    \"name\": \"Norman\"\n" +
                "}";

        GsonBuilder gsonBuilder = new GsonBuilder();

        // change serialization for specific types
        JsonDeserializer<DeDate> deserializer = new JsonDeserializer<DeDate>() {
            @Override
            public DeDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();

                Date date = new Date(
                        jsonObject.get("year").getAsInt(),
                        jsonObject.get("month").getAsInt(),
                        jsonObject.get("day").getAsInt()
                );

                return new DeDate(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("email").getAsString(),
                        jsonObject.get("isDeveloper").getAsBoolean(),
                        jsonObject.get("age").getAsInt(),
                        date);
            }
        };        
        gsonBuilder.registerTypeAdapter(DeDate.class, deserializer);

        Gson customGson = gsonBuilder.create();
        DeDate customObject = customGson.fromJson(userJson, DeDate.class);
        System.out.println("customObject = " + customObject);
    }
}
class DeDate {
    private String name;
    private String email;
    private boolean isDeveloper;
    private int age;
    private Date registerDate;

    public DeDate(String name, String email, boolean isDeveloper, int age, Date registerDate) {
        this.name = name;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.age = age;
        this.registerDate = registerDate;
    }
}
