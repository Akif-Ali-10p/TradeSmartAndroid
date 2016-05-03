package com.tenpearls.android.service;

import com.tenpearls.android.R;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.utilities.StringUtility;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class will be used as a Callback for all
 * the Webservice calls
 */
public abstract class ServiceCallback implements Callback {

    static  final  int DEFAULT_ERROR_CODE = -1;

    ServiceProtocol serviceProtocol;
    Controller controller;
    private ServiceSecondaryEventHandler serviceSecondaryEventHandler;

    public ServiceCallback(Controller controller) {
        this.controller = controller;
        this.serviceSecondaryEventHandler = null;
        this.serviceProtocol = ServiceManager.getInstance().getServiceProtocol();
    }

    public ServiceCallback(Controller controller, ServiceSecondaryEventHandler serviceSecondaryEventHandler) {

        this.controller = controller;
        this.serviceSecondaryEventHandler = serviceSecondaryEventHandler;
        this.serviceProtocol = ServiceManager.getInstance().getServiceProtocol();
    }

    @Override
    public final void onResponse(Response response) {
        int code = getStatusCode(response);
        try {
            if(response.errorBody() != null) {
                failure(getErrorMessage(response.errorBody().string(), code), code);
                return;
            }
            success(response.body(),code);

        } catch (Exception ex) {
            failure(getDefaultErrorMessage(), DEFAULT_ERROR_CODE);
        }
    }

    private final String getErrorMessage(String response, int code) {
        try {
            if(serviceProtocol != null) {
                String errorMessage = serviceProtocol.parseError(response, code);
                if(!StringUtility.isEmptyOrNull(errorMessage)) {
                    return errorMessage;
                }
            }

        } catch (Exception ex) {

        }

        return getDefaultErrorMessage();
    }

    private final int getStatusCode(Response response) {

        int code = response.code();
        if(serviceProtocol != null) {
            code = serviceProtocol.getStatusCode(response);
        }
        return code;
    }

    private void success(final Object response,final int code) {

        executeCallOnUIThread(new Runnable() {
            @Override
            public void run() {
                onSuccess(response, code);
                if(serviceSecondaryEventHandler == null) {
                    return;
                }

                serviceSecondaryEventHandler.didFinishCall(true);
            }
        });
    }

    private void failure(final String errorMessage,final int code) {

        executeCallOnUIThread(new Runnable() {
            @Override
            public void run() {
                onFailure(errorMessage, code);
                if(serviceSecondaryEventHandler == null) {
                    return;
                }

                serviceSecondaryEventHandler.didFinishCall(false);
            }
        });
    }

    @Override
    public final void onFailure(Throwable t) {
        failure(t.getMessage(), DEFAULT_ERROR_CODE);
    }

    /**
     * This method will be called when a successful response
     * is received
     * @param response Cast this response object to the the
     *                 desired {@link com.tenpearls.android.entities.BaseEntity}
     *                 or {@link com.tenpearls.android.service.response.BaseResponse}
     *                 subclass
     * @param code HTTP Status code
     *
     * @see ServiceCallback#onFailure(String, int)
     */
    protected abstract void onSuccess(Object response, int code);


    /**
     * This method will be called when the call
     * fails
     * @param errorMessage Error Message returned by {@link ServiceProtocol#parseError(String)}
     * @param code HTTP Status code
     *
     * @see ServiceCallback#onSuccess(Object, int)
     * @see ServiceProtocol#parseError(String)
     */
    protected abstract void onFailure(String errorMessage, int code);



    private String getDefaultErrorMessage() {
        return controller.getBaseActivity().getResources().getString(R.string.error_message_default);
    }

    public void willStartCall() {

        if(serviceSecondaryEventHandler == null) {
            return;
        }

        executeCallOnUIThread(new Runnable() {
            @Override
            public void run() {
                serviceSecondaryEventHandler.willStartCall();
            }
        });
    }


    private void executeCallOnUIThread(Runnable runnable) {

        controller.getBaseActivity().runOnUiThread(runnable);
    }
}
