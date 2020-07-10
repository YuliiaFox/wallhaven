package com.example.wallhaven.results.domain;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.wallhaven.NetworkClient;
import com.example.wallhaven.results.model.Image;
import com.example.wallhaven.results.network.model.ImageResponseDTO;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDataSource extends PageKeyedDataSource<Long, Image> {

    private static final int RESPONSE_SIZE = 24;
    private List<String> tadIds;

    public ImageDataSource(List<String> tagIds) {
        this.tadIds = tagIds;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Image> callback) {

        NetworkClient controller = new NetworkClient();
        controller.getImageAPI().getImagesWithRandomSorting(1L, tadIds).enqueue(new Callback<ImageResponseDTO>() {
            @Override
            public void onResponse(Call<ImageResponseDTO> call, Response<ImageResponseDTO> response) {
                if (response.isSuccessful()) {
                    Converter converter = new Converter();
                    callback.onResult(converter.convertDtoImageToImage(Objects.requireNonNull(response.body()).data), null, 2L);

                }
            }

            @Override
            public void onFailure(Call<ImageResponseDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Image> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Image> callback) {
        NetworkClient controller = new NetworkClient();
        controller.getImageAPI().getImagesWithRandomSorting(params.key, tadIds).enqueue(new Callback<ImageResponseDTO>() {
            @Override
            public void onResponse(Call<ImageResponseDTO> call, Response<ImageResponseDTO> response) {
                if (response.isSuccessful()) {
                    Converter converter = new Converter();
                    Long nextKey = (RESPONSE_SIZE == Objects.requireNonNull(response.body()).data.size() ? params.key + 1 : null);
                    callback.onResult(converter.convertDtoImageToImage(Objects.requireNonNull(response.body()).data), nextKey);
                }
            }

            @Override
            public void onFailure(Call<ImageResponseDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
