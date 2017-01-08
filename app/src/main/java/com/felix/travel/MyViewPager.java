package com.felix.travel;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.felix.travel.fragment.TravelAreaFragment;
import com.felix.travel.fragment.TravelFoodFragment;
import com.felix.travel.fragment.TravelGiftFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felixlin on 2016/11/20.
 */
public class MyViewPager extends ViewPager {

    public List<Fragment> fragmentList = new ArrayList<Fragment>();
    private boolean noScroll = true;

    public MyViewPager(Context context) {
        super(context);
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(ev);
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }



    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    private void init() {
        fragmentList.add(new TravelGiftFragment());
        fragmentList.add(new TravelAreaFragment());
        fragmentList.add(new TravelFoodFragment());
    }
}
