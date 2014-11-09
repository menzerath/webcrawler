package eu.menzerath.webcrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class GuiApplication extends JFrame {
    private JPanel mainPanel;
    private JTextField urlTextField;
    private JButton startButton;
    private JList list1;
    private JList list2;
    private JList list3;
    private JLabel crawlingLabel;

    private DefaultListModel<String> list1Model;
    private DefaultListModel<String> list2Model;
    private DefaultListModel<String> list3Model;

    private WebCrawler crawler;

    /**
     * Start the GUI!
     * Prepares everything and then shows the form
     */
    public static void startGUI() {
        // For an nicer look according to the used OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JFrame frame = new JFrame("GuiApplication");
        GuiApplication gui = new GuiApplication();
        frame.setContentPane(gui.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle(Main.APPLICATION_NAME);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * The constructor. Initialize the JLists and prepare the button for some action
     */
    public GuiApplication() {
        // give every JList a ListModel
        list1Model = new DefaultListModel<>();
        list1.setModel(list1Model);

        list2Model = new DefaultListModel<>();
        list2.setModel(list2Model);

        list3Model = new DefaultListModel<>();
        list3.setModel(list3Model);

        // click the start-button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                urlTextField.setEnabled(false);
                crawlingLabel.setText("Starting...");
                list1Model.clear();
                list2Model.clear();
                list3Model.clear();

                new Thread(){
                    public void run() {
                        crawler = new WebCrawler(urlTextField.getText().trim(), GuiApplication.this);
                        crawler.start();
                    }
                }.start();
            }
        });

        // double-click an JList-entry
        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() != 2) return;
                if (!Desktop.isDesktopSupported()) return;
                try {
                    JList list = (JList)evt.getSource();
                    String url = (String)list.getModel().getElementAt(list.locationToIndex(evt.getPoint()));
                    Desktop.getDesktop().browse(new URI(url.substring(6)));
                } catch (IOException | URISyntaxException ignored) {
                }
            }
        });
        list2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() != 2) return;
                if (!Desktop.isDesktopSupported()) return;
                try {
                    JList list = (JList)evt.getSource();
                    String url = (String)list.getModel().getElementAt(list.locationToIndex(evt.getPoint()));
                    Desktop.getDesktop().browse(new URI(url.substring(6)));
                } catch (IOException | URISyntaxException ignored) {
                }
            }
        });
        list3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() != 2) return;
                if (!Desktop.isDesktopSupported()) return;
                try {
                    JList list = (JList)evt.getSource();
                    String url = (String)list.getModel().getElementAt(list.locationToIndex(evt.getPoint()));
                    Desktop.getDesktop().browse(new URI(url.substring(6)));
                } catch (IOException | URISyntaxException ignored) {
                }
            }
        });
    }

    /**
     * Called by the Crawler to notify the GUI to add a new url
     * @param listId     list to add the url to
     * @param url        url to add
     * @param statuscode statuscode of this url
     */
    public void notifyPageFound(int listId, String url, int statuscode) {
        if (listId == 0) {
            list1Model.addElement("[" + statuscode + "] " + url);
        } else if (listId == 1) {
            list2Model.addElement("[" + statuscode + "] " + url);
        } else if (listId == 2) {
            list3Model.addElement("[" + statuscode + "] " + url);
        }
    }

    /**
     * Called by the Crawler to notify the GUI to change the label's text
     */
    public void notifyCrawlingNewPage(String url) {
        crawlingLabel.setText("Crawling #" + crawler.getTotalPagesCrawled() + ": " + url);
    }

    /**
     * Called by the Crawler to notify the GUI to allow input and load the final (ordered!) results
     */
    public void notifyCrawlingFinished() {
        crawlingLabel.setText("Done.");
        startButton.setEnabled(true);
        urlTextField.setEnabled(true);

        list1Model.clear();
        for (Map.Entry<String, Integer> entry : crawler.getInternalLinksList().entrySet()) {
            list1Model.addElement("[" + entry.getValue() + "] " + entry.getKey());
        }

        list2Model.clear();
        for (Map.Entry<String, Integer> entry : crawler.getExternalLinksList().entrySet()) {
            list2Model.addElement("[" + entry.getValue() + "] " + entry.getKey());
        }

        list3Model.clear();
        for (Map.Entry<String, Integer> entry : crawler.getImagesLinksList().entrySet()) {
            list3Model.addElement("[" + entry.getValue() + "] " + entry.getKey());
        }
    }
}
