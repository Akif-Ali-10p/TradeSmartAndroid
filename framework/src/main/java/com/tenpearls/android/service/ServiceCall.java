package com.tenpearls.android.service;

/**
 * Created on 27/01/2016.
 */
public interface ServiceCall<T> {

    void cancel();
    void enqueue(ServiceCallback callback);

    ServiceCall<T> clone();
    ServiceCall<T> enableRetry(boolean enable);
    ServiceCall<T> maxRetries(int maxRetries);
    boolean isExecuted();

}
