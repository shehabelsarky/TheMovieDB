package com.example.shehab.movieinfo.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageLoaderUtility {
    private static DisplayImageOptions displayImageOptions;
    public static DisplayImageOptions getDisplayImageOptions(){
        if(displayImageOptions == null){
            displayImageOptions = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    //.showImageOnLoading(R.mipmap.image)
                    .cacheInMemory(false)
                    .cacheOnDisk(true)
                    .build();
        }
        return displayImageOptions;
    }
}
