package com.felix.travel.service;

import com.felix.travel.callback.TraveAreaApiCallback;

/**
 * Created by felixlin on 2017/2/5.
 */
public interface ITravelService {

    /**
     * 從DB讀取Travel的資料
     * @param callback
     */
    void loadTravelInfoFromDB(TraveAreaApiCallback callback);

    /**
     * 從API取得Travel資料
     * @param callback
     */
    void getTravelInfoFromAPI(TraveAreaApiCallback callback);
}
