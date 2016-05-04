package com.iba.tradesmart.views;

import com.iba.tradesmart.R;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

/**
 * Created by Akif on 5/4/2016.
 */
public class TradeView extends BaseView {
    public TradeView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.view_activity_trade;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void setActionListeners() {

    }
}
