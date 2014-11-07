package eu.menzerath.webcrawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionTester {
    private URL url;

    public ConnectionTester(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("\nUnable to check Page at [" + this.url + "]: " + e.getMessage());
        }
    }

    public int getStatuscode() {
        try {
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setConnectTimeout(5000);
            http.setReadTimeout(5000);
            http.setRequestProperty("User-Agent", Main.APPLICATION_UA);
            return http.getResponseCode();
        } catch (IOException e) {
            // eg: server not available
            return 500;
        }
    }
}