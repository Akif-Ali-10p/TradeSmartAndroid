package com.iba.tradesmart;

import com.facebook.FacebookSdk;

/**
 * Created on 2/3/2016.
 */
public class Application extends android.app.Application {
    static Application applicationInstance;

    @Override
    public void onCreate () {
        super.onCreate ();
        applicationInstance = this;

        initializeFacebook();
    }

    private void initializeFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    public void onTerminate () {
        super.onTerminate();
    }

    public static Application getInstance () {
        return applicationInstance;
    }

}
