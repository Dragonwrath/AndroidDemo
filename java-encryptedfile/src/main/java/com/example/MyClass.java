package com.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

public class MyClass {
    private final static int NEGATIVE_ONE = -1;

    public static void main(String[] args) throws Exception{
        File file = new File("D:\\TFTP\\ROOT\\Test\\AML_AP2");

        String fileMD5 = getFileMD5(file);
        System.out.println("fileMD5 = " + fileMD5);

        AES aes = new AES();

        File before = new File("D:\\TFTP\\ROOT\\Test\\1.txt");
        String encryptedFileMD5 = getFileMD5(before);
        System.out.println("encryptedFileMD5 = " + encryptedFileMD5);

        File encryptedFile = aes.encryptFile(before, "utf-8","junwtech");

        File after = aes.decryptFile(encryptedFile, "utf-8", "junwtech");

        BufferedReader inputStream = new BufferedReader(new FileReader(encryptedFile));

        File dst = new File("D:\\TFTP\\ROOT\\Test\\3.txt");
        FileWriter out = new FileWriter(dst);
        BufferedWriter outputStream = new BufferedWriter(out);
        char[] bytes = new char[512];
        int len ;
        while ( (len = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes,0,len);
        }
        inputStream.close();
        outputStream.close();

        String dstMd5 = getFileMD5(dst);
        System.out.println("dstMd5 = " + dstMd5);

        System.out.println(encryptedFileMD5.equals(dstMd5)+"");

    }

    /**
     * Get MD5 of one file:hex string,test OK!
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != NEGATIVE_ONE) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
