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
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPopularActorsAdapter extends RecyclerView.Adapter<SearchPopularActorsAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    private String baseImageUrl = "http://image.tmdb.org/t/p/w342/";


    private Context mContext;
    ArrayList<Result> popularActorsList;

    public SearchPopularActorsAdapter(Context mContext, ArrayList<Result> popularActorsList) {
        this.mContext = mContext;
        this.popularActorsList = popularActorsList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_actor_basic_details, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(baseImageUrl + popularActorsList.get(position).getProfilePath(), holder.actorImage, ImageLoaderUtility.getDisplayImageOptions());
        holder.actorName.setText(popularActorsList.get(position).getName());
       /* holder.actorInfo.setText(popularActorsList.get(0).getKnownFor().get(position).getOverview());
        holder.tvBirthDate.setText(popularActorsList.get(0).getKnownFor().get(position).getReleaseDate());*/
        holder.tvActorRating.setText(String.format("%s", popularActorsList.get(0).getPopularity()));
        holder.actorInfo.setVisibility(View.GONE);
        holder.tvBirthDate.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if (popularActorsList.size() > 0)
            return popularActorsList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_image)
        RoundedImageView actorImage;
        @BindView(R.id.tv_name)
        TextView actorName;
        @BindView(R.id.tvBirthDate)
        TextView tvBirthDate;
        @BindView(R.id.tvInfo)
        TextView actorInfo;
        @BindView(R.id.ratingBar)
        ScaleRatingBar actorRating;
        @BindView(R.id.tv_Rating)
        TextView tvActorRating;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(popularActorsList.get(getAdapterPosition()).getId(),
                        popularActorsList.get(getAdapterPosition()).getName(),
                        baseImageUrl + popularActorsList.get(getAdapterPosition()).getProfilePath(),
                        popularActorsList.get(getAdapterPosition()).getPopularity()
                );
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int popularActorId, String name, String image, float popularity);
    }

    public void setOnItemClickListener(final OnItemClickListener mListener) {
        this.mListener = mListener;
    }

}
