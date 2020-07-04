package com.example.wallhaven.imagedetails.network;

import com.example.wallhaven.imagedetails.network.model.ImageDetailsDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImageDetailsAPI {

    @GET("api/v1/w/{id}")
    Call<ImageDetailsDto> getImageDetailById(@Path("id") String id);
}
