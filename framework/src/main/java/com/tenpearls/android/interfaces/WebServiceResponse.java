package com.tenpearls.android.interfaces;

import com.google.gson.JsonElement;

/**
 * An interface that is to be implemented by objects that will
 * act as Response entities for Web service
 */

public interface WebServiceResponse {

    /**
     * This method will be called when webservice has
     * received the response and its time to bind that
     * json response to to an entity(POJO)
     * @param jsonElement
     */
    void loadJson(JsonElement jsonElement);
}
