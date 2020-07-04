package com.example.wallhaven.searchbytag.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class TagDataFactory extends DataSource.Factory {
    private MutableLiveData<TagDataSource> mutableLiveData;
    private TagDataSource tagDataSource;

    public TagDataFactory() {
        this.mutableLiveData = new MutableLiveData<TagDataSource>();
    }

    @NonNull
    @Override
    public DataSource create() {
        tagDataSource = new TagDataSource();
        mutableLiveData.postValue(tagDataSource);
        return tagDataSource;
    }

    public MutableLiveData<TagDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
