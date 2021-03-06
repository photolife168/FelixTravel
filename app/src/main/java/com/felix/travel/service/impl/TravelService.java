package com.felix.travel.service.impl;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import com.felix.travel.MainActivity;
import com.felix.travel.api.TravelAPI;
import com.felix.travel.bean.JsonTravel;
import com.felix.travel.callback.ISearchResultCallback;
import com.felix.travel.callback.ITraveAreaApiCallback;
import com.felix.travel.fragment.TravelAreaFragment;
import com.felix.travel.receiver.DownloadReceiver;
import com.felix.travel.service.ITravelService;
import com.felix.travel.util.DBUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import greendao.bean.Travel;
import greendao.dao.TravelGreenDao;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by felixlin on 2017/2/4.
 */
public class TravelService implements ITravelService{

    private final String BASE_URL = "http://data.taipei/opendata/datalist/";
    private Context mContext;
    private List<Travel> dbTravelList;
    private TravelGreenDao mTravelDao ;
    private List<JsonTravel> mJsonTravelList = new ArrayList<>();
    private DownloadReceiver mDownloadReceiver;

    private ITraveAreaApiCallback mTraveAreaApiCallback;
    private ISearchResultCallback mISearchResultCallback;

    public TravelService(Context context){
        mContext = context;
        mTravelDao = DBUtils.getDaoSession(mContext).getTravelGreenDao();
        mDownloadReceiver = new DownloadReceiver(mContext);
    }

    public void loadTravelInfoFromDB(ITraveAreaApiCallback callback){
        mTraveAreaApiCallback = callback;
        GetTravelInfoFromDBAsyncTask getTravelInfoFromDBAsyncTask = new GetTravelInfoFromDBAsyncTask();
        getTravelInfoFromDBAsyncTask.execute();
    }

    public void getTravelInfoFromAPI(ITraveAreaApiCallback callback){
        mTraveAreaApiCallback = callback;
        GetTravelInfoAsyncTask getTravelInfo = new GetTravelInfoAsyncTask();
        getTravelInfo.execute();
    }

    @Override
    public List<Travel> getTravelListBySearchText(String searchText, ISearchResultCallback callback) {
        mISearchResultCallback = callback;
        return new ArrayList<Travel>();
    }

    private class GetTravelInfoFromDBAsyncTask extends AsyncTask<Void, Integer, List<Travel>> {

        @Override
        protected List<Travel> doInBackground(Void... params) {
            dbTravelList = mTravelDao.loadAll();
            return dbTravelList;
        }

        @Override
        protected void onPostExecute(List<Travel> dbTravelList) {
            super.onPostExecute(dbTravelList);
            mContext.registerReceiver(mDownloadReceiver, new IntentFilter(TravelAreaFragment.MY_MESSAGE));
            Intent intent = new Intent();
            intent.setAction(TravelAreaFragment.MY_MESSAGE);
            mContext.sendBroadcast(intent);
            mTraveAreaApiCallback.onLoadDBCompleted(dbTravelList);
        }
    }


    private class GetTravelInfoAsyncTask extends AsyncTask<String, Integer, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
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

            try {
                populateTravelInfo(travelInfos);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            insertDB();

            return travelInfos;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            mTraveAreaApiCallback.onSuccess(mJsonTravelList);
        }
    }

    private void populateTravelInfo(JSONArray result) throws JSONException {
        for (int i = 0; i < result.length(); i++) {
            JsonTravel jsonTravel = new JsonTravel();
            JSONObject travelObj = result.getJSONObject(i);
            jsonTravel.setId(travelObj.getString("_id"));
            jsonTravel.setRowNumber(travelObj.getString("RowNumber"));
            jsonTravel.setRefWp(travelObj.getString("REF_WP"));
            jsonTravel.setCat1(travelObj.getString("CAT1"));
            jsonTravel.setCat2(travelObj.getString("CAT2"));
            jsonTravel.setSerialNo(travelObj.getString("SERIAL_NO"));
            jsonTravel.setMemoTime(travelObj.getString("MEMO_TIME"));
            jsonTravel.setStitle(travelObj.getString("stitle"));
            jsonTravel.setxBody(travelObj.getString("xbody"));
            jsonTravel.setAvBegin(travelObj.getString("avBegin"));
            jsonTravel.setAvEnd(travelObj.getString("avEnd"));
            jsonTravel.setIdpt(travelObj.getString("idpt"));
            jsonTravel.setAddress(travelObj.getString("address"));
            jsonTravel.setxPostDate(travelObj.getString("xpostDate"));
            jsonTravel.setFile(populateImgUrl(travelObj.getString("file")));
            jsonTravel.setLangInfo(travelObj.getString("langinfo"));
            jsonTravel.setPoi(travelObj.getString("POI"));
            jsonTravel.setInfo(travelObj.getString("info"));
            jsonTravel.setLongitude(travelObj.getString("longitude"));
            jsonTravel.setLatitude(travelObj.getString("latitude"));
            jsonTravel.setMrt(travelObj.getString("MRT"));

            mJsonTravelList.add(jsonTravel);
        }
    }

    private class getTravelListBySearchTextAsyncTask extends AsyncTask<Void, Integer, List<Travel>> {

        @Override
        protected List<Travel> doInBackground(Void... params) {
           return new ArrayList<Travel>();
        }

        @Override
        protected void onPostExecute(List<Travel> dbTravelList) {
            super.onPostExecute(dbTravelList);

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
        for(JsonTravel jsonTravel : mJsonTravelList){
            Travel travel = new Travel();
            travel.setArea_name(jsonTravel.getStitle());
            travel.setArea_station(jsonTravel.getMrt());
            travel.setArea_pic(jsonTravel.getFile());
            travel.setArea_desc(jsonTravel.getxBody());
            travelList.add(travel);
        }

        for(Travel travel : travelList){
            mTravelDao.insert(travel);
        }

    }
}
