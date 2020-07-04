package com.example.wallhaven.searchbytag.network.model;

public class ResultsDto {
    private String purity;
    private String category;
    private int id;
    private String filterBy;
    private String value;
    private String label;

    public String getPurity() {
        return purity;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
