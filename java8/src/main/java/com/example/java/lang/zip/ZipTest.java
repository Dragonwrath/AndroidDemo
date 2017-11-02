package com.example.java.lang.zip;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipTest {
  public static void main(String[] args) {
    zip();
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
          if (out != null) {
            try {
              out.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
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

    } catch (ZipException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
