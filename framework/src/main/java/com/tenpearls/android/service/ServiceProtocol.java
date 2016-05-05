package com.tenpearls.android.service;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.tenpearls.android.R;
import com.tenpearls.android.interfaces.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class is used to define the basic protocol
 * for the Web service that you will use
 * and should be overridden
 */
public abstract class ServiceProtocol implements Interceptor {

    @Override
    public final Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .method(request.method(), request.body());

        HashMap<String, String> headers = getHeaders();
        if(headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder = requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }

        request = requestBuilder.build();
        return chain.proceed(request);
    }



    /**
     * Return a {@link HashMap} of all the headers that you need to send
     * with all calls
     * @return A {@link HashMap} of headers
     */

    protected abstract HashMap<String,String> getHeaders();

    /**
     * Return a the base API URL
     * @return A {@link HashMap} of headers
     */

    public abstract String getAPIUrl();

    //Parsing information


    /**
     * Override this method to parse errors from response returned by
     * server on call failure
     * @param errorJson
     *
     * @see ServiceCallback#onFailure(String, int)
     */
    protected String parseError(String errorJson, int code) {
        return errorJson;
    }


    /**
     * Override this method to parse status code from response
     * @param response
     *
     * @see ServiceCallback#onFailure(String, int)
     * @see ServiceCallback#onSuccess(Object, int)
     * @see retrofit2.Response
     */
    protected int getStatusCode(retrofit2.Response response) {
        return response.code();
    }


    /**
     * Override this method to have custom rules for authorization <br/>
     * Default unauthorized code is 401
     *
     * @see ServiceCallback#onFailure(String, int)
     * @see ServiceProtocol#onUnAuthorized(Controller)
     *
     */

    protected boolean isAuthorized(int code) {
        return code != 401;
    }


    /**
     * Override this method to perform tasks when client is unauthorized to use service
     *
     * @return true if you have consumed the event and
     * don't want service callbacks to be executed. Default is false
     *
     * @see ServiceProtocol#isAuthorized(int)
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    protected boolean onUnAuthorized(Controller controller) {
        return false;
    }

    /**
     * Override this method to implement custom retry functionality<br/>
     * By default is shows a snackbar
     */

    protected void onRetry(ServiceCall serviceCall, ServiceCallback serviceCallback, Controller controller) {
        showRetrySnackbar(R.string.error_retry_request, serviceCall, serviceCallback, controller);
    }

    /**
     * Override this method to implement custom functionality on internet connection error<br/>
     * By default is shows a snackbar
     */

    protected void onInternetConnectionError(ServiceCall serviceCall, ServiceCallback serviceCallback, Controller controller) {
        showRetrySnackbar(R.string.error_internet_connection, serviceCall, serviceCallback, controller);
    }

    /**
     * Override this method to provide a custom Connection time out in seconds
     *
     * @return time out value in seconds
     *
     */

    protected int getConnectionTimeoutInSeconds() {
        return 10;
    }

    /**
     * Override this method to provide a custom read time out in seconds
     *
     * @return time out value in seconds
     *
     */

    protected int getReadTimeoutInSeconds() {
        return 10;
    }

    /**
     * Override this method to provide a custom write time out in seconds
     *
     * @return time out value in seconds
     *
     */

    protected int getWriteTimeoutInSeconds() {
        return 10;
    }

    protected final void retry (ServiceCall serviceCall, ServiceCallback callback) {
        callback.refresh();
        if(serviceCall.isExecuted()) {
            serviceCall = serviceCall.clone();
        }
        serviceCall.enableRetry(true).enqueue(callback);
    }

    private void showRetrySnackbar(int textResource, final ServiceCall serviceCall, final ServiceCallback callback, Controller controller) {

        if(controller == null) {
            return;
        }

        final View view  = controller.getView();
        if(view == null) {
            return;
        }

        final Snackbar snackbar = Snackbar.make(view, textResource, Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setAction(R.string.btn_title_retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retry(serviceCall, callback);
                snackbar.dismiss();
            }
        });

        snackbar.show();
        controller.setSnackbar(snackbar);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                snackbar.dismiss();
//                view.setOnClickListener(null);
//            }
//        });
    }

}
