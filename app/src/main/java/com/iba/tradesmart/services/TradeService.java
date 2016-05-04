package com.iba.tradesmart.services;

import com.tenpearls.android.service.ServiceCall;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Akif on 5/4/2016.
 */
public interface TradeService {
    @POST()
    ServiceCall<String> makeTrade(@Query("action") String action, @Query("stock") String stock, @Query("type") String type, @Query("quantity") String quantity, @Query("UserName") String userName);
}
