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
    private final GuiApplication GUI;
    private Map<String, Integer> internalLinks = new TreeMap<>();
    private Map<String, Integer> externalLinks = new TreeMap<>();
    private Map<String, Integer> imageLinks = new TreeMap<>();
    private int pagesCrawled = 1;

    public WebCrawler(String url, GuiApplication gui) {
        ROOT_URL = url;
        GUI = gui;
    }

    /**
     * Start crawling and output results on the console and in a xml-file
     */
    public void start() {
        // crawl through all pages and grab every link you can get
        System.out.print("CRAWLING #" + pagesCrawled);
        crawlPage(ROOT_URL);
        if (GUI != null) GUI.notifyCrawlingFinished();

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
        for (Map.Entry<String, Integer> entry : imageLinks.entrySet()) {
            System.out.println("[" + i + "] [" + entry.getValue() + "] " + entry.getKey());
            i++;
        }

        // Create XML-file
        XMLOutput.createXmlOutput(internalLinks, externalLinks, imageLinks, new File("output.xml"));
    }

    /**
     * Recursive page-crawler
     * @param url url to crawl
     */
    private void crawlPage(String url) {
        addPage(0, url);
        if (GUI != null) GUI.notifyCrawlingNewPage(url);

        for (int i = 0; i < String.valueOf(pagesCrawled - 1).length() + 10; i++) {
            System.out.print("\b");
        }
        System.out.print("CRAWLING #" + pagesCrawled);
        pagesCrawled++;

        // Open page at url
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent(Main.APPLICATION_UA).timeout(5000).get();
        } catch (IOException e) {
            System.out.println("\nUnable to read Page at [" + url + "]: " + e.getMessage());
            addPage(0, url, 500);
            return;
        }

        // Get every image from that page
        Elements images = doc.select("img");
        for (Element image : images) {
            String imageUrl = image.attr("abs:src");
            // It is an image --> add to list
            if (!listContains(imageLinks, imageUrl) && !imageUrl.equals("")) addPage(2, imageUrl);
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
                            addPage(0, linkUrl);
                            return;
                        }
                    }

                    // No file --> crawl this page
                    if (!listContains(internalLinks, linkUrl) && !linkUrl.equals("")) crawlPage(linkUrl);
                }
            } else { // Found an external link
                if (!listContains(externalLinks, linkUrl) && !linkUrl.equals("")) addPage(1, linkUrl);
            }
        }
    }

    /**
     * Add a new page/link/url to the corresponding list
     * @param listId list/map which will contain the url
     * @param url    url to add
     */
    private void addPage(int listId, String url) {
        int statuscode = new ConnectionTester(url).getStatuscode();
        if (listId == 0) {
            internalLinks.put(url, statuscode);
        } else if (listId == 1) {
            externalLinks.put(url, statuscode);
        } else if (listId == 2) {
            imageLinks.put(url, statuscode);
        }

        if (GUI != null) GUI.notifyPageFound(listId, url, statuscode);
    }

    /**
     * Add a new page/link/url to the corresponding list
     * @param listId     list/map which will contain the url
     * @param url        url to add
     * @param statuscode http-statuscode of this url
     */
    private void addPage(int listId, String url, int statuscode) {
        if (listId == 0) {
            internalLinks.put(url, statuscode);
        } else if (listId == 1) {
            externalLinks.put(url, statuscode);
        } else if (listId == 2) {
            imageLinks.put(url, statuscode);
        }

        if (GUI != null) GUI.notifyPageFound(listId, url, statuscode);
    }

    /**
     * Check whether a specific list contains this url already or not
     * @param list list/map which may contain the link
     * @param url  url to add
     * @return list contains url or not
     */
    private boolean listContains(Map<String, Integer> list, String url) {
        return list.keySet().contains(url);
    }

    /**
     * Get the Internal-Links-Map / -List
     * @return Internal-Links-Map
     */
    public Map<String, Integer> getInternalLinksList() {
        return internalLinks;
    }

    /**
     * Get the External-Links-Map / -List
     * @return External-Links-Map
     */
    public Map<String, Integer> getExternalLinksList() {
        return externalLinks;
    }

    /**
     * Get the Image-Links-Map / -List
     * @return Image-Links-Map
     */
    public Map<String, Integer> getImagesLinksList() {
        return imageLinks;
    }

    /**
     * Get the amount of all crawled pages
     * @return amount of crawled pages
     */
    public int getTotalPagesCrawled() {
        return pagesCrawled;
    }
}