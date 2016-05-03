package com.iba.tradesmart;

import com.google.gson.JsonElement;
import com.tenpearls.android.entities.BaseEntity;

/**
 * Created by firdous on 03/05/16.
 */
public class FeedItem extends BaseEntity {
    private String title;
    private String thumbnail;

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

    @Override
    public void loadJson(JsonElement jsonElement) {

    }
}
