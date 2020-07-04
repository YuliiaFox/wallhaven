package com.example.wallhaven.searchbytag.network;

import com.example.wallhaven.searchbytag.network.model.AutocompleteSuggestions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AutocompleteAPI {
    @GET("autocomplete.php/{term}")
    Call<AutocompleteSuggestions> getTagsAutocompleteSuggestions(@Path("term") String term);
}
