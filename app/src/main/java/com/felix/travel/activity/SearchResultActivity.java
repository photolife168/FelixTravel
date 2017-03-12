package com.felix.travel.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.felix.travel.R;
import com.felix.travel.callback.ISearchResultCallback;
import com.felix.travel.service.ITravelService;
import com.felix.travel.service.impl.TravelService;

/**
 * Created by felixlin on 2017/2/17.
 */
public class SearchResultActivity extends AppCompatActivity implements ISearchResultCallback {

    private String mSearchText;
    private ITravelService mITravelService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        handleIntent();
        initService();

        loadDataBySearchText();

    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mSearchText = query;
            Log.d("search string=", query);
        }
    }

    private void initService() {
        mITravelService = new TravelService(this);
    }

    private void loadDataBySearchText() {
        mITravelService.getTravelListBySearchText(mSearchText, this);
    }

    @Override
    public void onSearchDBCompleted() {

    }
}
