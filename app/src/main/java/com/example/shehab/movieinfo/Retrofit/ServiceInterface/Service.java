package com.example.shehab.movieinfo.Retrofit.ServiceInterface;

import com.example.shehab.movieinfo.Retrofit.Model.RetroModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Shehab on 11/02/2018.
 */

public interface Service {

    String baseURL = "https://api.themoviedb.org/";

    @GET("3/movie/popular?api_key=aede46592fa18e618a51016ae6036c11")
    Call<List<RetroModel>>getPosts();

}
