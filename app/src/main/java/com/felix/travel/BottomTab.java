package com.felix.travel;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;


/**
 * Created by felixlin on 2016/11/20.
 */
public class BottomTab extends TabLayout {

    public static final int[] tabIcon_gray = new int[]{
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher};

    public static final int[] tabIcon_bule = new int[]{
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher};

    public static final String[] tabTitle = new String[]{"消息", "聯絡人", "動態"};
    private static final int NUM_TAD = 3;

    public BottomTab(Context context) {
        super(context);
        init();
    }

    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        super.setOnTabSelectedListener(onTabSelectedListener);
    }

    private void init() {

        for (int i = 0; i < NUM_TAD; i++) {
            if (i == 0) {
                addTab(newTab().setText(tabTitle[i]).setIcon(tabIcon_bule[i]));
            } else {
                addTab(newTab().setText(tabTitle[i]).setIcon(tabIcon_gray[i]));
            }
        }
    }
}
