package com.example.wallhaven.tags.domain;

import com.example.wallhaven.results.model.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class TagIdConverter {
    public List<String> convert(List<SearchParameters> tags) {
        return tags.stream().map(SearchParameters::getId).collect(Collectors.toList());
    }
}
