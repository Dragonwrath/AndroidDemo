package com.example.java.lang.zip;


import com.example.java.lang.zip.net.lingala.zip4j.examples.CipherHelper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class ZipTest {
  public static void main(String[] args) throws  Exception {
    encryptZip();
    decryptZip();
  }

  private static void unzip() {
    try {
      ZipFile file = new ZipFile(new File("D:\\Samples\\Rxjava\\java-https\\cache\\image.zip"));

      File dst = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst");

      Enumeration<? extends ZipEntry> entries = file.entries();
      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        String name = entry.getName();
        InputStream stream = file.getInputStream(entry);
        byte[] source = new byte[1024];
        int len;
        File dstFile = new File(dst, name);
        if (!dstFile.exists()) {
          //noinspection All
          dstFile.createNewFile();
        }
        BufferedOutputStream out = null;
        try {
          out = new BufferedOutputStream(new FileOutputStream(dstFile));
          while ((len = stream.read(source)) > 0) {
            out.write(source, 0, len);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          closeStream(null, out);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void zip() {
    try {
      File dst = new File("D:\\Samples\\Rxjava\\java-https\\cache\\copy");
      if (!dst.exists()) {
        dst.createNewFile();
      }
      File src = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst");
      BufferedInputStream in = null;
      ZipOutputStream out = null;
      try {
        out = new ZipOutputStream(new FileOutputStream(dst));
        int len;
        byte[] cache = new byte[1024];
        if (src.isDirectory()) {
          File[] files = src.listFiles();
          if (files != null) {
            for (File file : files) {
              if (!file.isDirectory() && file.length() > 0) {
                in = new BufferedInputStream(new FileInputStream(file));
                out.putNextEntry(new ZipEntry(file.getName()));
                while ((len = in.read(cache)) > 0) {
                  out.write(cache, 0, len);
                }
                out.closeEntry();
              }
            }
          }
        }
      } finally {
        closeStream(in, out);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void encryptZip() {
    final Cipher cipher = CipherHelper.encrypt("hahaha");
    File dst = new File("D:\\Samples\\Rxjava\\java-https\\cache\\copy.zip");
    File src = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst");
    BufferedInputStream in = null;
    ZipOutputStream out = null;
    try {
      out = new ZipOutputStream(new FileOutputStream(dst));
      int len;
      byte[] cache = new byte[1024];
      for (File file : src.listFiles()) {
        in = new BufferedInputStream(new CipherInputStream(new FileInputStream(file), cipher));
        out.putNextEntry(new ZipEntry(file.getName()));
        while ((len = in.read(cache)) > 0) {
          out.write(cache, 0, len);
        }
        out.closeEntry();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeStream(in, out);
    }
  }

  private static void decryptZip() throws Exception{
    final Cipher cipher = CipherHelper.decrypt("hahaha");
    ZipFile file = new ZipFile("D:\\Samples\\Rxjava\\java-https\\cache\\copy.zip");

    File dst = new File("D:\\Samples\\Rxjava\\java-https\\cache\\dst1");
    Enumeration<? extends ZipEntry> entries = file.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = entries.nextElement();
      String name = entry.getName();
      InputStream stream = file.getInputStream(entry);
      byte[] source = new byte[1024];
      int len;
      File dstFile = new File(dst, name);
      if (!dstFile.exists()) {
        //noinspection All
        dstFile.createNewFile();
      }
      BufferedOutputStream out = null;
      try {
        out = new BufferedOutputStream(new CipherOutputStream(new FileOutputStream(dstFile), cipher));
        while ((len = stream.read(source)) > 0) {
          out.write(source, 0, len);
        }
        closeStream(null, out);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        closeStream(null, out);
      }
    }

  }

  private static void closeStream(InputStream in, OutputStream out) {
    if (out != null) {
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (in != null) {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void crc() throws Exception {
    FileInputStream in1 = new FileInputStream(new File("D:\\Samples\\Rxjava\\java-https\\cache\\1.txt"));
    FileInputStream in2 = new FileInputStream(new File("D:\\Samples\\Rxjava\\java-https\\cache\\2.txt"));


    int r1 = in1.read();
    byte[] src1 = new byte[r1];
    in1.read(src1);
    System.out.println("Arrays.toString(src2) = " + Arrays.toString(src1));

    int r2 = in2.read();
    byte[] src2 = new byte[r2];
    in2.read(src2);
    System.out.println("Arrays.toString(src2) = " + Arrays.toString(src2));
    in1.close();
    in2.close();
  }
}
