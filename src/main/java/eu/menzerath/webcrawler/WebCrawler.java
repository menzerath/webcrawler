package eu.menzerath.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class WebCrawler {
    private final String ROOT_URL;
    private Map<String, Integer> internalLinks = new TreeMap<>();
    private Map<String, Integer> externalLinks = new TreeMap<>();
    private Map<String, Integer> intExtImages = new TreeMap<>();
    private int pageCrawled = 1;
    private int pageChecked = 1;

    public WebCrawler(String url) {
        ROOT_URL = url;
    }

    /**
     * Start crawling and output results on the console and in a xml-file
     */
    public void start() {
        // First: crawl through all pages and grab every link you can get
        System.out.print("CRAWLING #" + pageCrawled);
        crawlPage(ROOT_URL);

        // Then: check the returning status-code of all those pages
        System.out.print("\nCHECKING #" + pageChecked);
        checkPages(internalLinks);
        checkPages(externalLinks);
        checkPages(intExtImages);

        // Print results
        System.out.println("\n\nINTERNAL LINKS:");
        int i = 1;
        for (Map.Entry<String, Integer> entry : internalLinks.entrySet()) {
            System.out.println("[" + i + "] [" + entry.getValue() + "] " + entry.getKey());
            i++;
        }

        System.out.println("\nEXTERNAL LINKS:");
        i = 1;
        for (Map.Entry<String, Integer> entry : externalLinks.entrySet()) {
            System.out.println("[" + i + "] [" + entry.getValue() + "] " + entry.getKey());
            i++;
        }

        System.out.println("\nINTERNAL / EXTERNAL IMAGES:");
        i = 1;
        for (Map.Entry<String, Integer> entry : intExtImages.entrySet()) {
            System.out.println("[" + i + "] [" + entry.getValue() + "] " + entry.getKey());
            i++;
        }

        // Create XML-file
        XMLOutput.createXmlOutput(internalLinks, externalLinks, intExtImages, new File("output.xml"));
    }

    /**
     * Recursive page-crawler
     * @param url Url to crawl
     */
    private void crawlPage(String url) {
        internalLinks.put(url, 999);

        for (int i = 0; i < String.valueOf(pageCrawled - 1).length() + 10; i++) {
            System.out.print("\b");
        }
        System.out.print("CRAWLING #" + pageCrawled);
        pageCrawled++;

        // Open page at url
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent(Main.APPLICATION_UA).timeout(5000).get();
        } catch (IOException e) {
            System.out.println("\nUnable to read Page at [" + url + "]: " + e.getMessage());
            internalLinks.put(url, 500);
            return;
        }

        // Get every image from that page
        Elements images = doc.select("img");
        for (Element image : images) {
            String imageUrl = image.attr("abs:src");
            // It is an image --> add to list
            if (!intExtImages.keySet().contains(imageUrl) && !imageUrl.equals("")) intExtImages.put(imageUrl, 999);
        }

        // Get every link from that page
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String linkUrl = link.attr("abs:href");
            if (linkUrl.startsWith(ROOT_URL)) { // Found an internal link
                if (!linkUrl.matches(Main.noPageRegEx)) { // This link does not end with a "#"
                    // Is link a file?
                    for (String regex : Main.fileRegEx) {
                        if (linkUrl.matches(regex)) {
                            // It is a file --> add to list, but do not try to crawl
                            internalLinks.put(linkUrl, 999);
                            return;
                        }
                    }

                    // No file --> crawl this page
                    if (!internalLinks.keySet().contains(linkUrl) && !linkUrl.equals("")) crawlPage(linkUrl);
                }
            } else { // Found an external link
                if (!externalLinks.keySet().contains(linkUrl) && !linkUrl.equals("")) externalLinks.put(linkUrl, 999);
            }
        }
    }

    private void checkPages(Map<String, Integer> pagesToCheck) {
        for (Map.Entry<String, Integer> entry : pagesToCheck.entrySet()) {
            if (entry.getValue() == 500) continue;

            for (int i = 0; i < String.valueOf(pageChecked - 1).length() + 10; i++) {
                System.out.print("\b");
            }
            System.out.print("CHECKING #" + pageChecked);
            pageChecked++;

            pagesToCheck.put(entry.getKey(), new ConnectionTester(entry.getKey()).getStatuscode());
        }
    }
}