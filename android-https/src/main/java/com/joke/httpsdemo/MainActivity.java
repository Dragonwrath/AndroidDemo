package com.joke.httpsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class MainActivity extends AppCompatActivity {
    String path = "https://10.0.3.2:8443/Test/Hlloer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void onewayConnect(){
        try {
            InputStream stream = getAssets().open("server.cer");
            SSLContext tls = SSLContext.getInstance("TLS");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            Certificate certificate = CertificateFactory.getInstance("X.509").generateCertificate(stream);
            keyStore.setCertificateEntry("server",certificate);
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory instance = TrustManagerFactory.getInstance(algorithm);
            instance.init(keyStore);
            tls.init(null,instance.getTrustManagers(),null);
            SSLSocketFactory socketFactory = tls.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);

            URL url = new URL(path);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            InputStream inputStream = conn.getInputStream();
            String string = getString(inputStream);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getString(InputStream inputStream) {

        return "";
    }

}






















