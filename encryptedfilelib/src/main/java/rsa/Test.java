package rsa;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import static rsa.RSAUtils.generateRSAKeyPair;


public class Test {
    public static void main(String[] args) throws Exception{

        File file = new File("D:\\EncryptedFile", "1.txt");
        String fileMD5 = getFileMD5(file);
        System.out.println("fileMD5 = " + fileMD5);
//        File aml_ap2 = new File("D:\\downloadTest", "b55b26475bc14ffc9e0bf1152e2d7faf");
//        String md5 = getFileMD5(aml_ap2);
//        System.out.println("md5.equals(fileMD5) = " + md5.equals(fileMD5));

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("junwtech".getBytes());
        String string = new BigInteger(1, md5.digest()).toString(16);
        System.out.println("string = " + string.toUpperCase());
        System.out.println("string = " + string.toUpperCase().equals("21232F297A57A5A743894A0E4A801FC3"));

    }

    /**
     * Get MD5 of one file:hex string,test OK!
     *
     * @param file
     * @return
     */
    private static String getFileMD5(File file) {
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
            while ((len = in.read(buffer, 0, 1024)) != -1) {
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
