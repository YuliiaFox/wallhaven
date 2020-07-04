package com.example.wallhaven.searchbytag.network.model;

import java.util.List;

public class AutocompleteSuggestions {

    private String term;
    private List<ResultsDto> results;

    public String getTerm() {
        return term;
    }

    public List<ResultsDto> getResults() {
        return results;
    }
}
