package com.example.shehab.movieinfo.network;


import com.example.shehab.movieinfo.model.PopularPersonsResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pentavalue on 6/30/2017.
 */

public interface ApiEndpointInterface {

    @GET(ApiUrls.POPULAR_ACTORS)
    Call<PopularPersonsResponse> popularPersons(@Query("api_key") String apiKey, @Query("page") int page);
   /* @FormUrlEncoded
    @POST(ApiUrls.BASE_ACTION+ApiUrls.SIGN_UP_SERVICE)
    Call<User> signUpUser(@Field("Name") String name, @Field("Email") String email, @Field("Phone") String mobile, @Field("Pass") String password, @Field("Gender") String gender, @Field("DateOfBirth") String birthDate, @Field("zone") String zone, @Field("ProfilePic") String profilePic, @Field("location_type") int locType, @Field("Coordinates") String coordinates);

    //@FormUrlEncoded
    @POST(ApiUrls.BASE_ACTION+ApiUrls.UPDATE_PROFILE)
    Call<User> updateProfile(@Header("Cookie") String session, @Body User user);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.LOGIN)
    Call<User> loginUser(@Query("User") String user, @Query("Pass") String pass, @Query("accessToken") String accessToken, @Query("socialID") String socialID, @Query("socialType") String socialType, @Query("social") int social);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.LOG_OUT)
    Call<ApiResponse> logout(@Header("Cookie") String session);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.CHANGE_PASSWORD)
    Call<ApiResponse> changePassword(@Header("Cookie") String session, @Query("Pass") String pass, @Query("New") String newPass, @Query("Confirm") String confirmPassword);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.RESET_PASSWORD)
    Call<ApiResponse> resetPassword(@Query("Mail") String email);

    *//*@POST(ApiUrls.BASE_ACTION+ApiUrls.REGISTER_DEVICE)
    Call<ApiResponse> registerDevice(@Header("Cookie")String session,@Query("user_id") int userId,@Query("device_type") int os,@Query("token") String deviceId);
*//*
    @GET(ApiUrls.BASE_ACTION+ApiUrls.PLACES_IN_CATEGORIES)
    Call<Place> getPlacesInCategory(@Header("Cookie") String session, @Query("Dept_ID") int deptId, @Query("lang") int lang, @Query("page") int page, @Query("lalt") String lat, @Query("long") String lng);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.PLACES_CATEGORIES)
    Call<Category> getPlacesCategories(@Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.PLACE_DETAILS)
    Call<Place.EContent> getPlace(@Header("Cookie") String session, @Query("Place_ID") int placeId, @Query("lang") int lang);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.PLACE_CHECK_IN)
    Call<ApiResponse> checkInPlace(@Header("Cookie") String session, @Query("Place_ID") int placeId);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.UBER)
    Call<Category> getUberLog(@Header("Cookie") String session, @Query("Place_ID") int placeID);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.UBER_EVENT)
    Call<Category> getUberEventLog(@Header("Cookie") String session, @Query("Place_ID") int placeID);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.SHARE_PLACE_LOG)
    Call<Category> getSharePlaceLog(@Header("Cookie") String session, @Query("Place_ID") int placeID);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.SHARE_EVENT_LOG)
    Call<Category> getShareEventLog(@Header("Cookie") String session, @Query("Event_ID") int eventID);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.EVENT_VIEW_LOG)
    Call<Category> getEventViewLog(@Header("Cookie") String session, @Query("Event_ID") int eventID);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.PLACE_VIEW_LOG)
    Call<Category> getPlaceViewLog(@Header("Cookie") String session, @Query("Place_ID") int placeID);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.EVENTS_CATEGORIES)
    Call<Category> getEventsCategories(@Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.EVENTS_CALENDAR)
    Call<Event> getEventsInCategory(@Query("Dept_ID") int deptId, @Query("lang") int lang, @Query("page") int page);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.EVENT_DETAILS)
    Call<Event.EContent> getEvent(@Header("Cookie") String session, @Query("Event_ID") int eventId, @Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.FAVORITE_PLACES)
    Call<Place> getFavoritePlaces(@Header("Cookie") String g, @Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.FOLLOWING_PLACES)
    Call<Place> getFollowingPlaces(@Header("Cookie") String g, @Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.EVENT_GOING)
    Call<Event> getGoingEvents(@Header("Cookie") String session, @Query("lang") int lang);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.FAVORITE_PLACE)
    Call<ApiResponse> postFavoritePlace(@Header("Cookie") String sessionId, @Query("Place_ID") int placeId);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.FOLLOW_PLACE)
    Call<ApiResponse> postFollowPlace(@Header("Cookie") String sessionId, @Query("Place_ID") int placeId);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.GOING_EVENTS)
    Call<ApiResponse> postGoingEvent(@Header("Cookie") String sessionId, @Query("Event_ID") int eventId);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.USER_NEWS_FEED)
    Call<TimeLineItem> getUserNewsFeed(*//*@Header("Cookie")String session,*//* @Query("user_id") int userId, @Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.FEED)
    Call<TimeLineItem> getFeed(@Header("Cookie") String session, @Query("user_id") int userId, @Query("page") int page, @Query("lang") int lang);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.MAKE_MY_DAY)
    Call<Place> makeMyDay(@Header("Cookie") String session, @Body Filter filter, @Query("lang") int lang);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.FILTERED_EVENT)
    Call<Event> getFilteredEvents(@Header("Cookie") String session, @Body Filter filter, @Query("Dept_ID") int deptId);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.DISCOVER)
    Call<Discover> getSearchResult(@Header("Cookie") String session, @Query("q") String q, @Query("lalt") String lat, @Query("long") String lng, @Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.ZONES)
    Call<Zone> getZones(@Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.EVENTS_ON_DATE)
    Call<Event.EventsOnDate> getEventsOnDate(@Query("date") String date, @Query("cat_id") int catId, @Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.TRENDING_SEARCH)
    Call<TrendingSearch> getTrendingSearch();

    @GET(ApiUrls.BASE_ACTION+ApiUrls.MESSAGES_LIST)
    Call<EContentMessage> getMessages(@Header("Cookie") String session);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.MESSAGE_DISPLAY)
    Call<EContentMessage> getChat(@Header("Cookie") String session, @Query("ID") int id);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.MESSAGE_DISPLAY)
    Call<EContentMessage> getChatPlace(@Header("Cookie") String session, @Query("place_id") int id);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.MESSAGE_SEND)
    Call<ApiResponse> postMessage(@Header("Cookie") String session, @Query("ID") int id, @Query("To_ID") int placeId, @Query("Content") String content);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.MESSAGE_DELETE)
    Call<ApiResponse> deleteChat(@Header("Cookie") String session, @Query("To_ID") long id);

    @POST(ApiUrls.BASE_ACTION+ApiUrls.RATE_PLACE)
    Call<ApiResponse> ratePlace(@Header("Cookie") String session, @Body Rate rate);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.POLICY)
    Call<Policy> getPolicy(@Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.GET_ABOUT_US)
    Call<About> getAbout(@Query("lang") int lang);

    @GET(ApiUrls.BASE_ACTION + ApiUrls.PLACES_AND_EVENTS)
    Call<UserAssets> getMyPlacesAndEvents(@Header("Cookie") String session);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.EDIT_PLACE)
    Call<EditResponse> editPlace(@Header("Cookie") String session, @Body EditPlace editPlace);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.EDIT_EVENT)
    Call<EditResponse> editEvent(@Header("Cookie") String session, @Body EditEvent event);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.UPLOAD_IMAGE)
    Call<UploadImgResponse> uploadImg(@Header("Cookie") String session, @Body ServerImage img);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.UPLOAD_COVER)
    Call<UploadImgResponse> updateCover(@Header("Cookie") String session, @Body UpdateCoverRequest updateCoverRequest);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.UPLOAD_MENU)
    Call<UploadMenuResponse> uploadMenu(@Header("Cookie") String session, @Body UploadMenuRequest menu);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.ADD_PROMO)
    Call<JSONObject> addPromo(@Header("Cookie") String session, @Body AddPromoRequest promoRequest);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.EDIT_DELETE_PROMO)
    Call<JSONObject> editDeletePromo(@Header("Cookie") String session, @Body EditPromoRequest promoRequest);

    @POST(ApiUrls.BASE_ACTION + ApiUrls.UPLOAD_MEDIA)
    Call<UploadMenuResponse> uploadMedia(@Header("Cookie") String session, @Body UploadMediaRequest media);

    @GET(ApiUrls.BASE_ACTION+ApiUrls.AUTO_COMPLETE)
    Call<Discover> autoCompleteSearch(@Header("Cookie") String session, @Query("q") String q, @Query("lalt") String lat, @Query("long") String lng, @Query("lang") int lang);


*/
}