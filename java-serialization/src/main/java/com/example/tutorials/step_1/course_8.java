package com.example.tutorials.step_1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

//Custom Serialization-part-1
public class course_8 {
    public static void main(String[] args) {
        Merchant futureStudio = new Merchant(23, "Future Studio");
        Merchant coffeeShop = new Merchant(42, "Coffee Shop");

        // create a new subscription object and pass the merchants to it
        List<Merchant> subscribedMerchants = Arrays.asList(futureStudio, coffeeShop);
        UserSubscription subscription = new UserSubscription(
                "Norman",
                "norman@fs.io",
                26,
                true,
                subscribedMerchants);

        Gson gson = new Gson();
        String fullJSON = gson.toJson(subscription);
        System.out.println("fullJSON = " + fullJSON);

        //Custom JsonSerializer
        GsonBuilder builder = new GsonBuilder();
        JsonSerializer<Merchant> serializer = new JsonSerializer<Merchant>() {
            @Override
            public JsonElement serialize(Merchant src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jsonMerchant = new JsonObject();

                jsonMerchant.addProperty("Id", src.getId());

                return jsonMerchant;
            }
        };
        builder.registerTypeAdapter(Merchant.class, serializer);

        Gson customGson = builder.create();
        String customJSON = customGson.toJson(subscription);
        System.out.println("customJSON = " + customJSON);
    }
}

class Merchant {
    private int Id;
    private String name;

    // possibly more properties

    public Merchant(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }
}

class UserSubscription {
    String name;
    String email;
    int age;
    boolean isDeveloper;

    // new!
    List<Merchant> merchantList;

    public UserSubscription(String name, String email, int age, boolean isDeveloper, List<Merchant> merchantList) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
        this.merchantList = merchantList;
    }
}

class UserSimple {
    private String name;
    private String email;
    private boolean isDeveloper;
    private int age;
}
