package com.joke.rxjavademo.module.cache_6.data;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joke.rxjavademo.BaseApplication;
import com.joke.rxjavademo.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Database {
    private static String DATA_FILE_NAME = "data.db";
    private static Database INSTANCE;
    File dataFile = new File(BaseApplication.getInstance().getFilesDir(), DATA_FILE_NAME);
    Gson gson = new Gson();


    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public List<Item> readItems(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader(dataFile);
            return gson.fromJson(reader, new TypeToken<List<Item>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeItems(List<Item> items) {
        String json = gson.toJson(items);
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            FileWriter writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void delete() {
        dataFile.delete();
    }

}
