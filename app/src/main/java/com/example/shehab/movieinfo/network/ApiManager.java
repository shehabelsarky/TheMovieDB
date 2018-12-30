package com.example.shehab.movieinfo.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

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


   /* public void signUpUser(User user,ResponseListener responseListener){
        Call<User> call = networkInterface.signUpUser(user.getName(),user.getEmail(),user.getMobile(),user.getPassword(),user.getGender(),user.getBirthDate(), String.valueOf(user.getZone()),user.getProfilePic(),user.getLocationType(),user.getCoordinates());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.SIGN_UP_SERVICE);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void updateProfile(User user, ResponseListener responseListener) {
        Call<User> call = networkInterface.updateProfile(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),user*//*user.getName(),user.getEmail(),user.getMobile(),user.getGender(),user.getBirthDate(), String.valueOf(user.getZone()),user.getProfilePic(),user.getLocationType(),user.getCoordinates()*//*);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.UPDATE_PROFILE);
                    responseListener.onSuccess(response);
                } else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void loginUser(final User user,ResponseListener responseListener) {
        Call<User> call = networkInterface.loginUser(user.getUserName(),user.getPassword(),user.getAccessToken(),user.getSocialID(),user.getSocialType(),user.getIsSocial());
        call.clone().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType("Login");
                    responseListener.onSuccess(response);
                } else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    *//*public void registerDevice(String token,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.registerDevice(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_USERID,0), GoAppConstants.ANDROID,token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.REGISTER_DEVICE);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }*//*

    public void fbLogin(ResponseListener responseListener){}

    public void gLogin(){}

    public void logOut(ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.logout(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""));
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().setType("Logout");
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getPolicy(ResponseListener responseListener){
        Call<Policy> call = networkInterface.getPolicy(GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Policy>() {
            @Override
            public void onResponse(Call<Policy> call, Response<Policy> response) {
                response.body().setType(ApiUrls.POLICY);
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Policy> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void resetPassword(String mail,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.resetPassword(mail);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getPlacesCategories(ResponseListener responseListener){
        Call<Category> call = networkInterface.getPlacesCategories(GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getEventsCategories(ResponseListener responseListener){
        Call<Category> call = networkInterface.getEventsCategories(GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType("Category");
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void getUberLog(int placeID,ResponseListener responseListener){
        Call<Category> call = networkInterface.getUberLog(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeID);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void getUberEventLog(int placeID,ResponseListener responseListener){
        Call<Category> call = networkInterface.getUberEventLog(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeID);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void getSharePlaceLog(int placeID,ResponseListener responseListener){
        Call<Category> call = networkInterface.getSharePlaceLog(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeID);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void getShareEventLog(int eventId,ResponseListener responseListener){
        Call<Category> call = networkInterface.getShareEventLog(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),eventId);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getEventViewLog(int eventId,ResponseListener responseListener){
        Call<Category> call = networkInterface.getEventViewLog(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),eventId);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getPlaceViewLog(int placeID,ResponseListener responseListener){
        Call<Category> call = networkInterface.getPlaceViewLog(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeID);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void getPlacesInCategory(int deptId,int page,String lat,String lng, ResponseListener responseListener){

        Call<Place> call = networkInterface.getPlacesInCategory(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),deptId,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0),page,lat,lng);
        call.enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getEventsInCategory(int deptId,int page,ResponseListener responseListener){
        Call<Event> call = networkInterface.getEventsInCategory(deptId,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0),page);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.EVENTS_IN_CATEGORIES);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getEventsOnDate(String date,int catId,ResponseListener responseListener){
        Call<Event.EventsOnDate> call = networkInterface.getEventsOnDate(date,catId,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Event.EventsOnDate>() {
            @Override
            public void onResponse(Call<Event.EventsOnDate> call, Response<Event.EventsOnDate> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.EVENTS_ON_DATE);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Event.EventsOnDate> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getPlace(int placeId,ResponseListener responseListener){
        Call<Place.EContent> call = networkInterface.getPlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeId,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Place.EContent>() {
            @Override
            public void onResponse(Call<Place.EContent> call, Response<Place.EContent> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.PLACE_DETAILS);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Place.EContent> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void checkInPlace(int placeId,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.checkInPlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().setType(ApiUrls.PLACE_CHECK_IN);
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getEvent(int eventId,ResponseListener responseListener){
        Call<Event.EContent> call = networkInterface.getEvent(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),eventId,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Event.EContent>() {
            @Override
            public void onResponse(Call<Event.EContent> call, Response<Event.EContent> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.EVENT_DETAILS);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Event.EContent> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getUserNewsFeed(int userId,ResponseListener responseListener){
        Call<TimeLineItem> call = networkInterface.getUserNewsFeed(*//*GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),*//*userId,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<TimeLineItem>() {
            @Override
            public void onResponse(Call<TimeLineItem> call, Response<TimeLineItem> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<TimeLineItem> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getNewsFeed(int userId,int page,ResponseListener responseListener){
        Call<TimeLineItem> call = networkInterface.getFeed( GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""), userId,page,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<TimeLineItem>() {
            @Override
            public void onResponse(Call<TimeLineItem> call, Response<TimeLineItem> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<TimeLineItem> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getFavorite(ResponseListener responseListener){
        Call<Place> call = networkInterface.getFavoritePlaces(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getFollowing(ResponseListener responseListener){
        Call<Place> call = networkInterface.getFollowingPlaces(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if(response.body()!=null && response.code()!=500) {
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getGoing(ResponseListener responseListener){
        Call<Event> call = networkInterface.getGoingEvents(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.EVENT_GOING);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getTrendingSearch(ResponseListener responseListener){
        Call<TrendingSearch> call= networkInterface.getTrendingSearch();
        call.enqueue(new Callback<TrendingSearch>() {
            @Override
            public void onResponse(Call<TrendingSearch> call, Response<TrendingSearch> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.TRENDING_SEARCH);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<TrendingSearch> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getSearchResult(String q, String lat, String lng, ResponseListener responseListener){
        Call<Discover> call = networkInterface.getSearchResult(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),q,lat,lng,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Discover>() {
            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {
                if(response.body()!=null && response.code()!=500)
                    responseListener.onSuccess(response);
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Discover> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getAutoCompleteResult(String q, String lat, String lng, ResponseListener responseListener){
        Call<Discover> call = networkInterface.autoCompleteSearch(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),q,lat,lng,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Discover>() {
            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {
                if(response.body()!=null && response.code()!=500)
                    responseListener.onSuccess(response);
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Discover> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }
    public void getFilterPlaces(Filter filter,ResponseListener responseListener){
        Call<Place> call = networkInterface.makeMyDay(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),filter,GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if(response.body()!=null && response.code()!=500){
                    response.body().setType(ApiUrls.MAKE_MY_DAY);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getMessages(ResponseListener responseListener){
        Call<EContentMessage> call = networkInterface.getMessages(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""));
        call.enqueue(new Callback<EContentMessage>() {
            @Override
            public void onResponse(Call<EContentMessage> call, Response<EContentMessage> response) {
                if(response.body()!=null && response.code()!=500){
                    response.body().setType(ApiUrls.MESSAGES_LIST);
                    responseListener.onSuccess(response);
                }

                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<EContentMessage> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getChat(int messageId,ResponseListener responseListener){
        Call<EContentMessage> call = networkInterface.getChat(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),messageId);
        call.enqueue(new Callback<EContentMessage>() {
            @Override
            public void onResponse(Call<EContentMessage> call, Response<EContentMessage> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType("");
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<EContentMessage> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getChatPlace(int placeId,ResponseListener responseListener){
        Call<EContentMessage> call = networkInterface.getChatPlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeId);
        call.enqueue(new Callback<EContentMessage>() {
            @Override
            public void onResponse(Call<EContentMessage> call, Response<EContentMessage> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType("");
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<EContentMessage> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void postRating(Rate rate, ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.ratePlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),rate);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body()!=null && response.code()!=500)
                    responseListener.onSuccess(response);
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void postFavorite(int placeId,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.postFavoritePlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().setType(ApiUrls.FAVORITE_PLACE);
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void postGoing(int eventId,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.postGoingEvent(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),eventId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().setType(ApiUrls.GOING_EVENTS);
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void postFollow(int placeId,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.postFollowPlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),placeId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body()!=null && response.code()!=500){
                    response.body().setType(ApiUrls.FOLLOW_PLACE);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void postMessage(int placeId,int messageId,String message,ResponseListener responseListener){
        Call<ApiResponse> call = networkInterface.postMessage(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),messageId,placeId,message);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 500){
                    Toast.makeText(mContext,response.message(),Toast.LENGTH_LONG).show();
                }else{
                    response.body().setType("Message Sent");
                    responseListener.onSuccess(response);
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });

    }

    public void changePassword(String pass, String newPass, String confirmPass, ResponseListener responseListener) {
        Call<ApiResponse> call = networkInterface.changePassword(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),pass,newPass,confirmPass);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body()!=null && response.code()!=500){
                    response.body().setType(ApiUrls.CHANGE_PASSWORD);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getFilteredEvents(Filter filter,int deptId, ResponseListener responseListener) {
        Call<Event> call = networkInterface.getFilteredEvents(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),filter,deptId);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.body()!=null && response.code()!=500){
                    response.body().setType(ApiUrls.FILTERED_EVENT);
                    responseListener.onSuccess(response);
                }

                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getZones(ResponseListener responseListener){
        Call<Zone> call = networkInterface.getZones(GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<Zone>() {
            @Override
            public void onResponse(Call<Zone> call, Response<Zone> response) {
                if(response.body()!=null && response.code()!=500){
                    response.body().setType("Zone");
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Zone> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getAbout(ResponseListener responseListener){
        Call<About> call = networkInterface.getAbout(GoAppPreferences.getPrefs(mContext).getInt(GoAppPreferences.KEY_LANG,0));
        call.enqueue(new Callback<About>() {
            @Override
            public void onResponse(Call<About> call, Response<About> response) {
                response.body().setType(ApiUrls.GET_ABOUT_US);
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<About> call, Throwable t) {

            }
        });
    }

    public void deleteMessage(long itemId,ResponseListener responseListener) {
        Call<ApiResponse> call = networkInterface.deleteChat(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION,""),itemId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body()!=null && response.code()!=500) {
                    response.body().setType(ApiUrls.MESSAGE_DELETE);
                    responseListener.onSuccess(response);
                }
                else
                    Toast.makeText(mContext,"Something went Wrong",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void getMyPlacesAndEvents(ResponseListener responseListener) {
        Call<UserAssets> call = networkInterface.getMyPlacesAndEvents(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""));
        call.enqueue(new Callback<UserAssets>() {
            @Override
            public void onResponse(@NonNull Call<UserAssets> call, @NonNull Response<UserAssets> response) {
                if (response.body() != null && response.code() != 500) {
                    if ((response.body()).getECode() == ApiResponse.SUCCESS) {
                        if (response.body() != null) {
                            GoAppApplication.mUserPlacesAndEvents = response.body().geteContent();
                            responseListener.onSuccess(response);
                        }
                    }
                } else {
                    responseListener.onFailure();
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserAssets> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void editPlace(EditPlace place, ResponseListener responseListener) {
        Call<EditResponse> call = networkInterface.editPlace(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), place);
        call.enqueue(new Callback<EditResponse>() {
            @Override
            public void onResponse(@NonNull Call<EditResponse> call, @NonNull Response<EditResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EditResponse> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void editEvent(EditEvent event, ResponseListener responseListener) {
        Call<EditResponse> call = networkInterface.editEvent(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), event);
        call.enqueue(new Callback<EditResponse>() {
            @Override
            public void onResponse(@NonNull Call<EditResponse> call, @NonNull Response<EditResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EditResponse> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void uploadImg(ServerImage img, ResponseListener responseListener) {
        Call<UploadImgResponse> call = networkInterface.uploadImg(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), img);
        call.enqueue(new Callback<UploadImgResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadImgResponse> call, @NonNull Response<UploadImgResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadImgResponse> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void updateCover(UpdateCoverRequest updateCoverRequest, ResponseListener responseListener) {
        Call<UploadImgResponse> call = networkInterface.updateCover(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), updateCoverRequest);
        call.enqueue(new Callback<UploadImgResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadImgResponse> call, @NonNull Response<UploadImgResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    responseListener.onFailure();
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadImgResponse> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }


    public void uploadMenu(UploadMenuRequest menu, ResponseListener responseListener) {
        Call<UploadMenuResponse> call = networkInterface.uploadMenu(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), menu);
        call.enqueue(new Callback<UploadMenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadMenuResponse> call, @NonNull Response<UploadMenuResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                    responseListener.onFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadMenuResponse> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void uploadMedia(UploadMediaRequest uploadMediaRequest, ResponseListener responseListener) {
        Call<UploadMenuResponse> call = networkInterface.uploadMedia(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), uploadMediaRequest);
        call.enqueue(new Callback<UploadMenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadMenuResponse> call, @NonNull Response<UploadMenuResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                    responseListener.onFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadMenuResponse> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void addPromo(AddPromoRequest promo, ResponseListener responseListener) {
        Call<JSONObject> call = networkInterface.addPromo(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), promo);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(@NonNull Call<JSONObject> call, @NonNull Response<JSONObject> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                    responseListener.onFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JSONObject> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }

    public void editDeletePromo(EditPromoRequest promo, ResponseListener responseListener) {
        Call<JSONObject> call = networkInterface.editDeletePromo(GoAppPreferences.getPrefs(mContext).getString(GoAppPreferences.KEY_SESSION, ""), promo);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(@NonNull Call<JSONObject> call, @NonNull Response<JSONObject> response) {
                if (response.body() != null && response.code() == 200) {
                    responseListener.onSuccess(response);
                } else {
                    Toast.makeText(mContext, "Something went Wrong", Toast.LENGTH_LONG).show();
                    responseListener.onFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JSONObject> call, @NonNull Throwable t) {
                responseListener.onFailure();
            }
        });
    }*/
}
