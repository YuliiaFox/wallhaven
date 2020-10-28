package com.example.wallhaven.results.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchParameters implements Parcelable {
    private String tagId;
    private String tagName;
    private String ratio;
    private String resolution;
    private String color;
    private String sorting;
    private String categories;
    private String purity;

    public SearchParameters(String id, String tagName) {
        this.tagId = id;
        this.tagName = tagName;
    }

    private SearchParameters(Parcel in) {
        tagId = in.readString();
        tagName = in.readString();
    }

    public static final Creator<SearchParameters> CREATOR = new Creator<SearchParameters>() {
        @Override
        public SearchParameters createFromParcel(Parcel in) {
            return new SearchParameters(in);
        }

        @Override
        public SearchParameters[] newArray(int size) {
            return new SearchParameters[size];
        }
    };

    public String getId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tagId);
        dest.writeString(tagName);
    }
}
