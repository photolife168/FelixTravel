package com.felix.travel.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by felixlin on 2016/12/19.
 */
public interface TravelAPI {
    @GET("apiAccess")
    Call<ResponseBody> loadTravelInfos(@Query("scope") String scope,
                                       @Query("rid") String rid);
}
