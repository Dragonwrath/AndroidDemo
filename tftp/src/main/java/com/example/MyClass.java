package com.example;

import com.example.tftp.TFTPImpl;

public class MyClass {
    public static void main(String[] args) throws Exception {
//        new TFTPImpl("192.168.0.111","C:\\RHDSetup.log","D:\\2.txt",60000).sendFile();
//        new TFTPImpl("192.168.0.111","C:\\AML_AP2","AML_AP2",60000).receiveFile();

        TFTPUtil.downloadFile("192.168.0.106","AML_AP2","11.txt",3000);
    }
}
