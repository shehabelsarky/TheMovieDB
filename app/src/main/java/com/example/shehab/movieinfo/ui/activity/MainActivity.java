package com.example.shehab.movieinfo.ui.activity;

import android.support.constraint.ConstraintLayout;
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
import com.example.shehab.movieinfo.ui.fragments.PopularActorDetailsFragment;
import com.example.shehab.movieinfo.ui.fragments.PopularActorsFragment;
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

public class MainActivity extends AppCompatActivity implements PopularActorsFragment.OnPopularActorsFragmentInteractionListener
,PopularActorDetailsFragment.OnPopularActorDetailsFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppSharedPrefs.saveStringPrefs(PrefsConstant.API_KEY, "aede46592fa18e618a51016ae6036c11");

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Actors");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PopularActorsFragment.newInstance()).commit();
    }


    @Override
    public void onPopularActorsFragmentInteraction(int id,String name,String image,float popularity) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,
                PopularActorDetailsFragment.newInstance(id,name,image,popularity)).commit();
    }

    @Override
    public void onPopularActorDetailsFragmentInteraction() {

    }
}
