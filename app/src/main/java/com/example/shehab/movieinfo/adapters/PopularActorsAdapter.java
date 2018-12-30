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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.model.Result;
import com.example.shehab.movieinfo.utils.ImageLoaderUtility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularActorsAdapter extends RecyclerView.Adapter<PopularActorsAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    private String baseImageUrl="http://image.tmdb.org/t/p/w342/";


    private Context mContext;
    ArrayList<Result> popularActorsList;
    int width;
    int height;

    public PopularActorsAdapter(Context mContext, ArrayList<Result> popularActorsList) {
        this.mContext = mContext;
        this.popularActorsList = popularActorsList;
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
        ImageLoader.getInstance().displayImage(baseImageUrl+popularActorsList.get(position).getProfilePath(), holder.categoryImage, ImageLoaderUtility.getDisplayImageOptions(), new ImageLoadingListener() {
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
        holder.categoryText.setText(popularActorsList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return popularActorsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_category)
        ImageView categoryImage;
        @BindView(R.id.tv_category)
        TextView categoryText;
        @BindView(R.id.pb_category_image_loading)
        ProgressBar progressBar;
        @BindView(R.id.imageContainer)
        FrameLayout imageContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int adapterPosition);
    }

    public void setOnItemClickListener(final OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public static int adjustWithDP(float value, Context mContext) {
        return (int) (value * mContext.getResources().getDisplayMetrics().density + 0.5f);
    }
}
