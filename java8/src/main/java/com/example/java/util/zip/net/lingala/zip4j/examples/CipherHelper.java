package com.example.java.util.zip.net.lingala.zip4j.examples;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherHelper {

  private static final String AES = "AES/CBC/PKCS5Padding";

  public static Cipher encrypt(String password) {
    Cipher cipher = null;
    try {
      KeyGenerator kGen = KeyGenerator.getInstance("AES");
      kGen.init(128, new SecureRandom(password.getBytes()));
      byte[] key = kGen.generateKey().getEncoded();
      SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
      cipher = Cipher.getInstance(AES);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(key));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return cipher;
  }

  public static Cipher decrypt(String password) {
    Cipher cipher = null;
    try {
      KeyGenerator kGen = KeyGenerator.getInstance("AES");
      kGen.init(128, new SecureRandom(password.getBytes()));
      byte[] key = kGen.generateKey().getEncoded();
      SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
      cipher = Cipher.getInstance(AES);
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(key));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return cipher;
  }


}
