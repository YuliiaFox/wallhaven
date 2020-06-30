package com.example.wallhaven.search;

import java.util.List;
import java.util.stream.Collectors;

import com.example.wallhaven.search.network.model.DataDTO;

public class DtoToImageConverter {

    public List<ListImage> convert(List<DataDTO> dataDTOList){
        return dataDTOList.stream()
                .map(dtoEntity -> dtoEntity.thumbs.small)
                .map(ListImage::new)
                .collect(Collectors.toList());
    }
}
