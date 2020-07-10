package com.example.wallhaven.imagedetails.domain;

import com.example.wallhaven.imagedetails.model.ImageDetails;
import com.example.wallhaven.imagedetails.network.model.ImageDetailsDto;
import com.example.wallhaven.imagedetails.network.model.Tags;

import java.util.stream.Collectors;

public class ImageDetailsConverter {

    public ImageDetails convert(ImageDetailsDto dtoImageDetails){
        int views = dtoImageDetails.getData().getViews();
        String url = dtoImageDetails.getData().getUrl();
        String ratio = dtoImageDetails.getData().getRatio();
        String tags = dtoImageDetails.getData().getTags().stream().map(Tags::getName).collect(Collectors.joining(", "));
        String resolution = dtoImageDetails.getData().getResolution();
        String category = dtoImageDetails.getData().getCategory();
        String path = dtoImageDetails.getData().getPath();
        int fileSize = dtoImageDetails.getData().getFileSize();
//TODO: add autoValues/imutables
        return new ImageDetails(url, ratio, resolution, category, views, fileSize, path, tags);
    }
}
