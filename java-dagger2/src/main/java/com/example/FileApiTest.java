package com.example;

import java.io.File;
import java.io.IOException;



public class FileApiTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:");
        File[] files = file.listFiles();
        if (files != null)
            for (File dest : files) {
                System.out.println("dest.getAbsolutePath() = " + dest.getAbsolutePath());
            }

        File test = new File(file, "deleteTest");
        boolean newFile = test.createNewFile();
        System.out.println(test.delete());
        System.out.println(test.delete());

    }
}
