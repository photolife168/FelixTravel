package com.felix.travel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felix.travel.adapter.TravelAreaAdapter;
import com.felix.travel.api.TravelAPI;
import com.felix.travel.bean.TravelInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private List<TravelInfo> mTravelInfoList = new ArrayList<TravelInfo>();
    private RecyclerView mTravelInfoRecyclerView;
    private TravelAreaAdapter mTravelAreaAdapter;
    private TravelInfo mTravelInfo;
    private List<TravelInfo> travelInfoList = new ArrayList<>();

    public TravelAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_travel_area, container, false);
        mTravelInfoRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_app_travel_area);

        mTravelAreaAdapter = new TravelAreaAdapter(getActivity(), mTravelInfoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mTravelInfoRecyclerView.setLayoutManager(mLayoutManager);
        mTravelInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTravelInfoRecyclerView.setAdapter(mTravelAreaAdapter);

        prepareMovieData();

        GetTravelInfo getTravelInfo = new GetTravelInfo();
        getTravelInfo.execute();


        return view;
    }

    private void prepareMovieData() {
        TravelInfo travelInfo = new TravelInfo();
        mTravelInfoList.add(travelInfo);

        travelInfo = new TravelInfo();
        mTravelInfoList.add(travelInfo);

        mTravelAreaAdapter.notifyDataSetChanged();
    }

    private class GetTravelInfo extends AsyncTask<String, Integer, JSONArray> {

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
            mTravelAreaAdapter.setItems(travelInfoList);
            mTravelAreaAdapter.notifyDataSetChanged();
        }
    }

    private void populateTravelInfo(JSONArray result) throws JSONException {
        for (int i = 0; i < result.length(); i++) {
            mTravelInfo = new TravelInfo();
            JSONObject travelObj = result.getJSONObject(i);
            mTravelInfo.setId(travelObj.getString("_id"));
            mTravelInfo.setRowNumber(travelObj.getString("RowNumber"));
            mTravelInfo.setRefWp(travelObj.getString("REF_WP"));
            mTravelInfo.setCat1(travelObj.getString("CAT1"));
            mTravelInfo.setCat2(travelObj.getString("CAT2"));
            mTravelInfo.setSerialNo(travelObj.getString("SERIAL_NO"));
            mTravelInfo.setMemoTime(travelObj.getString("MEMO_TIME"));
            mTravelInfo.setStitle(travelObj.getString("stitle"));
            mTravelInfo.setxBody(travelObj.getString("xbody"));
            mTravelInfo.setAvBegin(travelObj.getString("avBegin"));
            mTravelInfo.setAvEnd(travelObj.getString("avEnd"));
            mTravelInfo.setIdpt(travelObj.getString("idpt"));
            mTravelInfo.setAddress(travelObj.getString("address"));
            mTravelInfo.setxPostDate(travelObj.getString("xpostDate"));
            mTravelInfo.setFile(populateImgUrl(travelObj.getString("file")));
            mTravelInfo.setLangInfo(travelObj.getString("langinfo"));
            mTravelInfo.setPoi(travelObj.getString("POI"));
            mTravelInfo.setInfo(travelObj.getString("info"));
            mTravelInfo.setLongitude(travelObj.getString("longitude"));
            mTravelInfo.setLatitude(travelObj.getString("latitude"));
            mTravelInfo.setMrt(travelObj.getString("MRT"));

            travelInfoList.add(mTravelInfo);
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
}
