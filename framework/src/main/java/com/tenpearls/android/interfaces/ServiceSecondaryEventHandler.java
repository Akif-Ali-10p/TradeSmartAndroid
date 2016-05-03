package com.tenpearls.android.interfaces;

/**
 * Created by khalil on 27/01/2016.
 */
public interface ServiceSecondaryEventHandler {

    void willStartCall();

    void didFinishCall(boolean isSuccess);
}
