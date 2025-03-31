package org.example;

import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        String rssFeedUrl = "http://rss.cnn.com/rss/edition.rss";

        try {
            for(RSSItem item : RSSParser.parseRSSFeed(rssFeedUrl)) {
                System.out.println(item);
            }
        } catch (IOException e) {
            System.err.println("Eroare la preluarea RSS feed-ului: " + e.getMessage());
        }

    }
}
