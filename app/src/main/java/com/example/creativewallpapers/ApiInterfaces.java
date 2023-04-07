package com.example.creativewallpapers;

import static com.example.creativewallpapers.ApiUtilities.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterfaces {
    String BASE_URL="https://api.pexels.com/v1/";

    @Headers("Authorization: "+ API)
    @GET("curated")
    Call<SearchModels> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+ API)
    @GET("search")
    Call<SearchModels> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );




}
