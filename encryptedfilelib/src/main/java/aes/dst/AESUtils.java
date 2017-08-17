package aes.dst;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import aes.Base64;


@SuppressWarnings("unused")
public final class AESUtils {

	public static byte[] initKey(byte[] raw) throws Exception{
		KeyGenerator kGen = KeyGenerator.getInstance("AES");
		//设置密钥长度
		kGen.init(128, new SecureRandom(raw));
		//生成密钥
		SecretKey skey = kGen.generateKey();
		//返回密钥的二进制编码
		return skey.getEncoded();
	}


	@Nullable
	public static String encrypt(String sSrc, byte[] raw) {
		try {
			if (raw == null) {
				return null;
			}
			// 判断Key是否为16位
			if (raw.length != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(raw));
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

			return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception ex) {
			return null;
		}
	}

	public static void encrypt(InputStream in, OutputStream out, byte[] raw) {
		if (raw == null)
			throw new IllegalArgumentException("");
		CipherInputStream cis = null;
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE,skeySpec, new IvParameterSpec(raw));
			cis = new CipherInputStream(in, cipher);
			int len ;
			byte[] bytes = new byte[128];
			while((len = cis.read(bytes)) != -1){
				out.write(bytes, 0 , len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				out.flush();
				if (cis != null) {
					cis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static String decrypt(String sSrc, byte[] raw){
		byte[] decrypt = Base64.decode(sSrc, Base64.DEFAULT);

		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(raw));
			byte[] encrypted = cipher.doFinal(decrypt);
			return new String(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void decrypt(InputStream in, OutputStream out,byte[] raw) {
		CipherOutputStream cos = null;
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(raw));
			cos = new CipherOutputStream(out, cipher);
			int len ;
			byte[] bytes = new byte[128];
			while((len = in.read(bytes)) != -1){
				cos.write(bytes, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.flush();
				if (cos != null) {
					cos.flush();
					cos.close();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
