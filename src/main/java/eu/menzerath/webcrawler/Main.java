package eu.menzerath.webcrawler;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String APPLICATION_NAME = "WebCrawler";
    public static final String APPLICATION_VERSION = "1.0";
    public static final String APPLICATION_URL = "https://github.com/MarvinMenzerath/WebCrawler";

    public static final List<String> fileRegEx = new ArrayList<>();
    public static final String noPageRegEx = ".*?\\#.*";

    /**
     * Start: Init and ask for the URL to crawl
     * @param args Will be ignored...
     */
    public static void main(String[] args) {
        init();
        sayHello();

        System.out.print("ENTER URL TO CRAWL: ");
        Scanner sc = new Scanner(System.in);
        new WebCrawler(sc.nextLine()).start();
        sc.close();
    }

    /**
     * Init some important variables and allow usage of any SSL-certificate
     */
    private static void init() {
        /**
         * Add some File-RegExs
         */
        fileRegEx.add(".*?\\.png");
        fileRegEx.add(".*?\\.jpg");
        fileRegEx.add(".*?\\.jpeg");
        fileRegEx.add(".*?\\.gif");
        fileRegEx.add(".*?\\.zip");
        fileRegEx.add(".*?\\.7z");
        fileRegEx.add(".*?\\.rar");
        fileRegEx.add(".*?\\.css.*");
        fileRegEx.add(".*?\\.js.*");

        /**
         * Allow usage of _any_ SSL-certificate
         */
        // Create a all-trusting TrustManager
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        } };

        // Install the all-trusting TrustManager
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            System.out.println("Unable to install the all-trusting TrustManager");
        }

        // Create & Install a all-trusting HostnameVerifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) { return true; }
        };

        // Install the all-trusting HostnameVerifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    /**
     * Show information about this program to the user
     */
    private static void sayHello() {
        String lineVersion = APPLICATION_NAME + " v" + APPLICATION_VERSION;
        for (int i = lineVersion.length(); i < 37; i++) lineVersion += " ";

        System.out.println("#############################################");
        System.out.println("### " + lineVersion + " ###");
        System.out.println("###                                       ###");
        System.out.println("### © 2014: Marvin Menzerath              ###");
        System.out.println("### " + APPLICATION_URL.substring(8) + " ###");
        System.out.println("#############################################\n");
    }
}