package com.tenpearls.android.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tenpearls.android.R;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

final class ServiceManager {

    private static ServiceManager instance;
    private ServiceProtocol serviceProtocol;
    private Retrofit retrofit;
    private Context context;

    private ServiceManager() {

    }

    public static final ServiceManager getInstance() {
        if(instance == null) {
            instance = new ServiceManager();
        }

        return instance;
    }

    public final void initialize (ServiceProtocol serviceProtocol, Context context) throws Exception {

        this.context = context;
        this.serviceProtocol = serviceProtocol;
        validateServiceProtocol();

        if(retrofit != null) {
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder().addNetworkInterceptor(serviceProtocol).build();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(serviceProtocol).create();
        retrofit = new Retrofit.Builder()
                .baseUrl(serviceProtocol.getAPIUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(new ServiceCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
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


    private void validateServiceProtocol() throws Exception {
        if(serviceProtocol != null) {
            return;
        }
        throw new IllegalArgumentException(context.getString(R.string.error_message_service_protocol));
    }
}
