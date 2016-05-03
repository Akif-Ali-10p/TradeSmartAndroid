package com.tenpearls.android.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khalil on 27/01/2016.
 */
public class ServiceCallAdapter<T> implements ServiceCall<T> {

    private final Call<T> call;

    ServiceCallAdapter(Call<T> call) {
        this.call = call;
    }

    @Override public void cancel() {
        call.cancel();
    }

    @Override public void enqueue(final ServiceCallback callback) {
        callback.willStartCall();
        call.enqueue(new Callback<T>() {

            @Override public void onResponse(Response<T> response) {

                callback.onResponse(response);
            }

            @Override public void onFailure(Throwable t) {

                callback.onFailure(t);
            }
        });
    }

    @Override public ServiceCall<T> clone() {
        return new ServiceCallAdapter<>(call.clone());
    }
}
