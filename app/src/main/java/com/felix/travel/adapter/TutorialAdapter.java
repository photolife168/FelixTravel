package com.felix.travel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.felix.travel.MainActivity;

import java.util.List;

/**
 * Created by felixlin on 2017/1/8.
 */
public class TutorialAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageView> mImageViewList = null;
    private Button mStartButton;

    public TutorialAdapter(Context context, List<ImageView> imageViewList, Button startButton){
        this.mContext = context;
        this.mImageViewList = imageViewList;
        this.mStartButton = startButton;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViewList.get(position % mImageViewList.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mImageViewList.get(position % mImageViewList.size());

        if(position == 4){
            mStartButton.setVisibility(View.VISIBLE);
            mStartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }else{
            mStartButton.setVisibility(View.GONE);
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
