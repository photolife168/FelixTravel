package com.felix.travel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felix.travel.R;
import com.felix.travel.item.TravelInfo;

import java.util.List;

/**
 * Created by felixlin on 2016/12/6.
 */
public class TravelAreaAdapter extends RecyclerView.Adapter<TravelAreaAdapter.MyViewHolder>{

    private List<TravelInfo> travelInfoList;

    public TravelAreaAdapter (List<TravelInfo> travelInfoList ){
        this.travelInfoList = travelInfoList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_area_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       TravelInfo travelInfo = travelInfoList.get(position);
        holder.areaInfo.setText(travelInfo.getAreaInfo());
        holder.areaName.setText(travelInfo.getAreaName());
    }

    @Override
    public int getItemCount() {
        return travelInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView areaName, areaInfo;

        public MyViewHolder(View view) {
            super(view);
            areaName = (TextView) view.findViewById(R.id.tv_app_travel_area_name);
            areaInfo = (TextView) view.findViewById(R.id.tv_app_travel_area_info);
        }
    }

}


