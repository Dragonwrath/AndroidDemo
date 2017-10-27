package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

public class MyClass {
    private String jsonStr = "{\"value1\":4,\"value2\":\"abc\"}";


    public static void main(String[] args) {
        // Serialization
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .create();
        String s = gson.toJson(new BagOfPrimitives());
        System.out.println("s = " + s);
        TypeToken<String> typeToken = new TypeToken<String>(){};
//        for (int i = 0; i < 5; i++) {
//            BagOfPrimitives obj = new BagOfPrimitives();
//            obj.value1 = i;
//            String json = gson.toJson(obj);
//            System.out.println("json = " + json);
//        }
//
//        for (int i = 0; i < 5; i++) {
////            BagOfPrimitives o = gson.fromJson(jsonStr, BagOfPrimitives.class);
////            System.out.println("o = " + o.toString());
//        }



    }
    static class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;
        BagOfPrimitives() {
            // no-args constructor
        }
    }
    private static class DateTimeTypeConverter
            implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
        @Override
        public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }

        @Override
        public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return new DateTime(json.getAsJsonPrimitive().getAsString());
            } catch (IllegalArgumentException e) {
                // May be it came in formatted as a java.util.Date, so try that
                Date date = context.deserialize(json, Date.class);
                return new DateTime(date);
            }
        }
    }

}
