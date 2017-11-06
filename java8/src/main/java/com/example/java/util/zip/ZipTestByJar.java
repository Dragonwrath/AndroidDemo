package com.example.java.util.zip;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


//ZIP4J
// http://www.lingala.net/zip4j/

public class ZipTestByJar {

  public static void main(String[] args) throws ZipException {
    cipherZipByJar();
  }

  private static void cipherZipByJar() throws ZipException {
    File file = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst");
    ZipFile zipFile = new ZipFile("D:\\Samples\\Rxjava\\java-https\\cache\\cipher.zip");
    ZipParameters parameters = new ZipParameters();
    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
    parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
    parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
    parameters.setEncryptFiles(true);
    parameters.setPassword("hahaha");
    ArrayList<File> files = new ArrayList<>();
    File[] listFiles = file.listFiles();
    assert listFiles != null;
    Collections.addAll(files, listFiles);
    zipFile.addFiles(files, parameters);
  }

}
