package com.example.wallhaven.filters.domain;

import com.example.wallhaven.filters.model.Tag;
import com.example.wallhaven.filters.network.model.AutocompleteSuggestions;

import java.util.List;
import java.util.stream.Collectors;

public class Converter{
    public List<Tag> convertResultDtoToTagList(AutocompleteSuggestions autocompleteSuggestions) {
        return autocompleteSuggestions.getResults().stream()
                .map(dtoEntity-> new Tag(dtoEntity.getId(), dtoEntity.getValue()))
                .collect(Collectors.toList());
    }
}
