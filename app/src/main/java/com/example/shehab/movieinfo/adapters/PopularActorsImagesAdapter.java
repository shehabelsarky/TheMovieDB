package com.example.shehab.movieinfo.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.model.Profile;
import com.example.shehab.movieinfo.model.Result;
import com.example.shehab.movieinfo.utils.ImageLoaderUtility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularActorsImagesAdapter extends RecyclerView.Adapter<PopularActorsImagesAdapter.ViewHolder> {

    private String baseImageUrl = "http://image.tmdb.org/t/p/w342/";


    private Context mContext;
    ArrayList<Profile> popularActorsImagesList;
    int width;
    int height;

    public PopularActorsImagesAdapter(Context mContext, ArrayList<Profile> popularActorsImagesList) {
        this.mContext = mContext;
        this.popularActorsImagesList = popularActorsImagesList;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }
        DisplayMetrics metrics = new DisplayMetrics();
        if (display != null) {
            display.getMetrics(metrics);
        }
        width = metrics.widthPixels;
        height = metrics.heightPixels;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_actors, parent, false);
        int width = parent.getMeasuredWidth();
        int height = parent.getMeasuredHeight();  //2150

        view.setMinimumWidth(width);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(baseImageUrl + popularActorsImagesList.get(position).getFilePath(), holder.categoryImage, ImageLoaderUtility.getDisplayImageOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return popularActorsImagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_category)
        ImageView categoryImage;
        @BindView(R.id.tv_category)
        TextView categoryText;
        @BindView(R.id.pb_category_image_loading)
        ProgressBar progressBar;
        @BindView(R.id.imageContainer)
        FrameLayout imageContainer;
        @BindView(R.id.llMaskImage)
        LinearLayout llMaskImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            llMaskImage.setVisibility(View.GONE);
            categoryText.setVisibility(View.GONE);
            if (width == 1080 && height >= 2094) {
                imageContainer.getLayoutParams().width = adjustWithDP(200, mContext);
                imageContainer.getLayoutParams().height = adjustWithDP(200, mContext);
            }/*else if (width == 1080 && height ==)*/ else {
                int screenSize = mContext.getResources().getConfiguration().screenLayout &
                        Configuration.SCREENLAYOUT_SIZE_MASK;
                switch (screenSize) {
                    case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                        imageContainer.getLayoutParams().width = adjustWithDP(250, mContext);

                        imageContainer.getLayoutParams().height = adjustWithDP(250, mContext);

                        //set action

                        break;
                    case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
                        break;
                    case Configuration.SCREENLAYOUT_SIZE_LARGE:
                        imageContainer.getLayoutParams().width = adjustWithDP(200, mContext);
                        imageContainer.getLayoutParams().height = adjustWithDP(200, mContext);
                        //set action
                        break;

                    case Configuration.SCREENLAYOUT_SIZE_NORMAL:   //in case it is note ,go here
                        imageContainer.getLayoutParams().width = adjustWithDP(163, mContext);
                        imageContainer.getLayoutParams().height = adjustWithDP(163, mContext);

                        break;
                    case Configuration.SCREENLAYOUT_SIZE_SMALL:
                        imageContainer.getLayoutParams().width = adjustWithDP(100, mContext);
                        imageContainer.getLayoutParams().height = adjustWithDP(100, mContext);
                        ;
                        break;
                    default:

                }
            }

        }


    }


    public static int adjustWithDP(float value, Context mContext) {
        return (int) (value * mContext.getResources().getDisplayMetrics().density + 0.5f);
    }
}
