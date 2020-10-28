package com.example.wallhaven.tags;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wallhaven.NetworkClient;
import com.example.wallhaven.tags.domain.Converter;
import com.example.wallhaven.tags.model.Tag;
import com.example.wallhaven.tags.network.model.AutocompleteSuggestions;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagsViewModel extends ViewModel {
    private final MutableLiveData<List<Tag>> tagLiveData;
    private NetworkClient networkClient;
    private Converter converter;

    public TagsViewModel() {
        tagLiveData = new MutableLiveData<>();
        networkClient = new NetworkClient();
        converter = new Converter();
    }

    public LiveData<List<Tag>> getTagLiveData() {
        return tagLiveData;
    }

    public void searchTags(String inputFromTagField) {
        networkClient.getSuggestedTags().getTagsAutocompleteSuggestions(inputFromTagField).enqueue(new Callback<AutocompleteSuggestions>() {
            @Override
            public void onResponse(Call<AutocompleteSuggestions> call, Response<AutocompleteSuggestions> response) {
                tagLiveData.postValue(converter.convertResultDtoToTagList(Objects.requireNonNull(response.body())));
            }

            @Override
            public void onFailure(Call<AutocompleteSuggestions> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
