package com.joke.httpsdemo.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GsonUtil {
    private static Gson gson = null;
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        if (gson == null ) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
        throw new AssertionError("this class can't be instantiated");
    }

    public static String objectToJson(@NonNull Object ts) {
        if (gson != null)
            return gson.toJson(ts);
        return null;
    }

    public static String objectToJsonDateSerializer(Object ts,
                                                    final String dateFormat) {
        gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class,
                new JsonSerializer<Date>() {
                    @Override
                    public JsonElement serialize(Date src,
                                                 Type typeOfSrc,
                                                 JsonSerializationContext context) {

                        return new JsonPrimitive(new SimpleDateFormat(dateFormat, Locale.getDefault()).format(src));
                    }
                })
                .setDateFormat(dateFormat)
                .create();
        return gson.toJson(ts);
    }

    public static List<?> jsonToList(@NonNull String jsonStr) {
        List<?> objectList = null;
        Type type = new TypeToken<List<?>>() {
        }.getType();
        objectList = gson.fromJson(jsonStr,type);
        return objectList;
    }


    public static <T> List<T> jsonToArrayList(String jsonStr, Class<T> clazz) {
        //创建一个JsonParser
        JsonParser parser = new JsonParser();

        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(jsonStr);

        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }
        List<T> list = new ArrayList<>();
        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            T item = null;
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            list.add((T)gson.fromJson(e, clazz));
        }

        return list;
    }


    /**
     * 将json格式转换成list对象，并准确指定类型
     *
     * @param jsonStr
     * @param type
     * @return
     */
    public static List<?> jsonToList(String jsonStr, Type type) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        List<?> objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }


    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @return
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        Object obj = null;

        // 过滤Static transient volatitle 属性
        gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC,
                Modifier.TRANSIENT,
                Modifier.VOLATILE)
                .create();

        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @param cl
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBeanDateSerializer(String jsonStr,
                                                 Class<T> cl,
                                                 final String pattern) {
        if (StringUtils.isEmpty(jsonStr)) {
            Log.i("***response***", "Json data is null");
            return null;
        }

        Object obj = null;
        // 过滤Static transient volatitle 属性
        // 注册自定义日期类型
        gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC,
                Modifier.TRANSIENT,
                Modifier.VOLATILE)
                .registerTypeAdapter(Date.class,
                        new JsonDeserializer<Date>() {
                            public Date deserialize(JsonElement json,
                                                    Type typeOfT,
                                                    JsonDeserializationContext context) throws JsonParseException {
                                SimpleDateFormat format = new SimpleDateFormat(pattern);
                                String dateStr = json.getAsString();
                                try {
                                    return format.parse(dateStr);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    Log.w("***response error***", e.getMessage());
                                }
                                return null;
                            }
                        })
                .setDateFormat(pattern)
                .create();
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return (T) obj;
    }

    /**
     * 将json转换成bean对象 默认日期格式处理
     *
     * @param jsonStr
     * @param cl
     * @return
     */
    public static <T> T fromJson(String jsonStr, Class<T> cl) {
        return jsonToBeanDateSerializer(jsonStr, cl, DEFAULT_DATE_PATTERN);
    }

    /**
     * 根据
     *
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getJsonValue(String jsonStr, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }
}
