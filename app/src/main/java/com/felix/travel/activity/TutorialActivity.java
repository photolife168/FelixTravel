package com.felix.travel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.felix.travel.R;
import com.felix.travel.adapter.TutorialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felixlin on 2017/1/3.
 */
public class TutorialActivity extends Activity {

    private List<ImageView> mImageViewList = null;
    private int prePosition = 0;
    private String[] bannerTextDescArray = {
            "第一張",
            "第二張",
            "第三張",
            "第四張"
    };
    private boolean isStop = false;
    private long scrollTimeOffset = 5000;
    private ViewPager mViewPager;
    private TextView tvBannerTextDesc;
    private LinearLayout layoutCircleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        initView();
        initViewPager();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_tutorial_page);
        layoutCircleGroup = (LinearLayout) findViewById(R.id.ll_circle_group);
        tvBannerTextDesc = (TextView) findViewById(R.id.tv_banner_text_desc);
    }

    private void initViewPager(){
        mImageViewList = new ArrayList<ImageView>();
        int[] images = new int[] {
                R.drawable.tutorial_view_1,
                R.drawable.tutorial_view_2,
                R.drawable.tutorial_view_3,
                R.drawable.tutorial_view_4,
        };

        ImageView imageView = null;
        View circle = null;
        LinearLayout.LayoutParams params = null;
        for (int id : images) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(id);
            mImageViewList.add(imageView);

            circle = new View(this);
            circle.setBackgroundResource(R.drawable.dot_bg_selector);
            params = new LinearLayout.LayoutParams(15, 15);
            params.leftMargin = 10;
            circle.setEnabled(false);
            circle.setLayoutParams(params);
            layoutCircleGroup.addView(circle);
        }

        mViewPager.setAdapter(new TutorialAdapter(this, mImageViewList));
        mViewPager.addOnPageChangeListener(new BannerPageChangeListener());

        tvBannerTextDesc.setText(bannerTextDescArray[0]);
        layoutCircleGroup.getChildAt(0).setEnabled(true);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        isStop = true;
        super.onDestroy();
    }


    private class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // Nothing to do
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // Nothing to do
        }

        @Override
        public void onPageSelected(int position) {
            int newPositon = position % mImageViewList.size();
            //tvBannerTextDesc.setText(bannerTextDescArray[newPositon]);
            layoutCircleGroup.getChildAt(prePosition).setEnabled(false);
            layoutCircleGroup.getChildAt(newPositon).setEnabled(true);
            prePosition = newPositon;
        }

    }

}