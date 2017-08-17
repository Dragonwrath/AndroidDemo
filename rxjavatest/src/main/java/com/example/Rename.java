package com.example;

import java.io.File;

public class Rename {
    public static void main(String[] args) {
        File file = new File("D:\\microlink-app\\microlink\\app\\src\\main\\res\\drawable-xhdpi");
        File dst = new File("D:\\microlink-app\\microlink\\app\\src\\main\\res\\drawable-xhdpi\\target");
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String s : list) {
                File target = new File(file, s);
                if (target.getName().contains("image")) {
                    String[] split = target.getName().split("image");
                    target.renameTo(new File(dst, "image"+ (Integer.parseInt(split[1].split("\\.")[0]) - 1) + ".png"));
                }
            }
        }
    }
}