package com.example.shehab.movieinfo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.adapters.PopularActorsAdapter;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import retrofit2.Response;


public class PopularActorsFragment extends Fragment implements ResponseListener, PopularActorsAdapter.OnItemClickListener {


    private OnPopularActorsFragmentInteractionListener mListener;

    public PopularActorsFragment() {
        // Required empty public constructor
    }


    public static PopularActorsFragment newInstance() {
        return new PopularActorsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


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

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_actors, container, false);
        unbinder = ButterKnife.bind(this, view);

        initViews();
        return view;
    }

    private void initViews() {


        rvPopularActors.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvPopularActors.setLayoutManager(gridLayoutManager);
        adapter = new PopularActorsAdapter(getActivity(), popularActorsList);
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
                .setContext(getActivity())
                .setTheme(R.style.CustomSpotDialog)
                .setCancelable(false)
                .build();

        if (NetworkingUtils.isNetworkConnected()) {
            if (!flag)
                dialog.show();
            ApiManager.getInstance(getActivity()).popularPersons(AppSharedPrefs.getStringVal(PrefsConstant.API_KEY), pageNo, this);
        } else {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPopularActorsFragmentInteractionListener) {
            mListener = (OnPopularActorsFragmentInteractionListener) context;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnPopularActorsFragmentInteractionListener {
        void onPopularActorsFragmentInteraction(int id,String name,String image,float popularity);
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
    public void onResume() {
        super.onResume();
        if (getActivity() !=null && getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.actors));
        }
    }
    @Override
    public void onFailure() {
        dialog.dismiss();
        progressBar.setVisibility(View.INVISIBLE);
        if (getActivity() != null)
            Toast.makeText(getActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int popularActorId,String name,String image,float popularity) {
        if (getActivity() != null)
            mListener.onPopularActorsFragmentInteraction(popularActorId,name,image,popularity);
    }

}
