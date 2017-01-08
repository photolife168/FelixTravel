package com.felix.travel.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by felixlin on 2017/1/8.
 */
public class TutorialAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageView> mImageViewList = null;

    public TutorialAdapter(Context context, List<ImageView> imageViewList){
        this.mContext = context;
        this.mImageViewList = imageViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViewList.get(position % mImageViewList.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mImageViewList.get(position % mImageViewList.size());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Page被點擊了", Toast.LENGTH_SHORT).show();
            }

        });

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
