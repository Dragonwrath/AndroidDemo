package com.example.gson.test2;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;

public class Test {
    public static void main(String[] args) throws IOException {
        String json = "{\"name\":\"zhuning\",\"age\":\"24\"}";
        User user = new User();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.beginObject(); // throws IOException
        while (reader.hasNext()) {
            String s = reader.nextName();
            switch (s) {
                case "name":
                    user.name = reader.nextString();
                    break;
                case "age":
                    user.age = reader.nextInt(); //自动转换
                    break;
                case "email":
                    user.email = reader.nextString();
                    break;
            }
        }
        reader.endObject(); // throws IOException
        System.out.println(user.name);  // 怪盗kidou
        System.out.println(user.age);   // 24
        System.out.println(user.email); // ikidou@example.com

        Gson gson = new Gson();
        gson.toJson(json,System.out);
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(System.out));
        writer.beginObject() // throws IOException
                .name("name").value("怪盗kidou")
                .name("age").value(24)
                .name("email").nullValue() //演示null
                .endObject(); // throws IOException
        writer.flush(); // throws IOException


        GsonBuilder gsonBuilder = new GsonBuilder();
    }
}
