package aes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public final class AESUtils {

	public static byte[] initKey() throws Exception{
		//实例化
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//设置密钥长度
		kgen.init(128);
		//生成密钥
		SecretKey skey = kgen.generateKey();
		//返回密钥的二进制编码
		return skey.getEncoded();
	}

	/**
	 * AES加密
	 * @param sSrc
	 * @param sKey
     * @return
     */
	public static String encrypt(String sSrc, String sKey) {
		try {
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

			return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception ex) {
			return "";
		}
	}

	public static void encrypt(InputStream in, OutputStream out, byte[] raw) {
		if (raw == null)
			throw new IllegalArgumentException("");
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = null;
			cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE,skeySpec);
			int len ;
			byte[] bytes = new byte[128];
			while((len = in.read(bytes)) != -1){
				out.write(cipher.doFinal(bytes, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * AES解密
	 * @param sSrc
	 * @param sKey
     * @return
     */
	public static String decrypt(String sSrc, String sKey){
		byte[] decrypt = Base64.decode(sSrc, Base64.DEFAULT);

		try {
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(decrypt);
			return new String(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void decrypt(InputStream in, OutputStream out,byte[] raw) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// "算法/模式/补码方式"
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			int len ;
			byte[] bytes = new byte[128];
			while((len = in.read(bytes)) != -1){
				out.write(cipher.doFinal(bytes, 0, len));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
