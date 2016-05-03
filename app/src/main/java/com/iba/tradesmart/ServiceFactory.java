package com.iba.tradesmart;

/**
 * Created on 08/02/2016.
 */
public class ServiceFactory extends com.tenpearls.android.service.ServiceFactory {

    @Override
    public com.tenpearls.android.service.ServiceProtocol getServiceProtocol() {
        return new ServiceProtocol();
    }


}
