package com.example.wallhaven.results.network;

import com.example.wallhaven.results.network.model.ImageResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageAPI {

    @GET("api/v1/search")
    Call<ImageResponseDTO> getImagesWithRandomSorting(@Query("page") Long page, @Query("q") List<String> tagId);
}
