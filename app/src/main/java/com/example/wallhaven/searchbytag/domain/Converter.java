package com.example.wallhaven.searchbytag.domain;

import com.example.wallhaven.searchbytag.model.Tag;
import com.example.wallhaven.searchbytag.network.model.ResultsDto;

import java.util.List;
import java.util.stream.Collectors;

public class Converter{
    public List<Tag> convertResultDtoToTagList(List<ResultsDto> resultsDtos){
        return resultsDtos.stream()
                .map(dtoEntity-> new Tag(dtoEntity.getId(), dtoEntity.getValue()))
                .collect(Collectors.toList());
    }
}
