package com.example.shehab.movieinfo.network;

import retrofit2.Response;

/**
 * Created by pentavalue on 7/1/2017.
 */

public interface ResponseListener {
    void onSuccess(Response response);
    void onFailure();
}
