package com.felix.travel;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.felix.travel.activity.SearchResultActivity;
import com.felix.travel.adapter.FragmentAdapter;
import com.felix.travel.fragment.TravelViewPager;
import com.felix.travel.viewpager.DepthPageTransformer;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private Toolbar mToolbar;
    private TravelViewPager mViewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private void init(Bundle savedInstanceState) {
        initToolbar();
        initView();
        initViewPager();
        initListeners(savedInstanceState);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (TravelViewPager) findViewById(R.id.viewPager);
    }

    private void initViewPager() {
        //viewpager
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mViewPager.getFragmentList()));
        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        //tablayout
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(getResources().getString(R.string.main_bottom_tab_title_1)).setIcon(R.drawable.ic_subway_24dp);
        mTabLayout.getTabAt(1).setText(getResources().getString(R.string.main_bottom_tab_title_2)).setIcon(R.drawable.ic_store_24dp);
        mTabLayout.getTabAt(2).setText(getResources().getString(R.string.main_bottom_tab_title_3)).setIcon(R.drawable.ic_food_24dp);
        mTabLayout.addOnTabSelectedListener(this);
    }

    private void initListeners(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


}
