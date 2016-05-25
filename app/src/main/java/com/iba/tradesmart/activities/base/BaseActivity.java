package com.iba.tradesmart.activities.base;

import com.iba.tradesmart.services.ServiceFactory;

/**
 * Created  on 08/02/2016.
 */
public abstract class BaseActivity extends com.tenpearls.android.activities.BaseActivity {

    @Override
    protected ServiceFactory getServiceFactory() {
        return new ServiceFactory();
    }


}
