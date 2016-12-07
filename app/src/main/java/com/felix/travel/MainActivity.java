package com.felix.travel;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private static final String NAV_ITEM_ID = "nav_index";
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private BottomTab myTabLayout;
    private MyViewPager myViewPager;
    private int navItemId;
    private BottomTab Mytable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navItemId);
    }


    private void navigateTo(MenuItem menuItem){
        //mContentView.setText(menuItem.getTitle());

        navItemId = menuItem.getItemId();
        menuItem.setChecked(true);
    }

    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        myTabLayout= (BottomTab) findViewById(R.id.tabLayout);
        myViewPager= (MyViewPager) findViewById(R.id.viewPager);
    }

    private void initListeners(Bundle savedInstanceState){

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, menuItem.getTitle() + " pressed", Toast.LENGTH_LONG).show();
                navigateTo(menuItem);

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if(null != savedInstanceState){
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, R.id.navigation_item_1);
        }
        else{
            navItemId = R.id.navigation_item_1;
        }

        navigateTo(mNavigationView.getMenu().findItem(navItemId));


        MyFragmentAdapter myfragAdapter = new MyFragmentAdapter(getSupportFragmentManager(),
                myViewPager.getFragmentList());

        myViewPager.setAdapter(myfragAdapter);
        myViewPager.setOffscreenPageLimit(2);

        myTabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.i("TAB", "selected" + tab.getPosition());
        tab.setIcon(BottomTab.tabIcon_bule[tab.getPosition()]);
        //toobat_centertv.setText(BottomTab.tabTitle[tab.getPosition()]);
        myViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.setIcon(BottomTab.tabIcon_gray[tab.getPosition()]);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


}
