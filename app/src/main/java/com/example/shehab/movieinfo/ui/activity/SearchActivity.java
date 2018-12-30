/*
package com.example.shehab.movieinfo.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.shehab.movieinfo.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

//import com.example.shehab.saudistore.activities.utils.TinyDB;

public class SearchActivity extends AppCompatActivity {


//    TinyDB tinydb;

    SearchView searchView;
    TextView tvYourSearch;
    Type listOfObjects;

   */
/* Gson gson;


    SharedPreferences  myPrefs;
    ArrayList<SearchHistoryModel> mySearch;
    ArrayList<String> searchSuggest=new ArrayList<>();*//*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StatusBarUtil.setTransparent(this);
        final TextView tvYourSearchSuggestionOrResult = findViewById(R.id.tvYourSearchSuggestionOrResult);



        Toolbar toolbar =  findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
       // myPrefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        tvYourSearch = findViewById(R.id.tvYourSearch);

        replaceFragment(new searchFragment(),true,true);

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
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            doMySearch(query);
        }
    }

    private void doMySearch(String searchQuery) {
        tvYourSearch.setText(searchQuery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_close){
            finish();
        }

        return true;
    }

    private ArrayList<RecommendedRecyclerviewModel> getResults() {

        ArrayList<RecommendedRecyclerviewModel> results=new ArrayList<>();
        RecommendedRecyclerviewModel model=new RecommendedRecyclerviewModel("Candy Game","Game","???","http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        model.setName("Candy Game");
        model.setType("Game");
        model.setDesc("???");
        model.setImage("http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        results.add(model);

        model=new RecommendedRecyclerviewModel("Candy Game","Game","???","http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        model.setName("Monako");
        model.setType("Game");
        model.setDesc("???");
        model.setImage("http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        results.add(model);

        model=new RecommendedRecyclerviewModel("Candy Game","Game","???","http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        model.setName("Hem");
        model.setType("Game");
        model.setDesc("???");
        model.setImage("http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        results.add(model);

        model=new RecommendedRecyclerviewModel("Candy Game","Game","???","http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        model.setName("Car");
        model.setType("Game");
        model.setDesc("???");
        model.setImage("http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        results.add(model);

        model=new RecommendedRecyclerviewModel("Candy Game","Game","???","http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        model.setName("Bus");
        model.setType("Game");
        model.setDesc("???");
        model.setImage("http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        results.add(model);

        model=new RecommendedRecyclerviewModel("Candy Game","Game","???","http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        model.setName("Dog");
        model.setType("Game");
        model.setDesc("???");
        model.setImage("http://www.culpwrit.com/wp-content/uploads/2012/06/personal-branding.jpg");
        results.add(model);


        return results;
    }

    
}
*/
