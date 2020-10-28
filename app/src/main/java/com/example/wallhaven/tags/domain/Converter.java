package com.example.wallhaven.tags.domain;

import com.example.wallhaven.tags.model.Tag;
import com.example.wallhaven.tags.network.model.AutocompleteSuggestions;

import java.util.List;
import java.util.stream.Collectors;

public class Converter{
    public List<Tag> convertResultDtoToTagList(AutocompleteSuggestions autocompleteSuggestions) {
        return autocompleteSuggestions.getResults().stream()
                .map(dtoEntity-> new Tag(dtoEntity.getId(), dtoEntity.getValue()))
                .collect(Collectors.toList());
    }
}
