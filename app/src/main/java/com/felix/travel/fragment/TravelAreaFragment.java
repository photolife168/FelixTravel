package com.felix.travel.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felix.travel.R;
import com.felix.travel.adapter.TravelAreaAdapter;
import com.felix.travel.api.TravelAPI;
import com.felix.travel.bean.TravelInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import greendao.DaoMaster;
import greendao.DaoSession;
import greendao.Travel;
import greendao.TravelDao;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by felixlin on 2016/11/20.
 */
public class TravelAreaFragment extends Fragment {

    private TextView mTvAreaInfo;
    private RecyclerView mTravelInfoRecyclerView;
    private TravelAreaAdapter mTravelAreaAdapter;
    private ProgressBar mProgressBar;
    private List<TravelInfo> mTravelInfoList= new ArrayList<>();
    private TravelDao mTravelDao;
    private List<Travel> dbTravelList;

    public TravelAreaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_travel_area, container, false);
        init(view);

        mTravelAreaAdapter = new TravelAreaAdapter(getActivity(), mTravelInfoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mTravelInfoRecyclerView.setLayoutManager(mLayoutManager);
        mTravelInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTravelInfoRecyclerView.setAdapter(mTravelAreaAdapter);

        return view;
    }

    private void init(View view){
        initView(view);
        initDao();
        loadTravelData();
    }

    private void initView(View view){
        mTravelInfoRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_app_travel_area);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar_travel_area);
    }

    private void initDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(),"felix-db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mTravelDao = daoSession.getTravelDao();
    }

    private void loadTravelData(){
        GetTravelInfoFromDBAsyncTask getTravelInfoFromDBAsyncTask = new GetTravelInfoFromDBAsyncTask();
        getTravelInfoFromDBAsyncTask.execute();
    }

    private class GetTravelInfoFromDBAsyncTask extends AsyncTask<Void, Integer, List<Travel>>{

        @Override
        protected List<Travel> doInBackground(Void... params) {

            dbTravelList = mTravelDao.loadAll();
            if(dbTravelList != null){
                for(Travel dbTravel : dbTravelList){
                    Log.d("db travel=", dbTravel.getArea_name());
                    Log.d("-","-");
                }
            }

            return dbTravelList;
        }

        @Override
        protected void onPostExecute(List<Travel> dbTravelList) {
            super.onPostExecute(dbTravelList);
            if(dbTravelList == null){
                Log.d("db no data..","aa");
                GetTravelInfoAsyncTask getTravelInfo = new GetTravelInfoAsyncTask();
                getTravelInfo.execute();
            }else{
                for(Travel travel : dbTravelList){
                    TravelInfo travelInfo = new TravelInfo();
                    travelInfo.setStitle(travel.getArea_name());
                    travelInfo.setMrt(travel.getArea_station());
                    travelInfo.setFile(travel.getArea_pic());
                    mTravelInfoList.add(travelInfo);
                }
                mTravelAreaAdapter.setItems(mTravelInfoList);
                mTravelAreaAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }


    private class GetTravelInfoAsyncTask extends AsyncTask<String, Integer, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://data.taipei/opendata/datalist/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            TravelAPI travelAPI = retrofit.create(TravelAPI.class);
            Call<ResponseBody> call = travelAPI.loadTravelInfos("resourceAquire", "36847f3f-deff-4183-a5bb-800737591de5");
            String result = "";
            JSONArray travelInfos = null;
            try {
                Response<ResponseBody> bodyResponse = call.execute();
                String body = bodyResponse.body().string();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jsobj2 = new JSONObject(jsonObject.getString("result"));
                result = body.toString();
                travelInfos = jsobj2.getJSONArray("results");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return travelInfos;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            try {
                populateTravelInfo(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mTravelAreaAdapter.setItems(mTravelInfoList);
            mTravelAreaAdapter.notifyDataSetChanged();
            mProgressBar.setVisibility(View.GONE);
            insertDB();

        }
    }

    private void populateTravelInfo(JSONArray result) throws JSONException {
        for (int i = 0; i < result.length(); i++) {
            TravelInfo travelInfo = new TravelInfo();
            JSONObject travelObj = result.getJSONObject(i);
            travelInfo.setId(travelObj.getString("_id"));
            travelInfo.setRowNumber(travelObj.getString("RowNumber"));
            travelInfo.setRefWp(travelObj.getString("REF_WP"));
            travelInfo.setCat1(travelObj.getString("CAT1"));
            travelInfo.setCat2(travelObj.getString("CAT2"));
            travelInfo.setSerialNo(travelObj.getString("SERIAL_NO"));
            travelInfo.setMemoTime(travelObj.getString("MEMO_TIME"));
            travelInfo.setStitle(travelObj.getString("stitle"));
            travelInfo.setxBody(travelObj.getString("xbody"));
            travelInfo.setAvBegin(travelObj.getString("avBegin"));
            travelInfo.setAvEnd(travelObj.getString("avEnd"));
            travelInfo.setIdpt(travelObj.getString("idpt"));
            travelInfo.setAddress(travelObj.getString("address"));
            travelInfo.setxPostDate(travelObj.getString("xpostDate"));
            travelInfo.setFile(populateImgUrl(travelObj.getString("file")));
            travelInfo.setLangInfo(travelObj.getString("langinfo"));
            travelInfo.setPoi(travelObj.getString("POI"));
            travelInfo.setInfo(travelObj.getString("info"));
            travelInfo.setLongitude(travelObj.getString("longitude"));
            travelInfo.setLatitude(travelObj.getString("latitude"));
            travelInfo.setMrt(travelObj.getString("MRT"));

            mTravelInfoList.add(travelInfo);
        }

    }

    private String populateImgUrl(String imgUrl) {
        //img url部份, api 回傳的內容需處理並改成https
        int location = imgUrl.indexOf(".jpg");
        if (location == -1) {
            location = imgUrl.indexOf(".JPG");
        }
        if (location == -1) {
            location = imgUrl.indexOf(".png");
        }
        String noProtocalUrl = imgUrl.substring(0, location + 4);
        String httpsUrl = "https" + noProtocalUrl.substring(4, noProtocalUrl.length());
        return httpsUrl;
    }

    private void insertDB(){
        List<Travel> travelList = new ArrayList<>();
        for(TravelInfo travelInfo : mTravelInfoList){
            Travel travel = new Travel();
            travel.setArea_name(travelInfo.getStitle());
            travel.setArea_station(travelInfo.getMrt());
            travel.setArea_pic(travelInfo.getFile());

            travelList.add(travel);
        }

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(),"felix-db",null);

        SQLiteDatabase db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);

        DaoSession daoSession = daoMaster.newSession();

        mTravelDao = daoSession.getTravelDao();

        for(Travel travel : travelList){
            mTravelDao.insert(travel);
        }

    }
}
