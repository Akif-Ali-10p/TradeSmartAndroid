package com.tenpearls.android.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created on 27/01/2016.
 */
public class ServiceCallAdapterFactory extends CallAdapter.Factory {

    @Override public CallAdapter<ServiceCall<?>> get(Type returnType, Annotation[] annotations,
                                                     Retrofit retrofit) {


        if (getRawType(returnType) != ServiceCall.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "ServiceCall must have generic type (e.g., ServiceCall<ResponseBody>)");
        }
        final Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new CallAdapter<ServiceCall<?>>() {
            @Override public Type responseType() {
                return responseType;
            }

            @Override public <R> ServiceCall<R> adapt(Call<R> call) {
                return new ServiceCallAdapter<>(call);
            }
        };
    }
}