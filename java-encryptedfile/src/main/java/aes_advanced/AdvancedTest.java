package aes_advanced;



import java.io.File;

import aes.MD5;

public class AdvancedTest {
    public static void main(String[] args) throws Exception {
        encrypted();
    }

    private static void encrypted() throws Exception {
        File destFile = new File("D:\\decryptedTestFile\\AML_AP2");
        File sourceFile = new File("D:\\encryptedTestFile\\AML_AP2");
        destFile.createNewFile();
        AES.encryptFile(sourceFile, destFile,"junwtech_microlink");

        File copyFile = new File("D:\\decryptedTestFile\\AML_AP2_COPY");
        copyFile.createNewFile();
        AES.decryptFile(destFile,copyFile,"junwtech_microlink");

        String source = MD5.getMD5(sourceFile);
        System.out.println("source = " + source);
        String dest = MD5.getMD5(copyFile);
        System.out.println("dest = " + dest);

        System.out.println("dest.equals(source) = " + dest.equals(source));
    }
}
