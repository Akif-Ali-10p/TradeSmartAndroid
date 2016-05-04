package com.iba.tradesmart.services;

import com.iba.tradesmart.constants.ServiceConstants;

import java.util.HashMap;

/**
 * Created  on 08/02/2016.
 */
public class ServiceProtocol extends com.tenpearls.android.service.ServiceProtocol {
    @Override
    protected HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getAPIUrl() {
        return ServiceConstants.SERVICE_URL;
    }


}
