package com.example.shehab.movieinfo.network;

public class ApiUrls {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String POPULAR_ACTORS  ="person/popular";
    public static final String POPULAR_PERSON  ="person_id";
    public static final String POPULAR_ACTOR_IMAGES = "person/{" + POPULAR_PERSON + "}/images";
    public static final String POPULAR_ACTOR_DETAILS = "person/{" + POPULAR_PERSON + "}";


}
