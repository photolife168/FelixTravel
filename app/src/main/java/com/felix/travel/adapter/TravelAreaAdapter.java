package com.felix.travel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felix.travel.R;
import com.felix.travel.activity.TravelDetailActivity;
import com.felix.travel.bean.TravelInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by felixlin on 2016/12/6.
 */
public class TravelAreaAdapter extends RecyclerView.Adapter<TravelAreaAdapter.MyViewHolder> {

    private List<TravelInfo> travelInfoList;
    private Context mContext;

    public TravelAreaAdapter(Context context, List<TravelInfo> travelInfoList) {
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
        final TravelInfo travelInfo = travelInfoList.get(position);
        holder.mTravelTitle.setText(mContext.getResources().getString(R.string.travel_area_name) + travelInfo.getStitle());
        holder.mTravelStation.setText(mContext.getResources().getString(R.string.travel_area_station) + travelInfo.getMrt());

        Picasso.with(mContext)
                .load(travelInfo.getFile())
                .fit()
                .into(holder.mTravelCover);

        holder.mTravelAreaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TravelDetailActivity.class);
                intent.putExtra("travelAreaName", travelInfo.getStitle());
                intent.putExtra("travelAreaDetail", travelInfo.getxBody());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTravelTitle, mTravelStation;
        private ImageView mTravelCover;
        private RelativeLayout mTravelAreaLayout;

        public MyViewHolder(View view) {
            super(view);
            mTravelTitle = (TextView) view.findViewById(R.id.tv_app_travel_area_name);
            mTravelStation = (TextView) view.findViewById(R.id.tv_app_travel_station_name);
            mTravelCover = (ImageView) view.findViewById(R.id.iv_travel_cover);
            mTravelAreaLayout = (RelativeLayout) view.findViewById(R.id.layout_app_travel_area);
        }
    }

    public void setItems(List<TravelInfo> travelInfos) {
        this.travelInfoList = travelInfos;
    }

}


