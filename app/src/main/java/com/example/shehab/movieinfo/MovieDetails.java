package com.example.shehab.movieinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    TextView tvTitle,tvOverview,tvVoteAverage,tvReleaseDate;
    ImageView ivMoviePoster;
    String posterUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setTitle("Movie Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.tv_original_title);
        ivMoviePoster = findViewById(R.id.iv_movie_poster);
        tvOverview = findViewById(R.id.tv_overview);
        tvVoteAverage = findViewById(R.id.tv_user_rating);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        posterUrl = getIntent().getStringExtra("poster_path");

        tvTitle.setText(getIntent().getStringExtra("title"));

        Picasso.with(this).load(posterUrl).placeholder(R.drawable.default_avatar_big).into(ivMoviePoster);
        tvOverview.setText(getIntent().getStringExtra("overview"));
        tvVoteAverage.setText("Rate: " +getIntent().getStringExtra("vote_average"));
        tvReleaseDate.setText("Release Date: "+getIntent().getStringExtra("release_date"));
    }
}
