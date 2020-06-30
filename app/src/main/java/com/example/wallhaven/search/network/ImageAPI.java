package com.example.wallhaven.Activities.network;

import com.example.wallhaven.Activities.network.model.ImageResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageAPI {

    @GET("search?sorting=random")
    Call<ImageResponseDTO> getImagesWithRandomSorting(@Query("page") Long page);
}
