package com.example.wallhaven.searchbytag.domain;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.wallhaven.searchbytag.model.Tag;

public class TagDataSource extends PageKeyedDataSource<Long, Tag> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Tag> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Tag> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Tag> callback) {

    }
}
