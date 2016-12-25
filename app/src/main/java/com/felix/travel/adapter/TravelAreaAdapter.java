package com.felix.travel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felix.travel.R;
import com.felix.travel.bean.TravelInfo;
import com.felix.travel.view.TravelCardView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by felixlin on 2016/12/6.
 */
public class TravelAreaAdapter extends RecyclerView.Adapter<TravelAreaAdapter.MyViewHolder>{

    private List<TravelInfo> travelInfoList;
    private Context mContext;

    public TravelAreaAdapter (Context context, List<TravelInfo> travelInfoList ){
        this.mContext = context;
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
        holder.mTravelTitle.setText("景點: " + travelInfo.getStitle());
        holder.mTravelStation.setText("車站: " + travelInfo.getMrt());
        if(travelInfo.getFile() != null){
            Log.d("img url=", travelInfo.getFile());
        }
        Context imgContext = holder.mTravelCover.getContext();
        Picasso.with(imgContext)
                .load(travelInfo.getFile())
                .fit()
                .into(holder.mTravelCover);
    }

    @Override
    public int getItemCount() {
        return travelInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TravelCardView mTravelCardView;
        private TextView mTravelTitle, mTravelStation;
        private ImageView mTravelCover;

        public MyViewHolder(View view) {
            super(view);
            //mTravelCardView = (TravelCardView) view.findViewById(R.id.travel_card_view);
            mTravelTitle = (TextView) view.findViewById(R.id.tv_app_travel_area_name);
            mTravelStation = (TextView) view.findViewById(R.id.tv_app_travel_station_name);
            mTravelCover = (ImageView) view.findViewById(R.id.iv_travel_cover);
        }
    }

    public void setItems(List<TravelInfo> travelInfos){
        this.travelInfoList = travelInfos;
    }

}


