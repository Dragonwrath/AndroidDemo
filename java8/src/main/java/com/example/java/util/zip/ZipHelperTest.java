package com.example.java.util.zip;

import com.example.java.util.zip.com.joke.zip.ZipHelper;

import java.io.File;
import java.util.zip.ZipException;

public class ZipHelperTest {
  public static void main(String[] args) throws ZipException {
    File src = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst1");
    File test = new File("D:\\Samples\\Rxjava\\java-https\\cache\\test.zip");
    ZipHelper helper = new ZipHelper(test, false);
    helper.addFile(src);
    helper.zip();
  }
}
