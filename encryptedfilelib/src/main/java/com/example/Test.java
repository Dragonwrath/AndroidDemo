package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Arrays;


public class Test {
    public static void main(String[] args) throws Exception {
//        test();



        String s1 = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJRex8swJ4fmK/4tCrcrogVYWAtPTzqhgX3uyn5ri3S+Gvjd1W4EEbg1FNMw5TpwVCibF2Ibfc/NOcD93Wn86B4GsYl76cJo8SlYrHa7Gmmnb0uu8kd29NMoNIAGAV/Xk0Lb8lGbpt9jfpJ+x9X5MaSFIEqM60ASVUkUR0gGTOL1AgMBAAECgYEAjFqMzd2jx58phqy9MkGIZ14HC8veuywE/0hhLe42Yh1kDoBrYkNfL86nAJGEhx+zvSXZnOk8vtL5C2QbjCO+vxMIVLdJ0Xli98bnGt36RTiDyXqW4P3ckNkql9FiDAbwMG+zJvLZq0HGXhgfMZ6tTwd73yWgTgyB8625pG7hmoECQQDlqhY4+SqqdBPgI3ExzxdNnzZrNNbAJI5YerNoRQTlR9MsYtbpwjoyvC2Sfj5tfSVP2FKk0fe0OtPetlGKofzpAkEApWJDR9W/yPmm2PNikrzD3KylPDTpL/5oGf1I01fGt5M9Na6JhOGY79+Du9qOYzFcH1FbFhGBIkL1DbzjwBs+LQJBANupugWWNDvCOHxqCctFMxm4DVmuuTQgAtIX0d8KHKub7hwHEV2VuwppuADkpJseVoCu/CTO+ue0WI8svIMnenkCQARXzT6ArXWgoLIHyiniTR5pZjlrhkgdXSx5i0UJhA1kD3Jub+wOZI7ABtyTA7Q1Ip8r6sFsmkCFE5xVvoFX9c0CQQCP/iC2orwpOLKupXpmbgsq+azm1mYWkMfH7Olltn/glxSX2yVP9170vKpr2mclwcCScYEat278wpT+ETNIuAZx";

        String s2 = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJRex8swJ4fmK/4tCrcrogVYWAtPTzqhgX3uyn5ri3S+Gvjd1W4EEbg1FNMw5TpwVCibF2Ibfc/NOcD93Wn86B4GsYl76cJo8SlYrHa7Gmmnb0uu8kd29NMoNIAGAV/Xk0Lb8lGbpt9jfpJ+x9X5MaSFIEqM60ASVUkUR0gGTOL1AgMBAAECgYEAjFqMzd2jx58phqy9MkGIZ14HC8veuywE/0hhLe42Yh1kDoBrYkNfL86nAJGEhx+zvSXZnOk8vtL5C2QbjCO+vxMIVLdJ0Xli98bnGt36RTiDyXqW4P3ckNkql9FiDAbwMG+zJvLZq0HGXhgfMZ6tTwd73yWgTgyB8625pG7hmoECQQDlqhY4+SqqdBPgI3ExzxdNnzZrNNbAJI5YerNoRQTlR9MsYtbpwjoyvC2Sfj5tfSVP2FKk0fe0OtPetlGKofzpAkEApWJDR9W/yPmm2PNikrzD3KylPDTpL/5oGf1I01fGt5M9Na6JhOGY79+Du9qOYzFcH1FbFhGBIkL1DbzjwBs+LQJBANupugWWNDvCOHxqCctFMxm4DVmuuTQgAtIX0d8KHKub7hwHEV2VuwppuADkpJseVoCu/CTO+ue0WI8svIMnenkCQARXzT6ArXWgoLIHyiniTR5pZjlrhkgdXSx5i0UJhA1kD3Jub+wOZI7ABtyTA7Q1Ip8r6sFsmkCFE5xVvoFX9c0CQQCP/iC2orwpOLKupXpmbgsq+azm1mYWkMfH7Olltn/glxSX2yVP9170vKpr2mclwcCScYEat278wpT+ETNIuAZx";

        System.out.println("s1.equals(s2) = " + s1.equals(s2));

        System.out.println("s2.length() = " + s2.toCharArray().length);
    }

    private static void test() throws Exception {
        KeyPair keyPair = RSAUtils.generateRSAKeyPair();
        byte[] bytes = keyPair.getPrivate().getEncoded();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File("D:\\EncryptedFile", "2.txt")));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:\\EncryptedFile", "1.txt")));
        boolean e = RSAUtils.encryptData(in,out,keyPair.getPublic());
        if (e)
            System.out.println("finish  encryptData");

        System.out.println(Arrays.toString(keyPair.getPublic().getEncoded()));
        System.out.println();

        System.out.println(Arrays.toString(keyPair.getPrivate().getEncoded()));
        System.out.println();


        String encode = Base64Utils.encode(bytes);

        System.out.println("encode = " + encode);

        System.out.println();
        int length = encode.length();
        String s1 = encode.substring(0, length / 3);
        System.out.println(s1);
        System.out.println();

        String s2 = encode.substring(length / 3, length / 3 * 2);
        System.out.println(s2);
        System.out.println();

        String s3 = encode.substring(length / 3 * 2, length);
        System.out.println(s3);
        System.out.println();


        PrivateKey key = RSAUtils.loadPrivateKey(new StringBuilder().append(s1).append(s2).append(s3).toString());
        BufferedInputStream in1 = new BufferedInputStream(new FileInputStream(new File("D:\\EncryptedFile", "1.txt")));
        BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream(new File("D:\\EncryptedFile", "3.txt")));
        boolean b = RSAUtils.decryptData(in1, out1, key);
        if (b)
            System.out.println("finish decryptData");
    }
}
