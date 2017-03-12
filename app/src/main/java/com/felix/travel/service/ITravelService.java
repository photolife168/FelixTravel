package com.felix.travel.service;

import com.felix.travel.callback.ISearchResultCallback;
import com.felix.travel.callback.ITraveAreaApiCallback;

import java.util.List;

import greendao.bean.Travel;

/**
 * Created by felixlin on 2017/2/5.
 */
public interface ITravelService {

    /**
     * 從DB讀取Travel的資料
     * @param callback
     */
    void loadTravelInfoFromDB(ITraveAreaApiCallback callback);

    /**
     * 從API取得Travel資料
     * @param callback
     */
    void getTravelInfoFromAPI(ITraveAreaApiCallback callback);

    /**
     * 根據搜尋結果找尋DB中相關的Travel資料
     * @param searchText
     * @return
     */
    List<Travel> getTravelListBySearchText(String searchText, ISearchResultCallback callback);


}
