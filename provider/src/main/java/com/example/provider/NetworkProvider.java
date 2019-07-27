package com.example.provider;

import android.util.Log;

import com.example.base.BaseApplication;
import com.example.network.HttpClient;
import com.example.network.wrapper.core.NetworkWrapper;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetworkProvider {
    private static final String TAG = "NetworkProvider";
    private NetworkWrapper networkWrapper;
    private HttpClient httpClient;
    private static final String HOST = "192.168.137.224";

    public NetworkProvider() {
        TrustManager trustManager = new MyTrustManager();
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            httpClient = new HttpClient.Builder()
                    .connectTimeout(3000)
                    .readTimeout(3000)
                    .hostnameVerifier(hostnameVerifier)
                    .SSLSocketFactory(sslSocketFactory)
                    .build();
            networkWrapper = new NetworkWrapper(HOST, httpClient, GsonProvider.getInstance().getGson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            Log.e(TAG, "hostname = " + hostname);
            return true;
        }
    };

    private static class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            try {
                X509Certificate certificate = (X509Certificate) certificateFactory.
                        generateCertificate(BaseApplication.getContext().getAssets().open("tomcat.cer"));
                X509Certificate server = chain[0];
                if (certificate.getIssuerDN().equals(server.getIssuerDN())) {
                    if (certificate.getPublicKey().equals(server.getPublicKey())) {
                        certificate.checkValidity();
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public NetworkWrapper getNetworkService() {
        return networkWrapper;
    }

    public static NetworkProvider getInstance() {
        return NetworkProviderHolder.INSTANCE;
    }

    private static class NetworkProviderHolder {
        private static final NetworkProvider INSTANCE = new NetworkProvider();
    }
}
