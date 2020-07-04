package com.example.wallhaven.imagedetails.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    private List<Tags> tags;
    private Thumbs thumbs;
    private String path;
    private List<String> colors;
    private String createdAt;
    private String fileType;
    @SerializedName("file_size")
    private int fileSize;
    private String ratio;
    private String resolution;
    private int dimensionY;
    private int dimensionX;
    private String category;
    private String purity;
    private String source;
    private int favorites;
    private int views;
    private Uploader uploader;
    private String shortUrl;
    private String url;
    private String id;

    public List<Tags> getTags() {
        return tags;
    }

    public Thumbs getThumbs() {
        return thumbs;
    }

    public String getPath() {
        return path;
    }

    public List<String> getColors() {
        return colors;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFileType() {
        return fileType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getRatio() {
        return ratio;
    }

    public String getResolution() {
        return resolution;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public String getCategory() {
        return category;
    }

    public String getPurity() {
        return purity;
    }

    public String getSource() {
        return source;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getViews() {
        return views;
    }

    public Uploader getUploader() {
        return uploader;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
