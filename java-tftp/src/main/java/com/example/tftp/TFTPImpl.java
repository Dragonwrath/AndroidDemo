package com.example.tftp;


import org.apache.commons.net.tftp.TFTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class TFTPImpl implements TFTP {
    private int transferMode, port;
    private String hostname, localFilename, remoteFilename;
    private TFTPClient mTFTP;

    public TFTPImpl(String hostname, String localFilename, String remoteFilename, int port) {
        this.hostname = hostname;
        this.localFilename = localFilename;
        this.remoteFilename = remoteFilename;
        this.port = port;
        mTFTP = getDefaultInstance();

    }

    private TFTPClient getDefaultInstance() {
        if (mTFTP == null) {
            synchronized (TFTPImpl.class){
                mTFTP = new TFTPClient();
                mTFTP.setDefaultTimeout(10000);
                mTFTP.setMaxTimeouts(10000);
                transferMode = TFTPClient.BINARY_MODE;
            }
        }
        return mTFTP;
    }

    @Override
    public void sendFile() {
        if (mTFTP != null) {
            try {
                mTFTP.open();
//                mTFTP.open(60000,InetAddress.getByName(getLocalIpAddress()));
                mTFTP.setSoTimeout(10000);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        FileInputStream input = null;

        try {
            input = new FileInputStream(localFilename);
        } catch (IOException e) {
            mTFTP.close();
            System.err.println("Error: could not open local file for reading.");
            System.err.println(e.getMessage());
        }

        // Try to send local file via TFTP
        try {
            mTFTP.sendFile(remoteFilename, transferMode, input, hostname,port);
        } catch (UnknownHostException e) {
            System.err.println("Error: could not resolve hostname.");
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(
                    "Error: I/O exception occurred while sending file.");
            System.err.println(e.getMessage());
            System.exit(1);
        } finally {
            // Close local socket and input file
            mTFTP.close();
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                System.err.println("Error: error closing file.");
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void receiveFile() {
        if (mTFTP != null) {
            try {
                mTFTP.open();
//                mTFTP.open(60000,InetAddress.getByName(getLocalIpAddress()));
                mTFTP.setSoTimeout(10000);

            } catch (SocketException  e) {
                e.printStackTrace();
            }
        }
        FileOutputStream output = null;
        File file;
        file = new File(localFilename);
        try {
            output = new FileOutputStream(file);
        } catch (IOException e) {
            System.err.println("Error: could not open local file for writing.");
            System.err.println(e.getMessage());
        } finally {
            mTFTP.close();
        }

        // Try to receive remote file via TFTP
        try {
            mTFTP.receiveFile(remoteFilename, transferMode, output, hostname, port);
        } catch (UnknownHostException e) {
            System.err.println("Error: could not resolve hostname.");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(
                    "Error: I/O exception occurred while receiving file.");
            System.err.println(e.getMessage());
        } finally {
            // Close local socket and output file
            mTFTP.close();
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                System.err.println("Error: error closing file.");
                System.err.println(e.getMessage());
            }
        }
    }

    public String getLocalIpAddress() {
        String strIP=null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        strIP= inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return strIP;
    }
}
