package com.tenpearls.android.service;

import com.tenpearls.android.R;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.service.response.BaseResponse;
import com.tenpearls.android.utilities.StringUtility;

import retrofit2.Response;

/**
 * This class will be used as a Callback for all
 * the Webservice calls
 */
public abstract class ServiceCallback {

    private ServiceProtocol serviceProtocol;
    Controller controller;
    private ServiceSecondaryEventHandler serviceSecondaryEventHandler;
    private boolean callbacksEnabled;

    public ServiceCallback(Controller controller) {
        this.controller = controller;
        this.serviceSecondaryEventHandler = null;
        this.serviceProtocol = ServiceManager.getInstance().getServiceProtocol();
        this.callbacksEnabled = true;
    }

    public ServiceCallback(Controller controller, ServiceSecondaryEventHandler serviceSecondaryEventHandler) {

        this.controller = controller;
        this.serviceSecondaryEventHandler = serviceSecondaryEventHandler;
        this.serviceProtocol = ServiceManager.getInstance().getServiceProtocol();
        this.callbacksEnabled = true;
    }

    final void refresh() {
        callbacksEnabled = true;
    }

    /**
     * This method will be called when a successful response
     * is received
     * @param response Cast this response object to the the
     *                 desired {@link com.tenpearls.android.entities.BaseEntity}
     *                 or {@link BaseResponse}
     *                 subclass
     * @param code HTTP Status code
     *
     * @see ServiceCallback#onFailure(String, int)
     */
    protected abstract void onSuccess(Object response, int code);


    /**
     * This method will be called when the call
     * fails
     * @param errorMessage Error Message returned by {@link ServiceProtocol#parseError(String, int)} )}
     * @param code HTTP Status code
     *
     * @see ServiceCallback#onSuccess(Object, int)
     * @see ServiceProtocol#parseError(String, int)
     */
    protected abstract void onFailure(String errorMessage, int code);


    final void success(final Object response,final int code) {

        if(!callbacksEnabled) {
            return;
        }

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

    final void failure(final String errorMessage,final int code) {

        if(!callbacksEnabled) {
            return;
        }

        executeCallOnUIThread(new Runnable() {
            @Override
            public void run() {
                onFailure(errorMessage, code);
                if (serviceSecondaryEventHandler == null) {
                    return;
                }

                serviceSecondaryEventHandler.didFinishCall(false);
            }
        });
    }

    final void willStartCall() {

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


    protected String getErrorMessage(String response, int code) {
        try {
            if(serviceProtocol != null) {
                String errorMessage = serviceProtocol.parseError(response, code);
                if(!StringUtility.isEmptyOrNull(errorMessage)) {
                    return errorMessage;
                }
            }

        } catch (Exception ignored) {

        }

        return getDefaultErrorMessage();
    }

    protected int getStatusCode(Response response) {

        int code = response.code();
        if(serviceProtocol != null) {
            code = serviceProtocol.getStatusCode(response);
        }
        return code;
    }

    final String getDefaultErrorMessage() {
        return controller.getBaseActivity().getResources().getString(R.string.error_message_default);
    }

    final int getDefaultErrorCode() {
        return -1;
    }

    protected boolean isAuthorized(int code) {

        return serviceProtocol == null || serviceProtocol.isAuthorized(code);
    }

    protected void onUnAuthorized() {

        if(serviceProtocol == null) {
            callbacksEnabled = true;
            return;
        }

        executeCallOnUIThread(new Runnable() {
            @Override
            public void run() {
                callbacksEnabled =  !serviceProtocol.onUnAuthorized(controller);

            }
        });
    }


    final void initiateRetry(final ServiceCall serviceCall) {

        executeCallOnUIThread(new Runnable() {
            @Override
            public void run() {
                onRetry(serviceCall);
            }
        });
    }

    protected void onRetry(ServiceCall serviceCall) {

        if(serviceProtocol == null) {
            return;
        }

        serviceProtocol.onRetry(serviceCall, this, getController());
    }

    protected final void retry(ServiceCall serviceCall, ServiceCallback serviceCallback) {

        if(serviceProtocol == null) {
            return;
        }

        serviceProtocol.retry(serviceCall, serviceCallback);
    }

    protected void onInternetConnectionError(ServiceCall serviceCall) {

        if (serviceProtocol == null) {
            return;
        }

        serviceProtocol.onInternetConnectionError(serviceCall, this, getController());
    }



    private void executeCallOnUIThread(Runnable runnable) {

        if(!callbacksEnabled) {
            return;
        }


        controller.getBaseActivity().runOnUiThread(runnable);
    }

    final boolean areCallbacksEnabled() {
        return callbacksEnabled;
    }

    final Controller getController() {
        return controller;
    }
}
