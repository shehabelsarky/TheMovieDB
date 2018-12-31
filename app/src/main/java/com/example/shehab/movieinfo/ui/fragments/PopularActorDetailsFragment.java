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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehab.movieinfo.R;
import com.example.shehab.movieinfo.adapters.PopularActorsAdapter;
import com.example.shehab.movieinfo.adapters.PopularActorsImagesAdapter;
import com.example.shehab.movieinfo.datastorage.AppSharedPrefs;
import com.example.shehab.movieinfo.datastorage.PrefsConstant;
import com.example.shehab.movieinfo.model.PopularActorDetails;
import com.example.shehab.movieinfo.model.PopularPersonsResponse;
import com.example.shehab.movieinfo.model.Profile;
import com.example.shehab.movieinfo.network.ApiManager;
import com.example.shehab.movieinfo.network.ResponseListener;
import com.example.shehab.movieinfo.utils.Constants;
import com.example.shehab.movieinfo.utils.EndlessRecyclerViewScrollListener;
import com.example.shehab.movieinfo.utils.NetworkingUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import retrofit2.Response;


public class PopularActorDetailsFragment extends Fragment implements ResponseListener {

    private String id;
    private String name;
    private String image;
    private float popularity;

    private OnPopularActorDetailsFragmentInteractionListener mListener;

    public PopularActorDetailsFragment() {
        // Required empty public constructor
    }


    public static PopularActorDetailsFragment newInstance(int popularActorId,String name,String image,float popularity) {
        PopularActorDetailsFragment fragment = new PopularActorDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.POPULAR_ACTOR_ID, String.valueOf(popularActorId));
        args.putString(Constants.POPULAR_ACTOR_NAME, name);
        args.putString(Constants.POPULAR_ACTOR_IMAGE, image);
        args.putString(Constants.POPULAR_ACTOR_POPULARITY, String.valueOf(popularity));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(Constants.POPULAR_ACTOR_ID);
            name = getArguments().getString(Constants.POPULAR_ACTOR_NAME);
            image = getArguments().getString(Constants.POPULAR_ACTOR_IMAGE);
            popularity = Float.parseFloat(getArguments().getString(Constants.POPULAR_ACTOR_POPULARITY));
        }
    }

    private Unbinder unbinder;
    @BindView(R.id.main_recyclerview) RecyclerView rvPopularActors;
    @BindView(R.id.iv_image) RoundedImageView ivPopularActorImage;
    @BindView(R.id.tv_name) TextView tvActorName;
    @BindView(R.id.tvInfo) TextView tvInfo;
    @BindView(R.id.tv_Rating) TextView tvActorPopularity;
    @BindView(R.id.tvBirthDate) TextView tvBirthDate;

    /*@BindView(R.id.progressBar)
    ProgressBar progressBar;
*/
    PopularActorsImagesAdapter adapter;
    android.app.AlertDialog dialog;
    ArrayList<Profile> popularActorsImagesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_actor_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        dialog = new SpotsDialog.Builder().setContext(getActivity()).setTheme(R.style.CustomSpotDialog).setCancelable(false).build();

        if (image!=null && !image.isEmpty() && getActivity() !=null){
            Picasso.with(getActivity()).load(image).fit().placeholder(R.drawable.dark_img).into(ivPopularActorImage);
        }else {
            Picasso.with(getActivity()).load(R.drawable.dark_img).fit().placeholder(R.drawable.dark_img).into(ivPopularActorImage);
        }
        tvActorName.setText(name);
        tvActorPopularity.setText(String.format("%s", popularity));

        initViews();
        return view;
    }


    private void initViews() {

        rvPopularActors.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvPopularActors.setLayoutManager(gridLayoutManager);
        adapter = new PopularActorsImagesAdapter(getActivity(), popularActorsImagesList);
        rvPopularActors.setAdapter(adapter);

        callPopularPersonsImagesApi();
        callPopularActorDetailsApi();
    }

    private void callPopularActorDetailsApi(){
        if (NetworkingUtils.isNetworkConnected()) {
            dialog.show();
            ApiManager.getInstance(getActivity()).popularPersonDetails(id,AppSharedPrefs.getStringVal(PrefsConstant.API_KEY), this);
        } else {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void callPopularPersonsImagesApi(){

        if (NetworkingUtils.isNetworkConnected()) {
                dialog.show();
            ApiManager.getInstance(getActivity()).popularPersonImages(id,AppSharedPrefs.getStringVal(PrefsConstant.API_KEY), this);
        } else {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPopularActorDetailsFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPopularActorDetailsFragmentInteractionListener) {
            mListener = (OnPopularActorDetailsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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

    @Override
    public void onSuccess(Response response) {
        dialog.dismiss();
        if (response.body() instanceof PopularActorDetails){
            PopularActorDetails popularActorDetails = (PopularActorDetails) response.body();
            if (popularActorDetails != null) {
                tvInfo.setText(popularActorDetails.getBiography());
                tvBirthDate.setText(popularActorDetails.getBirthday());
            }

        }
        if (response.body() instanceof PopularPersonsResponse) {
//            progressBar.setVisibility(View.INVISIBLE);
            PopularPersonsResponse popularPersonsResponse = (PopularPersonsResponse) response.body();
            if (popularPersonsResponse != null) {
                popularActorsImagesList.addAll(popularPersonsResponse.getProfiles());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailure() {
        dialog.dismiss();
//        progressBar.setVisibility(View.INVISIBLE);
        if (getActivity() != null)
            Toast.makeText(getActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }


    public interface OnPopularActorDetailsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPopularActorDetailsFragmentInteraction();
    }
}
