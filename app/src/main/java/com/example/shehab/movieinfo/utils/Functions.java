package com.example.shehab.movieinfo.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;


public class Functions {

    public static void changeLocals(Context context, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
