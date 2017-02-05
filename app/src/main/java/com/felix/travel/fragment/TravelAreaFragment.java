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

import java.util.ArrayList;
import java.util.List;

import greendao.bean.Travel;


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
        mTravelService.loadTravelInfoFromDB(this);
    }

    @Override
    public void onSuccess(List<JsonTravel> jsonTravelList) {
        if(jsonTravelList != null){
           List<Travel> travelList = transData(jsonTravelList);
            setRecyclerViewData(travelList);
        }
    }

    @Override
    public void onFailed(List<JsonTravel> jsonTravelList) {

    }

    @Override
    public void onLoadDBCompleted(List<Travel> dbTravelList) {
        if(dbTravelList.isEmpty()){
           mTravelService.getTravelInfoFromAPI(this);
        }else{
            setRecyclerViewData(dbTravelList);
        }
    }

    private List<Travel> transData(List<JsonTravel> jsonTravelList){
        List<Travel> travelList = new ArrayList<>();
        for(JsonTravel jsonTravel : jsonTravelList){
            Travel travel = new Travel();
            travel.setArea_name(jsonTravel.getStitle());
            travel.setArea_station(jsonTravel.getMrt());
            travel.setArea_pic(jsonTravel.getFile());
            travel.setArea_desc(jsonTravel.getxBody());
            travelList.add(travel);
        }
        return travelList;
    }

    private void setRecyclerViewData(List<Travel> travelList){
        mTravelAreaAdapter = new TravelAreaAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewTravelInfo.setLayoutManager(mLayoutManager);
        mRecyclerViewTravelInfo.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewTravelInfo.setAdapter(mTravelAreaAdapter);

        mTravelAreaAdapter.setItems(travelList);
        mTravelAreaAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }


}
