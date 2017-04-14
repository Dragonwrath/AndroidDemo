package com.example;

import java.io.File;

public class FileApiTest {
    public static void main(String[] args) {
        File file = new File("D:");
        File[] files = file.listFiles();
        for (File dest : files) {
            System.out.println("dest.getAbsolutePath() = " + dest.getAbsolutePath());
        }

        File test = new File(file, "deleteTest");
        System.out.println(test.delete());
        System.out.println(test.delete());

    }
}
