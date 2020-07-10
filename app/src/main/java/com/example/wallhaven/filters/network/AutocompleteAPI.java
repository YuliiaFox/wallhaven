package com.example.wallhaven.filters.network;

import com.example.wallhaven.filters.network.model.AutocompleteSuggestions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AutocompleteAPI {
    @GET("autocomplete.php/")
    Call<AutocompleteSuggestions> getTagsAutocompleteSuggestions(@Query("term") String term);
}
