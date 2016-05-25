package com.iba.tradesmart.services.interfaces;

import com.iba.tradesmart.entities.TransactionsResponse;
import com.tenpearls.android.service.ServiceCall;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Akif on 5/4/2016.
 */
public interface TradeService {
    @POST("default.aspx")
    ServiceCall<String> makeTrade(@Query("action") String action, @Query("stock") String stock, @Query("type") String type, @Query("quantity") String quantity, @Query("UserName") String userName);

    @GET("default.aspx")
    ServiceCall<TransactionsResponse> getTransactionsHistory(@Query("action") String action, @Query("UserName") String userName);
}
