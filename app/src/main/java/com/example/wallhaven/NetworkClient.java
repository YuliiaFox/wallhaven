package com.example.wallhaven;

import com.example.wallhaven.imagedetails.network.ImageDetailsAPI;
import com.example.wallhaven.searchingresults.network.ImageAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    static String BASE_URL = "https://wallhaven.cc/";
    public static final String API_KEY = "wlHZJOPWfWFW3VZcTvH7vzYsi5oHRVPq";

    private Retrofit retrofit;

    public NetworkClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl httpUrl = request
                                .url()
                                .newBuilder()
                                .addQueryParameter("apikey", API_KEY)
                                .build();
                        request = request.newBuilder().url(httpUrl).build();
                        return chain.proceed(request);
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public ImageAPI getImageAPI() {
        return retrofit.create(ImageAPI.class);
    }

    public ImageDetailsAPI getImageDetails() {
        return retrofit.create(ImageDetailsAPI.class);
    }
}
