package com.example.wallhaven.searchbytag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.wallhaven.searchbytag.model.Tag;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TagsViewModel extends ViewModel {
    private Executor executor;
    private LiveData<PagedList<Tag>> tagLiveData;

    public TagsViewModel{
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

    }
}
