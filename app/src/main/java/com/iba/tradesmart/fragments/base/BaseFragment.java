package com.iba.tradesmart.fragments.base;

import com.iba.tradesmart.services.ServiceFactory;

/**
 * Created on 08/02/2016.
 */
public abstract class BaseFragment extends com.tenpearls.android.fragments.BaseFragment {

    @Override
    protected ServiceFactory getServiceFactory() {
        return new ServiceFactory();
    }
}
