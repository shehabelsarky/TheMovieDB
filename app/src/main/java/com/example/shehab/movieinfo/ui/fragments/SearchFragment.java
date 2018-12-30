/*
package com.example.shehab.movieinfo.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.saudistore.R;
import com.app.saudistore.adapters.RecommendedRecyclerviewAdapter;
import com.app.saudistore.adapters.SearchHistoryAdapter;
import com.app.saudistore.datastorage.AppSharedPrefs;
import com.app.saudistore.datastorage.PrefsConstant;
import com.app.saudistore.models.SearchHistoryModel;
import com.app.saudistore.models.search_auto_complete.Content;
import com.app.saudistore.models.search_auto_complete.SearchAutoCompleteModel;
import com.app.saudistore.models.search_auto_complete.SearchAutoCompleteResponse;
import com.app.saudistore.network.ApiManager;
import com.app.saudistore.network.ResponseListener;
import com.app.saudistore.utils.ItemClickListener;
import com.app.saudistore.utils.NetworkingUtils;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;

*/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link searchFragment#newInstance} factory method to
 * create an instance of this fragment.
 *//*

public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    List<String> arr;
    ImageView btnClear;
    RecyclerView serachRV, searchResultRecyclerview;
    EditText etSearch;
    TextView tvSearchSuggestionOrResult;
    SearchHistoryAdapter adapter;
    ImageView ivClearText;
    FrameLayout noResultFoundContainer;

    private static int SPLASH_TIME_OUT = 1000;


    LinearLayout historyViewContainer;
    RecommendedRecyclerviewAdapter searchResultAdapter;
    ArrayList<com.app.saudistore.models.recommended_list.Content> searchResltList = new ArrayList<>();
    String[] names = {"Games", "CAR", "CANDY GAME", "Cairo"};
    String[] type = {"Game", "Game", "Game", "Game"};
    String[] desc = {"spicy vegetarian delight is topped with extremely appealing golden corns"
            , "crunchy onions, crispy capsicum, juicy tomato and jalapeno"
            , "delicious piece of cheese cube made of pure milk.",
            " delicious and are made with single topping of Cheese. "};
    String[] recommended_img_url = {
            "https://images.pexels.com/photos/333850/pexels-photo-333850.jpeg?auto=compress&cs=tinysrgb&h=350"
            , "https://images.pexels.com/photos/333850/pexels-photo-333850.jpeg?auto=compress&cs=tinysrgb&h=350"
            , "https://images.pexels.com/photos/333850/pexels-photo-333850.jpeg?auto=compress&cs=tinysrgb&h=350"
            , "https://images.pexels.com/photos/333850/pexels-photo-333850.jpeg?auto=compress&cs=tinysrgb&h=350"};


    public SearchFragment() {
        // Required empty public constructor
    }





    */
