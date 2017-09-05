package MD5;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {
    public static void main(String[] args) throws Exception {
        String s = string2mdv();
        System.out.println("s = " + s);
        String hahaha = MD5Util.getEncryptedPwd("hahaha");
        System.out.println("h = " + hahaha);

    }

    private static String string2mdv() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        byte[] bytes = "hahaha".getBytes();
            messageDigest.update(bytes);
        byte[] source = messageDigest.digest();

        StringBuilder builder = new StringBuilder();
        for (byte b : source) {
            String s = Integer.toHexString(0xff & b);
            if (s.length() == 1){
                builder.append("0");
            }
            builder.append(s);
        }
       return builder.toString();

    }


}
