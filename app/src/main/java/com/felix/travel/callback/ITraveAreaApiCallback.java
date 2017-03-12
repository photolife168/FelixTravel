package com.felix.travel.callback;

import com.felix.travel.bean.JsonTravel;

import java.util.List;

import greendao.bean.Travel;

/**
 * Created by felixlin on 2017/1/15.
 */
public interface ITraveAreaApiCallback {

    void onSuccess(List<JsonTravel> jsonTravelList);
    void onFailed(List<JsonTravel> jsonTravelList);
    void onLoadDBCompleted(List<Travel> dbTravelList);
}
