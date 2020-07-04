package com.example.wallhaven.imagedetails.domain;

import com.example.wallhaven.imagedetails.model.ImageDetails;
import com.example.wallhaven.imagedetails.network.model.ImageDetailsDto;

public class ImageDetailsConverter {

    public ImageDetails convert(ImageDetailsDto dtoImageDetails){
        int views = dtoImageDetails.getData().getViews();
        String url = dtoImageDetails.getData().getUrl();
        String ratio = dtoImageDetails.getData().getRatio();
        String resolution = dtoImageDetails.getData().getResolution();
        String category = dtoImageDetails.getData().getCategory();
        String path = dtoImageDetails.getData().getPath();
        int fileSize = dtoImageDetails.getData().getFileSize();

        return new ImageDetails(url,ratio,resolution,category,views,fileSize, path);
    }
}
