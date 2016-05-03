package com.iba.tradesmart;

import com.google.gson.JsonElement;
import com.tenpearls.android.entities.BaseEntity;

import java.util.Date;

/**
 * Created by firdous on 03/05/16.
 */
public class FeedItem extends BaseEntity {
    private String title;
    private String thumbnail;
    private String url;
    private String description;
    private Date pubDate;

    public FeedItem() {
    }

    public FeedItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public FeedItem(String url, String title, String description, String imageUrl) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.thumbnail = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public void setpubDate(Date pubDate){
        this.pubDate = pubDate;
    }
    public Date getpubDate(){
        return pubDate;
    }

    @Override
    public void loadJson(JsonElement jsonElement) {

    }
}
