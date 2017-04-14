package com.example;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import rsa.Base64Utils;

public class RSAUtils {
    private final static String RSA = "RSA";
    private final static String RSA_CIPHER = "RSA/ECB/PKCS1Padding";

    /**
     * 随机生成RSA密钥对(默认密钥长度为1024)
     *
     * @return
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    /**
     * 随机生成RSA密钥对
     *
     * @param keyLength 密钥长度，范围：512～2048<br>
     *                  一般1024
     * @return 获取到的RSA秘钥
     */
    private static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用公钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param in   需要加密的文件输入流
     * @param out  指定接收的文件输出流
     * @param publicKey 公钥
     * @return 加密后的byte型数据
     */
    public static boolean encryptData(InputStream in, OutputStream out, PublicKey publicKey) {
        boolean finish = false;
        try {
            Cipher cipher = Cipher.getInstance(RSA_CIPHER);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = new byte[512];
            int len ;
            while ( (len = in.read(bytes)) > 0){
                out.write(cipher.doFinal(bytes,0, len));
            }
            finish = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return finish;
    }

    /**
     * 用私钥解密
     *
     * @param in   需要加密的文件输入流
     * @param out  指定接收的文件输出流
     * @param privateKey    私钥
     * @return
     */
    public static boolean decryptData(InputStream in, OutputStream out, PrivateKey privateKey) {
        boolean finish = false;

        try {
            Cipher cipher = Cipher.getInstance(RSA_CIPHER);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] bytes = new byte[512];
            int len ;
            while ( (len = in.read(bytes)) > 0){
                out.write(cipher.doFinal(bytes,0, len));
            }
            finish = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return finish;
    }

    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64Utils.decode(privateKeyStr);
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

}
