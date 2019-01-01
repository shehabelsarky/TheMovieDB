package com.example.shehab.movieinfo.ui.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.adapters.PopularActorsAdapter;
import com.example.shehab.movieinfo.datastorage.AppSharedPrefs;
import com.example.shehab.movieinfo.datastorage.PrefsConstant;

import com.example.shehab.movieinfo.ui.fragments.PopularActorDetailsFragment;
import com.example.shehab.movieinfo.ui.fragments.PopularActorsFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements PopularActorsFragment.OnPopularActorsFragmentInteractionListener
        , PopularActorDetailsFragment.OnPopularActorDetailsFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppSharedPrefs.saveStringPrefs(PrefsConstant.API_KEY, "aede46592fa18e618a51016ae6036c11");

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.actors));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PopularActorsFragment.newInstance()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.ic_search) {
            Intent searchActivityIntent = new Intent(this, SearchActivity.class);
            startActivity(searchActivityIntent);
        }

        return true;
    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void onPopularActorsFragmentInteraction(int id, String name, String image, float popularity) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container,
                PopularActorDetailsFragment.newInstance(id, name, image, popularity)).commit();
    }

    @Override
    public void onPopularActorDetailsFragmentInteraction() {

    }
}
