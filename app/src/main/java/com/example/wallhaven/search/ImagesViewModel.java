package com.example.wallhaven.Activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImagesViewModel extends ViewModel {
    private Executor executor;
    private LiveData<NetworkState> networkStateLiveData;
    private LiveData<PagedList<ListImage>> imageLiveData;

    public ImagesViewModel() {
        init();
    }

    private void init(){
        executor = Executors.newFixedThreadPool(5);
        ImageDataFactory imageDataFactory = new ImageDataFactory();
        networkStateLiveData = Transformations.switchMap(imageDataFactory.getMutableLiveData(), dataSource -> dataSource.getNetworkState());

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(2)
                .setPageSize(24).build();

        imageLiveData = new LivePagedListBuilder<>(imageDataFactory, pagedListConfig)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<ListImage>> getImages(){
        return imageLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

}
