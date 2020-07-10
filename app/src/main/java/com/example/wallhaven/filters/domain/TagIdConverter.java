package com.example.wallhaven.filters.domain;

import com.example.wallhaven.results.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class TagIdConverter {
    public List<String> convert(List<Tag> tags) {
        return tags.stream().map(Tag::getId).collect(Collectors.toList());
    }
}
