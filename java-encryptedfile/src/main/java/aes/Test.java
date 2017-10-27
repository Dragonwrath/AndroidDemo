package aes;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

import aes.dst.AESUtils;

public class Test {
    public static void main(String[] args) throws Exception {
        test3();
    }

    private static void test3()  throws Exception {
        byte[] key = AESUtils.initKey("test".getBytes("utf-8"));
        String src = "HAHAHHAHAHAAHAHHAHA";
        String s = AESUtils.encrypt(src, key);
        System.out.println("encrypt = " + s);
        String s1 = AESUtils.decrypt(s, key);
        System.out.println("decrypt = " + s1);
        System.out.println("s1.equals(src) = " + s1.equals(src));
    }

    private static void test1() throws Exception {
        byte[] key =  AESUtils.initKey("test".getBytes("utf-8"));

        System.out.println("Arrays.toString(key) = " + Arrays.toString(key));
        String encrypted = encrypted(key);

        key =  AESUtils.initKey("test".getBytes("utf-8"));
        String decrypted = decrypted(key);

        System.out.println("decrypted.equals(encrypted) = " + decrypted.equals(encrypted));
    }

    private static String encrypted(byte[] key) throws Exception{
        File srcDest = new File("D:\\encryptedTestFile");
        File amlAp = new File(srcDest, "AML_AP2");
        String md5 = MD5.getMD5(amlAp);
        System.out.println("source file md5 = " + md5);
        if (!amlAp.exists() ) {
            throw new FileNotFoundException("targetFile not found: " + amlAp.getName());
        }

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(amlAp));
        File file = new File("D:\\decryptedTestFile");
        File tag = new File(file, "AML_AP2");
        boolean b = tag.createNewFile();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tag));

        AESUtils.encrypt(in,out,key);
        return  MD5.getMD5(amlAp);
    }

    private static String decrypted(byte[] key) throws  Exception {
        File srcDest = new File("D:\\encryptedTestFile");
        File copy = new File(srcDest, "AML_AP2_copy");
        boolean newFile = copy.createNewFile();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(copy));

        File file = new File("D:\\decryptedTestFile");
        File tag = new File(file, "AML_AP2");
        boolean b = tag.createNewFile();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(tag));

        AESUtils.decrypt(in, out, key);

        String md5 = MD5.getMD5(copy);
        System.out.println("source file md5 = " + md5);

       return MD5.getMD5(copy);
    }
}
