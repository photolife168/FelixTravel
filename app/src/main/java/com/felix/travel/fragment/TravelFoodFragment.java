package com.felix.travel.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.travel.R;


/**
 * Created by felixlin on 2016/11/20.
 */
public class TravelFoodFragment extends Fragment{

        public TravelFoodFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_travel_food, container, false);
        }
}
