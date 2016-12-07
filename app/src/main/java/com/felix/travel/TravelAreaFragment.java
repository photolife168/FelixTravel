package com.felix.travel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felix.travel.adapter.TravelAreaAdapter;
import com.felix.travel.item.TravelInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by felixlin on 2016/11/20.
 */
public class TravelAreaFragment extends Fragment {

    private TextView mTvAreaInfo;
    private List<TravelInfo> mTravelInfoList = new ArrayList<TravelInfo>();
    private RecyclerView mTravelInfoRecyclerView;
    private TravelAreaAdapter mTravelAreaAdapter;

    public TravelAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_travel_area, container, false);
        mTravelInfoRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_app_travel_area);

        mTravelAreaAdapter = new TravelAreaAdapter(mTravelInfoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mTravelInfoRecyclerView.setLayoutManager(mLayoutManager);
        mTravelInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTravelInfoRecyclerView.setAdapter(mTravelAreaAdapter);

        prepareMovieData();



        return view;
    }

    private void prepareMovieData() {
        TravelInfo travelInfo = new TravelInfo();
        travelInfo.setAreaName("Tokyo");
        travelInfo.setAreaInfo("play for fun");
        mTravelInfoList.add(travelInfo);

        travelInfo = new TravelInfo();
        travelInfo.setAreaName("Osaka");
        travelInfo.setAreaInfo("take the photos");
        mTravelInfoList.add(travelInfo);

        mTravelAreaAdapter.notifyDataSetChanged();
    }
}
