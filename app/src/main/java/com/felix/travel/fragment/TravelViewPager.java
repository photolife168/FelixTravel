package com.felix.travel.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felixlin on 2016/11/20.
 */
public class TravelViewPager extends ViewPager {

    public List<Fragment> fragmentList = new ArrayList<Fragment>();

    public TravelViewPager(Context context) {
        super(context);
        init();
    }

    public TravelViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    private void init() {
        fragmentList.add(TravelAreaFragment.newInstance());
        fragmentList.add(TravelPlanFragment.newInstance());
        fragmentList.add(TravelFoodFragment.newInstance());
    }
}
