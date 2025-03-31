
package org.example;

public class RSSItem {
    private String title;
    private String link;
    private String description;
    private String pubDate;

    public RSSItem(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }
    //get pentru fiecare atribut
    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public String toString() { //formatez afisarea
        String var10000 = this.title;
        return "Title: " + var10000 + "\nLink: " + this.link + "\nPublished: " + this.pubDate + "\n" + "-".repeat(50);
    }
}
