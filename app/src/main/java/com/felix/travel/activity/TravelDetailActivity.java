package com.felix.travel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.felix.travel.MainActivity;
import com.felix.travel.R;

import org.w3c.dom.Text;

/**
 * Created by felixlin on 2017/1/2.
 */
public class TravelDetailActivity extends AppCompatActivity{

    private TextView mTravelAreaName, mTravelAreaDetail;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        init(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(Bundle savedInstanceState){
        initView();
        initToolBar();
        handleIntent();
    }

    private void initView(){
        mTravelAreaName = (TextView) findViewById(R.id.tv_app_travel_area_name);
        mTravelAreaDetail = (TextView) findViewById(R.id.tv_app_travel_area_detail);
    }

    private void initToolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar_travel_detail);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void handleIntent(){
        Bundle extras = getIntent().getExtras();
        String travelAreaName =extras.getString("travelAreaName");
        String travelAreaDeatil = extras.getString("travelAreaDetail");
        mTravelAreaName.setText(travelAreaName);
        mTravelAreaDetail.setText(travelAreaDeatil);
    }


}
