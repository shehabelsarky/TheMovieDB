package com.example.shehab.movieinfo.ui.activity;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;



import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.ui.fragments.PopularActorDetailsFragment;
import com.example.shehab.movieinfo.ui.fragments.SearchFragment;




public class SearchActivity extends AppCompatActivity implements SearchFragment.OnSearchPopularActorsFragmentInteractionListener
,PopularActorDetailsFragment.OnPopularActorDetailsFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.search));
        replaceFragment(new SearchFragment(),true,true);

    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    public void replaceFragment(Fragment fragment, boolean isFirst, boolean isClearBS) {

        if (isClearBS) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++)
                    getSupportFragmentManager().popBackStack();
            }
        }
        if (isFirst)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commitAllowingStateLoss();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commitAllowingStateLoss();

    }


    @Override
    public void onSearchPopularActorsFragmentInteraction(int id, String name, String image, float popularity) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,
                PopularActorDetailsFragment.newInstance(id,name,image,popularity)).commit();
    }

    @Override
    public void onPopularActorDetailsFragmentInteraction() {

    }
}
