package com.tenpearls.android.service;

/**
 * Created by khalil on 27/01/2016.
 */
public interface ServiceCall<T> {

    void cancel();
    void enqueue(ServiceCallback callback);

    ServiceCall<T> clone();

}
