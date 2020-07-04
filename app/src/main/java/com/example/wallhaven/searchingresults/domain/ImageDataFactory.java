package com.example.wallhaven.searchingresults.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ImageDataFactory extends DataSource.Factory {
    private MutableLiveData<ImageDataSource>  mutableLiveData;
    private ImageDataSource imageDataSource;

    public ImageDataFactory() {
        this.mutableLiveData = new MutableLiveData<ImageDataSource>();
    }

    @NonNull
    @Override
    public DataSource create() {
        imageDataSource = new ImageDataSource();
        mutableLiveData.postValue(imageDataSource);
        return imageDataSource;
    }

    public MutableLiveData<ImageDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
