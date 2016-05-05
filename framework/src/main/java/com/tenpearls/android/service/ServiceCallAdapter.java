package com.tenpearls.android.service;

import com.tenpearls.android.R;
import com.tenpearls.android.utilities.DeviceUtility;
import com.tenpearls.android.utilities.StringUtility;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 27/01/2016.
 */
final class ServiceCallAdapter<T> implements ServiceCall<T> {

    private final Call<T> call;
    private boolean shouldShowRetry = false;
    int maxRetries;
    int retryCount;

    ServiceCallAdapter(Call<T> call) {
        this.shouldShowRetry = false;
        this.call = call;
        this.maxRetries = -1;
        this.retryCount = 0;
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone", "unchecked"})
    @Override
    public ServiceCall<T> clone() {
        ServiceCallAdapter callAdapter = new ServiceCallAdapter<>(call.clone());
        callAdapter.maxRetries = maxRetries;
        callAdapter.retryCount = retryCount;
        return callAdapter;
    }

    @Override
    public ServiceCall<T> enableRetry(boolean enable) {
        shouldShowRetry = enable;
        return this;
    }

    @Override
    public ServiceCall<T> maxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    @Override
    public boolean isExecuted() {
        return call != null && call.isExecuted();
    }

    @Override
    public void enqueue(final ServiceCallback callback) {
        callback.refresh();
        if(!DeviceUtility.isInternetConnectionAvailable(callback.getController().getBaseActivity())) {
            callback.onInternetConnectionError(this);
            return;
        }
        callback.willStartCall();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                ServiceCallAdapter.this.onResponse(callback, call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                ServiceCallAdapter.this.onFailure(callback, call, t);
            }
        });
    }

    private void onResponse(ServiceCallback callback, Call<T> call, Response<T> response) {
        int code = callback.getStatusCode(response);

        if(!callback.isAuthorized(code)) {
            callback.onUnAuthorized();
        }

        try {
            if(!callback.areCallbacksEnabled()) {
                return;
            }

            if(response.errorBody() != null) {
                failure(callback, callback.getErrorMessage(response.errorBody().string(), code), code);
                return;
            }
            callback.success(response.body(), code);

        } catch (Exception ex) {
            failure(callback, callback.getDefaultErrorMessage(), callback.getDefaultErrorCode());
        }
    }

    private void onFailure(ServiceCallback callback, Call<T> call, Throwable t) {

        String message = t.getMessage();
        if(t instanceof SocketTimeoutException) {
            message = callback.getController().getBaseActivity().getString(R.string.error_connection_timeout);
        }

        if(StringUtility.isEmptyOrNull(message)) {
            message = callback.getDefaultErrorMessage();
        }

        failure(callback, message, callback.getDefaultErrorCode());
    }


    private void failure(final ServiceCallback callback, String errorMessage, int code) {
        callback.failure(errorMessage, code);
        if(!shouldShowRetry) {
            return;
        }

        retryCount++;
        if(maxRetries > 0 && retryCount > maxRetries) {
            return;
        }

        callback.initiateRetry(this.clone());
    }

}
