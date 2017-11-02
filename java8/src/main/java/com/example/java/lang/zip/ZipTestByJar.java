package com.example.java.lang.zip;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;


//ZIP4J
// http://www.lingala.net/zip4j/

public class ZipTestByJar {

  public static void main(String[] args) throws ZipException {
    cipherZipByJar();
  }

  private static void cipherZipByJar() throws ZipException {
    File file = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst");
    net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile("D:\\Samples\\Rxjava\\java-https\\cache\\cipher.zip");
    ZipParameters parameters = new ZipParameters();
    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
    parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
    parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
    parameters.setEncryptFiles(true);
    parameters.setPassword("hahaha");
    ArrayList<File> files = new ArrayList<>();
    for (File src : file.listFiles()) {
      files.add(src);
    }
    zipFile.addFiles(files, parameters);
  }

}
