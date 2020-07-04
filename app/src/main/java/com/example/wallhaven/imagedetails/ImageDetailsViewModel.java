package com.example.wallhaven.imagedetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wallhaven.NetworkClient;
import com.example.wallhaven.imagedetails.domain.ImageDetailsConverter;
import com.example.wallhaven.imagedetails.model.ImageDetails;
import com.example.wallhaven.imagedetails.network.model.ImageDetailsDto;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDetailsViewModel extends ViewModel {
    private final MutableLiveData<ImageDetails> imageDetailsMutableLiveData;
    NetworkClient networkClient;
    ImageDetailsConverter converter;

    public ImageDetailsViewModel() {
        imageDetailsMutableLiveData = new MutableLiveData<>();
        networkClient = new NetworkClient();
        converter = new ImageDetailsConverter();
    }

    public LiveData<ImageDetails> getImageDetailsMutableLiveData(String id) {
        networkClient.getImageDetails().getImageDetailById(id).enqueue(new Callback<ImageDetailsDto>() {
            @Override
            public void onResponse(Call<ImageDetailsDto> call, Response<ImageDetailsDto> response) {
                imageDetailsMutableLiveData.postValue(converter.convert(Objects.requireNonNull(response.body())));
            }
            @Override
            public void onFailure(Call<ImageDetailsDto> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return imageDetailsMutableLiveData;
    }
}
