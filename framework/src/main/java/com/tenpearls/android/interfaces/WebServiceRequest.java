package com.tenpearls.android.interfaces;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * An interface that is to be implemented by objects that will
 * act as Request entities for Web service
 */
public interface WebServiceRequest {

    /**
     * This method will be called when input is
     * being prepared for {@link retrofit2.http.POST} calls
     *
     * @return Return a valid {@link JsonElement} from this
     * method. A {@link JsonElement} could be both a {@link JsonObject}
     * or a {@link JsonArray}
     *
     * @see JsonElement
     * @see JsonObject
     * @see JsonArray
     */

    JsonElement getJson();
}
