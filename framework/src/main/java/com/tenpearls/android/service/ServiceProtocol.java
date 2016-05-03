package com.tenpearls.android.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.interfaces.WebServiceResponse;
import com.tenpearls.android.service.input.BaseInput;
import com.tenpearls.android.service.response.BaseResponse;
import com.tenpearls.android.utilities.JavaUtility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class is used to define the basic protocol
 * for the Web service that you will use
 * and should be overridden
 */
public abstract class ServiceProtocol implements Interceptor, TypeAdapterFactory {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .method(request.method(), request.body());

        if(getHeaders() != null) {
            for (Map.Entry<String, String> entry : getHeaders().entrySet()) {
                requestBuilder = requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }

        request = requestBuilder.build();
        return chain.proceed(request);
    }

    @Override
    public final <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final Class currentClass = JavaUtility.getClassOfTokenType(type);
        final Boolean isEntity = JavaUtility.getSuperclassOfTokenType(type).equals(getBaseEntityClass())
                                || JavaUtility.getSuperclassOfTokenType(type).equals(BaseEntity.class)
                                || JavaUtility.getSuperclassOfTokenType(type).equals(getBaseResponseClass())
                                || JavaUtility.getSuperclassOfTokenType(type).equals(BaseResponse.class);
        final Boolean isInput = JavaUtility.getSuperclassOfTokenType(type).equals(getBaseInputClass());

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {

                try {
                    if(isInput) {
                        ((BaseInput)value).write(out);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);

                try {
                    if(isEntity) {
                        WebServiceResponse webServiceResponse = (WebServiceResponse) currentClass.newInstance();
                         webServiceResponse.loadJson(jsonElement);
                        return (T) webServiceResponse;
                    }

                    if(currentClass.equals(String.class)) {
                        return (T)jsonElement.toString();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }

    /**
     * Return a {@link HashMap} of all the headers that you need to send
     * with all calls
     * @return A {@link HashMap} of headers
     */

    protected abstract HashMap<String,String> getHeaders();

    /**
     * Return a the base API URL
     * @return A {@link HashMap} of headers
     */

    public abstract String getAPIUrl();

    //Parsing information

    /**
     * Return a {@link Class} of base entity you are using for
     * parsing data. Should be a subclass of {@link BaseEntity}
     *
     * @see BaseEntity
     */

    protected Class<? extends BaseEntity> getBaseEntityClass() {
        return BaseEntity.class;
    }

    /**
     * Return a {@link Class} of base entity you are using for
     * parsing data of collection type. Should be a subclass of {@link BaseResponse}
     *
     * @see BaseResponse
     */

    protected Class<? extends BaseResponse> getBaseResponseClass() {
        return BaseResponse.class;
    }

    /**
     * Return a {@link Class} of base input you are using for
     * creating json for POST Request. Should be a subclass of {@link BaseInput}
     *
     * @see BaseInput
     */
    protected Class<? extends BaseInput> getBaseInputClass() {
        return BaseInput.class;
    }


    /**
     * Override this method to parse errors from response returned by
     * server on call failure
     * @param errorJson
     *
     * @see ServiceCallback#onFailure(String, int)
     */
    protected String parseError(String errorJson, int code) {
        return errorJson;
    }


    /**
     * Override this method to parse status code from response
     * @param response
     *
     * @see ServiceCallback#onFailure(String, int)
     * @see ServiceCallback#onSuccess(Object, int)
     * @see retrofit2.Response
     */
    protected int getStatusCode(retrofit2.Response response) {
        return response.code();
    }
}
