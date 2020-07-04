package com.example.wallhaven.imagedetails.network.model;

import com.google.gson.annotations.SerializedName;

public class Avatar {

    @SerializedName("200px")
    private String px200;
    @SerializedName("128px")
    private String px128;
    @SerializedName("32px")
    private String px32;
    @SerializedName("20px")
    private String px20;

    public String getPx200() {
        return px200;
    }

    public String getPx128() {
        return px128;
    }

    public String getPx32() {
        return px32;
    }

    public String getPx20() {
        return px20;
    }
}
