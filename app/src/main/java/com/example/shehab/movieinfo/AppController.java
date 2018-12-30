package com.example.shehab.movieinfo;


import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.view.Display;
import android.view.WindowManager;

import com.example.shehab.movieinfo.datastorage.AppSharedPrefs;
import com.example.shehab.movieinfo.datastorage.PrefsConstant;
import com.example.shehab.movieinfo.utils.Functions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by MinaSamir
 */
public class AppController extends MultiDexApplication /*implements ApiMethods.UserTokenCallback*//*, ApiMethods.LoginCallback */{


    private static AppController instance;
    private static Context mContext;
    private final int MAX_SIZE_DISKCACHE = 30;


    public static AppController getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {

        super.onCreate();
       // Fabric.with(this, new Crashlytics());
        instance = this;
        mContext=this;
        initImageLoader(getApplicationContext());
        initLanguage();

    }

    private void initLanguage() {
        if(AppSharedPrefs.getStringVal(PrefsConstant.LANG).equals("ar")){
            Functions.changeLocals(getBaseContext(), "ar");
        }else {
            Functions.changeLocals(getBaseContext(), "en");
        }
    }


    private void initImageLoader(Context context) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width_screen = display.getWidth();
        int height_screen = display.getHeight();
        int memoryCacheSize;
        int minMemory = 2097152; // 2 MB
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        memoryCacheSize = Math.min(minMemory, (memClass / 8) * 1048576); // 1/8 of app memory limit - 1048576 = 1024*1024
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheExtraOptions(width_screen, height_screen)
                .memoryCacheSize(memoryCacheSize).diskCacheExtraOptions(width_screen, height_screen,null)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(MAX_SIZE_DISKCACHE * 1048576).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

}
