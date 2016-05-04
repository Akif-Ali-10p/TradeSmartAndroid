package com.iba.tradesmart.helpers;

/**
 * Created by firdous on 03/05/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.iba.tradesmart.entities.FeedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Illustrative, as in: nowhere near complete or ready for production use ;)
 */
public class IllustrativeRSS implements Parcelable {

    public static final Parcelable.Creator<IllustrativeRSS> CREATOR = new Parcelable.Creator<IllustrativeRSS>(){
        @Override
        public IllustrativeRSS createFromParcel(Parcel source) {
            return new IllustrativeRSS(source);
        }

        @Override
        public IllustrativeRSS[] newArray(int size) {
            return new IllustrativeRSS[size];
        }
    };

    private List<FeedItem> items;

    public IllustrativeRSS(Parcel parcel) {
        items = new ArrayList<FeedItem>();
        int len = parcel.readInt();
        for (int i=0; i<len; i++)
            items.add(new FeedItem(
                    parcel.readString(),
                    parcel.readString()));
    }

    public IllustrativeRSS(){
        items = new ArrayList<FeedItem>();
    }

    public void add(String url, String title) {
        items.add(new FeedItem(url, title));
    }

    public int size() {
        return items.size();
    }

    public FeedItem get(int i){
        return items.get(i);
    }

    public List getList(){
        return items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(items.size());
        for (FeedItem i : items) {
            dest.writeString(i.getUrl());
            dest.writeString(i.getTitle());
        }
    }
}
