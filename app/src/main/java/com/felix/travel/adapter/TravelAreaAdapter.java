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
import com.felix.travel.bean.JsonTravel;
import com.squareup.picasso.Picasso;

import java.util.List;

import greendao.bean.Travel;

/**
 * Created by felixlin on 2016/12/6.
 */
public class TravelAreaAdapter extends RecyclerView.Adapter<TravelAreaAdapter.MyViewHolder> {

    private List<Travel> travelList;
    private Context mContext;

    public TravelAreaAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_area_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Travel travel = travelList.get(position);
        holder.mTravelTitle.setText(mContext.getResources().getString(R.string.travel_area_name) + travel.getArea_name());
        holder.mTravelStation.setText(mContext.getResources().getString(R.string.travel_area_station) + travel.getArea_station());

        Picasso.with(mContext)
                .load(travel.getArea_pic())
                .fit()
                .into(holder.mTravelCover);

        holder.mTravelAreaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TravelDetailActivity.class);
                intent.putExtra("travelAreaName", travel.getArea_name());
                intent.putExtra("travelAreaDetail", travel.getArea_desc());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelList.size();
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

    public void setItems(List<Travel> travelList) {
        this.travelList = travelList;
    }

}


