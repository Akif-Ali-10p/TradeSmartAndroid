package com.iba.tradesmart;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

/**
 * Created by firdous on 03/05/16.
 */
public class FeedActivityView extends BaseView {
    public FeedActivityView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_feed;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void setActionListeners() {

    }
}
