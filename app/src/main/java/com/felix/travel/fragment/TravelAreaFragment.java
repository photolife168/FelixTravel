package com.felix.travel.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felix.travel.R;
import com.felix.travel.adapter.TravelAreaAdapter;
import com.felix.travel.bean.JsonTravel;
import com.felix.travel.callback.TraveAreaApiCallback;
import com.felix.travel.service.TravelService;

import java.util.List;


/**
 * Created by felixlin on 2016/11/20.
 */
public class TravelAreaFragment extends Fragment implements TraveAreaApiCallback{

    private Context mContext;
    private TextView mTvAreaInfo;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerViewTravelInfo;
    private TravelAreaAdapter mTravelAreaAdapter;
    private TravelService mTravelService;


    public TravelAreaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_travel_area, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        initView(view);
        initService();
        loadTravelData();
    }

    private void initView(View view){
        mRecyclerViewTravelInfo = (RecyclerView) view.findViewById(R.id.recyclerview_app_travel_area);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar_travel_area);
    }

    private void initService(){
        mTravelService = new TravelService(mContext);
    }

    private void loadTravelData(){
        mTravelService.loadTravelInfo(this);
    }

    @Override
    public void onSuccess(List<JsonTravel> jsonTravelList) {

        mTravelAreaAdapter = new TravelAreaAdapter(getActivity(), jsonTravelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewTravelInfo.setLayoutManager(mLayoutManager);
        mRecyclerViewTravelInfo.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewTravelInfo.setAdapter(mTravelAreaAdapter);

        mTravelAreaAdapter.setItems(jsonTravelList);
        mTravelAreaAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onfailed(List<JsonTravel> jsonTravelList) {

    }

}
