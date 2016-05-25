package com.iba.tradesmart.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iba.tradesmart.entities.TransactionsResponse;
import com.iba.tradesmart.fragments.base.BaseFragment;
import com.iba.tradesmart.services.ServiceFactory;
import com.iba.tradesmart.views.TransactionHistoryFragmentView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.service.ServiceCallback;
import com.tenpearls.android.views.BaseView;

/**
 * Created by on 5/5/2016.
 */
public class TransactionHistoryFragment extends BaseFragment implements ServiceSecondaryEventHandler {
    @Override
    protected BaseView getViewForController(Controller controller) {
        return new TransactionHistoryFragmentView(controller);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getTransactionsHistory("Akif");
        super.onActivityCreated(savedInstanceState);
    }

    public void getTransactionsHistory(String username) {
        ((ServiceFactory)serviceFactory).loadTradeService();
        ((ServiceFactory)serviceFactory).tradeService.getTransactionsHistory("getData", username).enqueue(new ServiceCallback(this, this){
            @Override
            protected void onSuccess(Object response, int code) {
                TransactionsResponse transactionsResponse = (TransactionsResponse) response;
                if (transactionsResponse != null) {
                    ((TransactionHistoryFragmentView) view).setTransactionsList(transactionsResponse.getList());
                }
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
