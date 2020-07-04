package com.example.wallhaven.searchingresults.domain;

        import androidx.annotation.NonNull;
        import androidx.lifecycle.MutableLiveData;
        import androidx.paging.PageKeyedDataSource;

        import java.util.Objects;

        import com.example.wallhaven.NetworkClient;
        import com.example.wallhaven.searchingresults.model.Image;
        import com.example.wallhaven.searchingresults.model.NetworkState;
        import com.example.wallhaven.searchingresults.network.model.ImageResponseDTO;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class ImageDataSource extends PageKeyedDataSource<Long, Image> {

    public static final int RESPONSE_SIZE = 24;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;


    public ImageDataSource() {
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Image> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        NetworkClient controller = new NetworkClient();
        controller.getImageAPI().getImagesWithRandomSorting(1L).enqueue(new Callback<ImageResponseDTO>() {
            @Override
            public void onResponse(Call<ImageResponseDTO> call, Response<ImageResponseDTO> response) {
                if (response.isSuccessful()) {
                    Converter converter = new Converter();
                    callback.onResult(converter.convertDtoImageToImage(Objects.requireNonNull(response.body()).data), null, 2L);
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                } else {
                    initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
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
        networkState.postValue(NetworkState.LOADING);
        NetworkClient controller = new NetworkClient();
        controller.getImageAPI().getImagesWithRandomSorting(params.key).enqueue(new Callback<ImageResponseDTO>() {
            @Override
            public void onResponse(Call<ImageResponseDTO> call, Response<ImageResponseDTO> response) {
                if (response.isSuccessful()) {
                    Converter converter = new Converter();
                    Long nextKey = (RESPONSE_SIZE == Objects.requireNonNull(response.body()).data.size() ? params.key + 1 : null);
                    callback.onResult(converter.convertDtoImageToImage(Objects.requireNonNull(response.body()).data), nextKey);
                    networkState.postValue(NetworkState.LOADED);
                } else {
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ImageResponseDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }
}