/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchFragment.
     *//*

    // TODO: Rename and change types and number of parameters
    public static searchFragment newInstance(String param1, String param2) {
        searchFragment fragment = new searchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    LinearLayout linearLayout;
    SearchAutoCompleteResponse searchAutoCompleteResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        linearLayout = view.findViewById(R.id.root);

        arr = new ArrayList<String>();

        etSearch = view.findViewById(R.id.mSearch);
        tvSearchSuggestionOrResult = view.findViewById(R.id.tvYourSearchSuggestionOrResult);


        searchResultRecyclerview = view.findViewById(R.id.searchResultRecyclerview);
        searchResultRecyclerview.setNestedScrollingEnabled(false);
        searchResultRecyclerview.setHasFixedSize(true);
        searchResultRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        historyViewContainer = view.findViewById(R.id.historyViewContainer);

        ivClearText = view.findViewById(R.id.ivClearText);
        noResultFoundContainer = view.findViewById(R.id.noResultFoundContainer);
        serachRV = view.findViewById(R.id.myRecycler);
        serachRV.setVisibility(View.INVISIBLE);
        serachRV.setLayoutManager(new LinearLayoutManager(getActivity()));



       */
/* for (int i = 0; i < getApp().size(); i++) {
            arr.add(getApp().get(i).getText());
        }*//*


        noResultFoundContainer.setVisibility(View.GONE);



        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // noResultFoundContainer.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {


                //tvSearchSuggestionOrResult.setText(R.string.search_suggestions);



                if (etSearch != null && !etSearch.getText().toString().isEmpty() && etSearch.getText().toString().length()%2 ==0) {

                    callingSearchAutoCompleteAPI();
                    serachRV.setVisibility(View.VISIBLE);
                    searchResultRecyclerview.setVisibility(View.GONE);
                    noResultFoundContainer.setVisibility(View.GONE);
                   */
/* adapter.getFilter().filter(charSequence);
                    searchResultAdapter.getFilter().filter(charSequence);*//*

                    tvSearchSuggestionOrResult.setText(String.format("Search suggestions of \"%s\"", etSearch.getText().toString()));
                }
//                if (adapter!=null && adapter.getItemCount() < 1) {
                if (searchAutoCompleteResponse!=null && searchAutoCompleteResponse.getContent().size() < 1) {
                    serachRV.setVisibility(View.GONE);
                    noResultFoundContainer.setVisibility(View.VISIBLE);
                    tvSearchSuggestionOrResult.setText(String.format("Search result of \"%s\"", etSearch.getText().toString()));

                }

            }





            @Override
            public void afterTextChanged(Editable editable) {


            }
        });



     RxTextView.textChanges(etSearch).debounce(3, TimeUnit.SECONDS)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(textChanged -> {
         Log.d("TAG", "Stopped typing!");
         if (!etSearch.getText().toString().equals("")) {
             if (adapter!=null && adapter.getItemCount()<1){
                 serachRV.setVisibility(View.GONE);
                 noResultFoundContainer.setVisibility(View.VISIBLE);
                 searchResultRecyclerview.setVisibility(View.GONE);
                 tvSearchSuggestionOrResult.setText(String.format("Search result of \"%s\"", etSearch.getText().toString()));
             }else {
                 serachRV.setVisibility(View.GONE);
                 searchResultRecyclerview.setVisibility(View.VISIBLE);
                 noResultFoundContainer.setVisibility(View.GONE);
                 //tvSearchSuggestionOrResult.setText(R.string.search_result);
                 tvSearchSuggestionOrResult.setText(String.format("Search result of \"%s\"", etSearch.getText().toString()));
             }
         }
     });

        ivClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
                noResultFoundContainer.setVisibility(View.GONE);
            }
        });
        return view;
    }


    private void callingSearchAutoCompleteAPI(){

        android.app.AlertDialog dialog=new SpotsDialog.Builder()
                .setContext(getActivity())
                .setTheme(R.style.CustomSpotDialog)
                .setCancelable(false)
                .build();

        if (NetworkingUtils.isNetworkConnected()) {
            dialog.show();

            SearchAutoCompleteModel searchAutoCompleteModel = new SearchAutoCompleteModel();
            searchAutoCompleteModel.setPage(1);
            searchAutoCompleteModel.setName(etSearch.getText().toString());
            String lang_id="";
            if (AppSharedPrefs.getStringVal(PrefsConstant.LANG).equals("ar"))
                lang_id="1";
            else
                lang_id="2";
            ApiManager apiManager = ApiManager.getInstance(getActivity());
            apiManager.searchAutoComplete(searchAutoCompleteModel,lang_id,new ResponseListener() {
                @Override
                public void onSuccess(Response response) {
                    dialog.dismiss();
                    searchAutoCompleteResponse = (SearchAutoCompleteResponse) response.body();

                    if (searchAutoCompleteResponse != null) {


                        adapter = new SearchHistoryAdapter(getActivity(), (ArrayList<com.app.saudistore.models.recommended_list.Content>) searchAutoCompleteResponse.getContent(), new ItemClickListener() {
                            @Override
                            public void onItemClick(int pos, String text) {
               */
/* SearchHistoryAdapter.SearchHistoryViewHolder viewHolder = (SearchHistoryAdapter.SearchHistoryViewHolder) serachRV.findViewHolderForAdapterPosition(pos);
                adapter.notifyDataSetChanged();*//*


                                etSearch.setText(text);
                                serachRV.setVisibility(View.GONE);
                                searchResultRecyclerview.setVisibility(View.VISIBLE);
                                // tvSearchSuggestionOrResult.setText(R.string.search_result);
                                tvSearchSuggestionOrResult.setText(String.format("Search result of \"%s\"", etSearch.getText().toString()));
                            }
                        });
                        serachRV.setAdapter(adapter);

                       // searchResultAdapter = new RecommendedRecyclerviewAdapter(searchResltList, getActivity(), true);
                        searchResultAdapter = new RecommendedRecyclerviewAdapter((ArrayList<com.app.saudistore.models.recommended_list.Content>) searchAutoCompleteResponse.getContent(), getActivity(), true);


                        searchResultRecyclerview.setAdapter(searchResultAdapter);
                    }

                }

                @Override
                public void onFailure(Response response) {
                    dialog.dismiss();
                    SearchAutoCompleteResponse searchAutoCompleteResponse = (SearchAutoCompleteResponse) response.body();
                    if (searchAutoCompleteResponse != null) {
                       */
/* Toast.makeText(MainScreenActivity.this, ""+recommendedListResponse
                                .getStatus()
                                .getValidation()
                                .get(0)
                                .getMessage(), Toast.LENGTH_SHORT).show();*//*


                        Toasty.error(getActivity(), ""+""+searchAutoCompleteResponse.getStatus().getMessage(), Toast.LENGTH_SHORT, true).show();

                    }
                }

                @Override
                public void onFailure() {
//                    dialog.show();
                    dialog.dismiss();
                    //  Toast.makeText(MainScreenActivity.this, R.string.unable_to_fetch_data, Toast.LENGTH_SHORT).show();
                    Toasty.error(getActivity(), ""+getResources().getString(R.string.unable_to_fetch_data), Toast.LENGTH_SHORT, true).show();

                }
            });
        }else {
            Snackbar.make(linearLayout, getString(R.string.no_internet), Snackbar.LENGTH_SHORT).show();
        }


    }

    private ArrayList<SearchHistoryModel> getApp() {

        ArrayList<SearchHistoryModel> apps = new ArrayList<>();
        SearchHistoryModel name = new SearchHistoryModel();
        name.setText("GAME");
        apps.add(name);

        name = new SearchHistoryModel();
        name.setText("CAR");
        apps.add(name);

        name = new SearchHistoryModel();
        name.setText("CANDY GAME");
        apps.add(name);

        name = new SearchHistoryModel();
        name.setText("Translate");
        apps.add(name);

        name = new SearchHistoryModel();
        name.setText("Cairo");
        apps.add(name);


        return apps;
    }

}
*/
