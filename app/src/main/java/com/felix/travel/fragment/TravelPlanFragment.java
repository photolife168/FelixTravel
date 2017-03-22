package com.felix.travel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.travel.R;
import com.felix.travel.activity.PlanActivity;


/**
 * Created by felixlin on 2016/11/20.
 */
public class TravelPlanFragment extends Fragment {

    private Context mContext;
    private static TravelPlanFragment mTravelPlanFragment;
    private FloatingActionButton mBtnFloatingAction;

    public static TravelPlanFragment newInstance() {
        if (mTravelPlanFragment == null) {
            mTravelPlanFragment = new TravelPlanFragment();
        }
        return mTravelPlanFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_travel_plan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView(view);
        setListeners();
    }

    private void initView(View view) {
        mBtnFloatingAction = (FloatingActionButton) view.findViewById(R.id.fab_app_travel_plan);
    }

    private void setListeners() {
        mBtnFloatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlanActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


}
