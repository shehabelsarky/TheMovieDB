package com.example.shehab.movieinfo.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.shehab.movieinfo.model.PopularActorDetails;
import com.example.shehab.movieinfo.model.PopularPersonsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pentavalue on 6/30/2017.
 */

public class ApiManager {
    public static ApiManager apiManager = null;
    private Retrofit retrofit;
    protected static ApiEndpointInterface networkInterface;
    private OkHttpClient.Builder httpClient;
    private Retrofit.Builder builder;
    protected static Context mContext;
    private static HttpLoggingInterceptor loggingInterceptor;

    public ApiManager() {
        init();
    }

    public static synchronized ApiManager getInstance(final Context context) {
        mContext = context;
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    private void init() {
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .build();
                    return chain.proceed(request);
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true);

        //if(BuildConfig.DEBUG){  // only enable log in debug mode to still secure my requests like password ..
            httpClient.addInterceptor(loggingInterceptor);
        //}

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        builder = new Retrofit.Builder()
                .baseUrl(ApiUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        networkInterface = retrofit.create(ApiEndpointInterface.class);
    }


    public void popularPersons(String apiKey,int page, ResponseListener responseListener) {

        Call<PopularPersonsResponse> call = networkInterface.popularPersons(apiKey,page);
        call.clone().enqueue(new Callback<PopularPersonsResponse>() {
            @Override
            public void onResponse(Call<PopularPersonsResponse> call, Response<PopularPersonsResponse> response) {

                if (response.isSuccessful()) {
                        responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                }
            }
            @Override
            public void onFailure(Call<PopularPersonsResponse> call, Throwable t) {
                t.printStackTrace();
                responseListener.onFailure();
            }
        });

    }

    public void popularPersonImages(String personId,String apiKey, ResponseListener responseListener) {

        Call<PopularPersonsResponse> call = networkInterface.popularPersonImages(personId,apiKey);
        call.clone().enqueue(new Callback<PopularPersonsResponse>() {
            @Override
            public void onResponse(Call<PopularPersonsResponse> call, Response<PopularPersonsResponse> response) {

                if (response.isSuccessful()) {
                    responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                }
            }
            @Override
            public void onFailure(Call<PopularPersonsResponse> call, Throwable t) {
                t.printStackTrace();
                responseListener.onFailure();
            }
        });

    }


    public void popularPersonDetails(String personId,String apiKey, ResponseListener responseListener) {
        Call<PopularActorDetails> call = networkInterface.popularPersonDetails(personId,apiKey);
        call.clone().enqueue(new Callback<PopularActorDetails>() {
            @Override
            public void onResponse(Call<PopularActorDetails> call, Response<PopularActorDetails> response) {

                if (response.isSuccessful()) {
                    responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                }
            }
            @Override
            public void onFailure(Call<PopularActorDetails> call, Throwable t) {
                t.printStackTrace();
                responseListener.onFailure();
            }
        });

    }
}
