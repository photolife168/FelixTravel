package com.felix.travel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felix.travel.R;

/**
 * Created by felixlin on 2016/12/11.
 */
public class TravelCardView extends RelativeLayout {

    private TextView mTvAreaName, mTvAreaInfo;
    private Context mContext;

    public TravelCardView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public TravelCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public TravelCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }


    public void init(){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.travel_card_view, null);
        mTvAreaName = (TextView) view.findViewById(R.id.tv_app_travel_area_name);
        mTvAreaInfo = (TextView) view.findViewById(R.id.tv_app_travel_area_info);
    }

    public void setAreaName(String areaName){
        mTvAreaName.setText(areaName);
    }

    public void setAreaInfo(String areaInfo){
        mTvAreaInfo.setText(areaInfo);
    }
}
