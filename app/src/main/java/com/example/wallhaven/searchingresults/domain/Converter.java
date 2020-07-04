package com.example.wallhaven.searchingresults.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.example.wallhaven.searchingresults.model.Image;
import com.example.wallhaven.searchingresults.network.model.DataDTO;

public class Converter {

    public List<Image> convertDtoImageToImage(List<DataDTO> dataDTOList){
        return dataDTOList.stream()
                .map(dtoEntity -> new Image(dtoEntity.thumbs.small,dtoEntity.id))
                .collect(Collectors.toList());
    }
}
