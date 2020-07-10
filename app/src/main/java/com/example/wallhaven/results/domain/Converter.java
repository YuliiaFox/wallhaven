package com.example.wallhaven.results.domain;

import com.example.wallhaven.results.model.Image;
import com.example.wallhaven.results.network.model.DataDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public List<Image> convertDtoImageToImage(List<DataDTO> dataDTOList){
        return dataDTOList.stream()
                .map(dtoEntity -> new Image(dtoEntity.thumbs.small, dtoEntity.id))
                .collect(Collectors.toList());
    }
}
