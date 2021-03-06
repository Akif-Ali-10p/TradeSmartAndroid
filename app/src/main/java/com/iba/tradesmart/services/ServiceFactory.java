package com.iba.tradesmart.services;

import com.iba.tradesmart.services.interfaces.TradeService;

/**
 * Created on 08/02/2016.
 */
public class ServiceFactory extends com.tenpearls.android.service.ServiceFactory {

    public TradeService tradeService;

    public void loadTradeService() {
        if (tradeService == null) {
            tradeService = loadService(TradeService.class);
        }
    }

    @Override
    public com.tenpearls.android.service.ServiceProtocol getServiceProtocol() {
        return new ServiceProtocol();
    }


}
