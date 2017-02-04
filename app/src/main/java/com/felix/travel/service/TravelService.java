package com.felix.travel.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.felix.travel.api.TravelAPI;
import com.felix.travel.bean.JsonTravel;
import com.felix.travel.callback.TraveAreaApiCallback;

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
 * Created by felixlin on 2017/2/4.
 */
public class TravelService {

    private Context mContext;
    private List<Travel> dbTravelList;
    private TravelDao mTravelDao ;
    private List<JsonTravel> mJsonTravelList = new ArrayList<>();

    private TraveAreaApiCallback mTraveAreaApiCallback;

    public TravelService(Context context){
        mContext = context;
        initDao();
    }

    private void initDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext,"felix-db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mTravelDao = daoSession.getTravelDao();
    }

    public void loadTravelInfo(TraveAreaApiCallback callback){
        mTraveAreaApiCallback = callback;
        GetTravelInfoFromDBAsyncTask getTravelInfoFromDBAsyncTask = new GetTravelInfoFromDBAsyncTask();
        getTravelInfoFromDBAsyncTask.execute();
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
            if(dbTravelList == null){
                GetTravelInfoAsyncTask getTravelInfo = new GetTravelInfoAsyncTask();
                getTravelInfo.execute();
            }else{
                for(Travel travel : dbTravelList){
                    JsonTravel jsonTravel = new JsonTravel();
                    jsonTravel.setStitle(travel.getArea_name());
                    jsonTravel.setMrt(travel.getArea_station());
                    jsonTravel.setFile(travel.getArea_pic());
                    mJsonTravelList.add(jsonTravel);
                }
                mTraveAreaApiCallback.onSuccess(mJsonTravelList);
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

            travelList.add(travel);
        }

        for(Travel travel : travelList){
            mTravelDao.insert(travel);
        }

    }
}
