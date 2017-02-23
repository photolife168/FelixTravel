package com.felix.travel.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by felixlin on 2017/2/14.
 */
public interface HotelAPI {
    @GET("apiAccess")
    Call<ResponseBody> loadTravelInfos(@Query("scope") String scope,
                                       @Query("rid") String rid);
}
