package com.iba.tradesmart.fragments;

import android.util.Log;

import com.iba.tradesmart.fragments.base.BaseFragment;
import com.iba.tradesmart.services.ServiceFactory;
import com.iba.tradesmart.views.TradeFragmentView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.service.ServiceCallback;
import com.tenpearls.android.views.BaseView;

/**
 * Created by on 5/5/2016.
 */
public class TradeFragment extends BaseFragment implements ServiceSecondaryEventHandler {
    @Override
    protected BaseView getViewForController(Controller controller) {
        return new TradeFragmentView(controller);
    }

    public void makeTrade(String stock, String type, String quantity, String username) {
        ((ServiceFactory)serviceFactory).loadTradeService();
        ((ServiceFactory)serviceFactory).tradeService.makeTrade("makeTrade", stock, type, quantity, username).enqueue(new ServiceCallback(this, this){
            @Override
            protected void onSuccess(Object response, int code) {
                showToast(response.toString());
            }

            @Override
            protected void onFailure(String errorMessage, int code) {
                showToast(errorMessage);
                Log.d("Login Exception", String.valueOf(code) + " " + errorMessage);
            }
        });
    }

    @Override
    public void willStartCall() {
        showLoader();
    }

    @Override
    public void didFinishCall(boolean isSuccess) {
        hideLoader();
    }
}
