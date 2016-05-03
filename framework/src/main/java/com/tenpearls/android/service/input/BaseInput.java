package com.tenpearls.android.service.input;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import com.tenpearls.android.utilities.JsonUtility;
import com.tenpearls.android.utilities.StringUtility;

import java.util.Map;

/**
 * An abstract class that is to be used as base for all the entities(POJO)
 * that are used for web service input i-e for input to {@link retrofit2.http.POST} calls
 */

public abstract class BaseInput {


    public final void write(JsonWriter jsonWriter) {

        try {

            JsonElement jsonElement = getJson();
            consumeIfArrayOrObject(jsonWriter, jsonElement);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createJsonObject(JsonWriter jsonWriter, JsonObject jsonObject) throws Exception{

        jsonWriter.beginObject();
        for (Map.Entry<String,JsonElement> entry : jsonObject.entrySet()) {
            String name = entry.getKey();
            if(!StringUtility.isEmptyOrNull(name)) {
                jsonWriter.name(name);
            }
            JsonElement jsonElement = entry.getValue();
            if(consumeIfArrayOrObject(jsonWriter, jsonElement)) {
                continue;
            }

            consumeJsonPrimitive(jsonWriter, name, jsonElement.getAsJsonPrimitive());

        }
        jsonWriter.endObject();
    }

    private void createJsonArray(JsonWriter jsonWriter, JsonArray jsonArray) throws Exception {

        jsonWriter.beginArray();
        for (JsonElement jsonElement: jsonArray) {
            if(consumeIfArrayOrObject(jsonWriter, jsonElement)) {
                continue;
            }

            consumeJsonPrimitive(jsonWriter, null, jsonElement.getAsJsonPrimitive());
        }
        jsonWriter.endArray();
    }

    private boolean consumeIfArrayOrObject(JsonWriter jsonWriter, JsonElement jsonElement) throws Exception {

        if(!JsonUtility.isJsonElementNull(jsonElement)
                && jsonElement.isJsonPrimitive()) {
            return false;
        }

        if(jsonElement.isJsonObject()) {
            createJsonObject(jsonWriter, jsonElement.getAsJsonObject());
        }

        if(jsonElement.isJsonArray()) {
            createJsonArray(jsonWriter, jsonElement.getAsJsonArray());
        }

        return true;
    }

    private void consumeJsonPrimitive(JsonWriter jsonWriter, String name, JsonPrimitive jsonPrimitive) throws Exception {

        if(jsonPrimitive.isString()) {
            put(jsonWriter, name, jsonPrimitive.getAsString());
            return;
        }

        if(jsonPrimitive.isNumber()) {
            put(jsonWriter, name, jsonPrimitive.getAsNumber());
            return;
        }

        if(jsonPrimitive.isBoolean()) {
            put(jsonWriter, name, jsonPrimitive.getAsBoolean());
        }
    }


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

    protected abstract JsonElement getJson();

    private void put(JsonWriter jsonWriter, String name, boolean value) throws Exception{
//        if(StringUtility.isEmptyOrNull(name)) {
//            jsonWriter.name(name);
//        }
        jsonWriter.value(value);
    }

    private void put(JsonWriter jsonWriter, String name, Number value) throws Exception{
//        if(StringUtility.isEmptyOrNull(name)) {
//            jsonWriter.name(name);
//        }
        jsonWriter.value(value);
    }

    private void put(JsonWriter jsonWriter, String name, String value) throws Exception{
//        if(StringUtility.isEmptyOrNull(name)) {
//            jsonWriter.name(name);
//        }
        jsonWriter.value(value);
    }


}
