package com.felix.travel.callback;

import com.felix.travel.bean.JsonTravel;

import java.util.List;

/**
 * Created by felixlin on 2017/1/15.
 */
public interface TraveAreaApiCallback {

    void onSuccess(List<JsonTravel> jsonTravelList);
    void onfailed(List<JsonTravel> jsonTravelList);
}
