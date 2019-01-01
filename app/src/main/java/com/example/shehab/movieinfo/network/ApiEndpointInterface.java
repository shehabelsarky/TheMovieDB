package com.example.shehab.movieinfo.network;


import com.example.shehab.movieinfo.model.PopularActorDetails;
import com.example.shehab.movieinfo.model.PopularPersonsResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pentavalue on 6/30/2017.
 */

public interface ApiEndpointInterface {

    @GET(ApiUrls.POPULAR_ACTORS)
    Call<PopularPersonsResponse> popularPersons(@Query("api_key") String apiKey, @Query("page") int page);

    @GET(ApiUrls.POPULAR_ACTOR_IMAGES)
    Call<PopularPersonsResponse> popularPersonImages(@Path(value = ApiUrls.POPULAR_PERSON, encoded = true) String personId,@Query("api_key") String apiKey);

    @GET(ApiUrls.POPULAR_ACTOR_DETAILS)
    Call<PopularActorDetails> popularPersonDetails(@Path(value = ApiUrls.POPULAR_PERSON, encoded = true) String personId, @Query("api_key") String apiKey);


    @GET(ApiUrls.SEARCH_ACTORS)
    Call<PopularPersonsResponse> searchPersons(@Query("api_key") String apiKey, @Query("page") int page,@Query("query") String actorName);
}