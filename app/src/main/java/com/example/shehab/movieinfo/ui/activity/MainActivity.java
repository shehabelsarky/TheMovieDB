package com.example.shehab.movieinfo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.adapters.PopularActorsAdapter;
import com.example.shehab.movieinfo.datastorage.AppSharedPrefs;
import com.example.shehab.movieinfo.datastorage.PrefsConstant;
import com.example.shehab.movieinfo.model.PopularPersonsResponse;
import com.example.shehab.movieinfo.model.Result;
import com.example.shehab.movieinfo.network.ApiManager;
import com.example.shehab.movieinfo.network.ResponseListener;
import com.example.shehab.movieinfo.utils.EndlessRecyclerViewScrollListener;
import com.example.shehab.movieinfo.utils.NetworkingUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ResponseListener, PopularActorsAdapter.OnItemClickListener {

//    public static String url = "https://api.themoviedb.org/3/person/popular?api_key=aede46592fa18e618a51016ae6036c11";

    @BindView(R.id.main_recyclerview)
    RecyclerView rvPopularActors;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    PopularActorsAdapter adapter;
    android.app.AlertDialog dialog;
    int pageNo = 1;
    ArrayList<Result> popularActorsList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;
    int totalPages = 0;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppSharedPrefs.saveStringPrefs(PrefsConstant.API_KEY, "aede46592fa18e618a51016ae6036c11");
        initViews();
    }

    private void initViews() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Actors");

        rvPopularActors.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvPopularActors.setLayoutManager(gridLayoutManager);
        adapter = new PopularActorsAdapter(this, popularActorsList);
        adapter.setOnItemClickListener(this);
        rvPopularActors.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                flag = true;
                progressBar.setVisibility(View.VISIBLE);
                pageNo++;
                if (pageNo <= totalPages)
                    callPopularPersonsApi();

            }
        };
        rvPopularActors.addOnScrollListener(scrollListener);

        callPopularPersonsApi();
    }


    private void callPopularPersonsApi() {
        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.CustomSpotDialog)
                .setCancelable(false)
                .build();

        if (NetworkingUtils.isNetworkConnected()) {
            if (!flag)
            dialog.show();
            ApiManager.getInstance(this).popularPersons(AppSharedPrefs.getStringVal(PrefsConstant.API_KEY), pageNo, this);
        } else {
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Response response) {
        dialog.dismiss();
        progressBar.setVisibility(View.INVISIBLE);
        PopularPersonsResponse popularPersonsResponse = (PopularPersonsResponse) response.body();
        if (popularPersonsResponse != null) {
            popularActorsList.addAll(popularPersonsResponse.getResults());
            totalPages = popularPersonsResponse.getTotalPages();
            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onFailure() {
        dialog.dismiss();
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int adapterPosition) {
        Toast.makeText(this, "Item is clicked", Toast.LENGTH_SHORT).show();
    }

}
