package com.example.wallhaven.results;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.wallhaven.filters.domain.TagIdConverter;
import com.example.wallhaven.results.domain.ImageDataSource;
import com.example.wallhaven.results.model.Image;
import com.example.wallhaven.results.model.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImagesViewModel extends ViewModel {
    private Executor executor;
    private LiveData<PagedList<Image>> imageLiveData;
    private MutableLiveData<List<Tag>> tagIdsLiveData = new MutableLiveData<>();

    public ImagesViewModel() {
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

        imageLiveData = Transformations.map(tagIdsLiveData, tags -> {
            PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(2)
                    .setPageSize(24).build();
            TagIdConverter converter = new TagIdConverter();

            ImageDataSource dataSource = new ImageDataSource(converter.convert(tags));
            return new PagedList.Builder<>(dataSource, pagedListConfig)
                    .setFetchExecutor(executor)
                    .setNotifyExecutor(new Executor() {
                        Handler mainThreadHandler = new Handler(Looper.getMainLooper());

                        @Override
                        public void execute(Runnable command) {
                            mainThreadHandler.post(command);
                        }
                    })
                    .build();
        });
        tagIdsLiveData.postValue(new LinkedList<>());
    }

    public LiveData<PagedList<Image>> getImages() {
        return imageLiveData;
    }

    public LiveData<List<Tag>> getTags() {
        return tagIdsLiveData;
    }

    public void loadDataWithNewTag(@Nullable final Tag tag) {
        List<Tag> tags = tagIdsLiveData.getValue();
        Objects.requireNonNull(tags).add(tag);
        tagIdsLiveData.postValue(tags);
    }

    public void refreshDataWithoutTag(Tag tag) {
        List<Tag> tags = tagIdsLiveData.getValue();
        Objects.requireNonNull(tags).remove(tag);
        tagIdsLiveData.postValue(tags);
    }
}
