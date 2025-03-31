
package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RSSParser {
    public RSSParser() {
    }

    public static List<RSSItem> parseRSSFeed(String url) throws IOException {
        List<RSSItem> rssItems = new ArrayList();
        Document doc = Jsoup.connect(url).get();

        for(Element item : doc.select("item")) {//toate elementele din xml
            String title = item.select("title").text();
            String link = item.select("link").text();
            String description = item.select("description").text();
            String pubDate = item.select("pubDate").text();
            rssItems.add(new RSSItem(title, link, description, pubDate));
        }

        return rssItems;
    }
}
