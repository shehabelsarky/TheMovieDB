package com.example.shehab.movieinfo.datastorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shehab.movieinfo.AppController;


public class AppSharedPrefs {

    private static final String SHARED_PREFS_NAME = "TheMovieDB";

    public static SharedPreferences getSharedPreferences() {

        return AppController.getInstance().getSharedPreferences(
                SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * function to clear all data
     */
    public static void ClearSherdPrefs()
    {
        getSharedPreferences().edit().clear().commit();
    }

    public static void saveStringPrefs(String key, String val)
    {
        getSharedPreferences().edit().putString(key,val).commit();
    }

    public static String getStringVal(String key)
    {
        return getSharedPreferences().getString(key,"");
    }

    public static void saveBoleanVal(String key, boolean val){
        getSharedPreferences().edit().putBoolean(key,val).commit();
    }

    public static boolean getBoleanVal(String key){
        return getSharedPreferences().getBoolean(key,true);
    }
}
