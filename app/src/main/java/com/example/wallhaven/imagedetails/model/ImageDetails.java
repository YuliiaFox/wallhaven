package com.example.wallhaven.imagedetails.model;

public class ImageDetails {
    private String url;
    private String path;
    private String ratio;
    private String resolution;
    private String category;
    private int views;
    private int fileSize;

    public ImageDetails(String url, String ratio, String resolution, String category, int views, int fileSize, String path) {
        this.url = url;
        this.ratio = ratio;
        this.resolution = resolution;
        this.category = category;
        this.views = views;
        this.fileSize = fileSize;
        this.path = path;
    }

    public String getRatio() {
        return ratio;
    }

    public String getResolution() {
        return resolution;
    }

    public String getCategory() {
        return category;
    }

    public int getViews() {
        return views;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }
}
