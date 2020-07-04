package com.example.wallhaven.searchingresults.network;

import com.example.wallhaven.searchingresults.network.model.ImageResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageAPI {

    @GET("api/v1/search?sorting=random")
    Call<ImageResponseDTO> getImagesWithRandomSorting(@Query("page") Long page);
}
