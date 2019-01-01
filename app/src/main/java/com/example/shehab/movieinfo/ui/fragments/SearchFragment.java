package com.example.shehab.movieinfo.ui.fragments;


import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.adapters.SearchPopularActorsAdapter;
import com.example.shehab.movieinfo.datastorage.AppSharedPrefs;
import com.example.shehab.movieinfo.datastorage.PrefsConstant;
import com.example.shehab.movieinfo.model.PopularPersonsResponse;
import com.example.shehab.movieinfo.model.Result;
import com.example.shehab.movieinfo.network.ApiManager;
import com.example.shehab.movieinfo.network.ResponseListener;
import com.example.shehab.movieinfo.ui.activity.MainActivity;
import com.example.shehab.movieinfo.ui.activity.SearchActivity;
import com.example.shehab.movieinfo.utils.EndlessRecyclerViewScrollListener;
import com.example.shehab.movieinfo.utils.NetworkingUtils;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;


public class SearchFragment extends Fragment implements ResponseListener, SearchPopularActorsAdapter.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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


    View view;



    private Unbinder unbinder;
    @BindView(R.id.root) LinearLayout linearLayout;
    @BindView(R.id.historyViewContainer) LinearLayout historyViewContainer;
    @BindView(R.id.searchResultRecyclerview) RecyclerView searchResultRecyclerview;
    @BindView(R.id.mSearch) EditText etSearch;
    @BindView(R.id.tvYourSearchSuggestionOrResult) TextView tvSearchSuggestionOrResult;
    @BindView(R.id.ivClearText) ImageView ivClearText;
    @BindView(R.id.noResultFoundContainer) FrameLayout noResultFoundContainer;

    SearchPopularActorsAdapter adapter;
    ArrayList<Result> popularActorsList = new ArrayList<>();

    PopularPersonsResponse popularPersonsResponse;
    private EndlessRecyclerViewScrollListener scrollListener;

    int pageNo = 1;
    int totalPages = 0;
    android.app.AlertDialog dialog;

    OnSearchPopularActorsFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);

        searchResultRecyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        searchResultRecyclerview.setLayoutManager(linearLayoutManager);

        adapter = new SearchPopularActorsAdapter(getActivity(), popularActorsList);
        adapter.setOnItemClickListener(this);
        searchResultRecyclerview.setAdapter(adapter);
        noResultFoundContainer.setVisibility(View.GONE);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                pageNo++;
                if (pageNo <= totalPages)
                    callingSearchAutoCompleteAPI();

            }
        };
        searchResultRecyclerview.addOnScrollListener(scrollListener);

        RxTextView.textChanges(etSearch).debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textChanged -> {
                    Log.d("TAG", "Stopped typing!");
                    if (!etSearch.getText().toString().equals("")) {
                        callingSearchAutoCompleteAPI();
                    }
                });

        ivClearText.setOnClickListener(view -> {
            etSearch.setText("");
            noResultFoundContainer.setVisibility(View.GONE);
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchPopularActorsFragmentInteractionListener) {
            mListener = (OnSearchPopularActorsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPopularActorsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void callingSearchAutoCompleteAPI() {

         dialog = new SpotsDialog.Builder()
                .setContext(getActivity())
                .setTheme(R.style.CustomSpotDialog)
                .setCancelable(false)
                .build();

        if (NetworkingUtils.isNetworkConnected()) {
//            dialog.show();
            ApiManager.getInstance(getActivity()).searchPersons(AppSharedPrefs.getStringVal(PrefsConstant.API_KEY), pageNo,etSearch.getText().toString(), this);

        }else {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() !=null && getActivity() instanceof SearchActivity){
            ((SearchActivity) getActivity()).setActionBarTitle(getString(R.string.search));
        }
    }
    @Override
    public void onItemClick(int popularActorId, String name, String image, float popularity) {
        if (getActivity() != null)
            mListener.onSearchPopularActorsFragmentInteraction(popularActorId,name,image,popularity);
    }

    @Override
    public void onSuccess(Response response) {
//        dialog.dismiss();
        popularActorsList.clear();
        popularPersonsResponse = (PopularPersonsResponse) response.body();
        if (popularPersonsResponse != null) {
            popularActorsList.addAll(popularPersonsResponse.getResults());
            totalPages = popularPersonsResponse.getTotalPages();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure() {
//        dialog.dismiss();
        if (getActivity() != null)
            Toast.makeText(getActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }


    public interface OnSearchPopularActorsFragmentInteractionListener {
        void onSearchPopularActorsFragmentInteraction(int id,String name,String image,float popularity);
    }
}
