package com.tenpearls.android.service;

import android.content.Context;

import com.tenpearls.android.R;
import com.tenpearls.android.service.converters.ServiceConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

final class ServiceManager {

    private static ServiceManager instance;
    private ServiceProtocol serviceProtocol;
    private Retrofit retrofit;
    private Context context;

    private ServiceManager() {

    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }

        return instance;
    }

    public final void initialize(ServiceProtocol serviceProtocol, Context context) {

        this.context = context;
        this.serviceProtocol = serviceProtocol;
        validateServiceProtocol();

        if (retrofit != null) {
            return;
        }


        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .addNetworkInterceptor(serviceProtocol)
                .connectTimeout(serviceProtocol.getConnectionTimeoutInSeconds(), TimeUnit.SECONDS)
                .readTimeout(serviceProtocol.getReadTimeoutInSeconds(), TimeUnit.SECONDS)
                .writeTimeout(serviceProtocol.getWriteTimeoutInSeconds(), TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(serviceProtocol.getAPIUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(new ServiceCallAdapterFactory())
                .addConverterFactory(new ServiceConverterFactory())
                .build();
    }


    public final <S> S loadService(Class<S> serviceClass) {

        try {
            validateServiceProtocol();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return retrofit.create(serviceClass);
    }

    public ServiceProtocol getServiceProtocol() {
        return serviceProtocol;
    }


    private void validateServiceProtocol() {
        if (serviceProtocol != null) {
            return;
        }
        throw new IllegalArgumentException(context.getString(R.string.error_message_service_protocol));
    }
}
