package com.example;

import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.ThreadSafe;


public class FileApiTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:");
        File[] files = file.listFiles();
        for (File dest : files) {
            System.out.println("dest.getAbsolutePath() = " + dest.getAbsolutePath());
        }

        File test = new File(file, "deleteTest");
        boolean newFile = test.createNewFile();
        System.out.println(test.delete());
        System.out.println(test.delete());

    }
}
