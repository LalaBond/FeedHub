package com.feedhub.someone.feedhub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by someone on 10/13/18.
 */

public class ArticleSerializableModel implements Serializable{

    private String title;
    private String author;
    private String link;
    private Date pubDate;
    private String description;
    private String content;
    private String image;
    private List<String> categories;

    public ArticleSerializableModel() {


    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getLink() {
        return this.link;
    }

    public Date getPubDate() {
        return this.pubDate;
    }

    public String getDescription() {
        return this.description;
    }

    public String getContent() {
        return this.content;
    }

    public String getImage() {
        return this.image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategories (List<String> categories) {this.categories = categories; }

    public List<String> getCategories() {
        return this.categories;
    }

    public void addCategory(String category) {
        if(this.categories == null) {
            this.categories = new ArrayList();
        }

        this.categories.add(category);
    }

    public String toString() {
        return "Article{title='" + this.title + '\'' + ", author='" + this.author + '\'' + ", link='" + this.link + '\'' + ", pubDate=" + this.pubDate + ", description='" + this.description + '\'' + ", content='" + this.content + '\'' + ", image='" + this.image + '\'' + ", categories=" + this.categories + '}';
    }
}
