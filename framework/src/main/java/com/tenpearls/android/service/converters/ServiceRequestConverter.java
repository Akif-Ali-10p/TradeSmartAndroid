package com.tenpearls.android.service.converters;

import com.tenpearls.android.service.input.BaseInput;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created on 11/02/2016.
 */
final class ServiceRequestConverter<T extends BaseInput> implements Converter<T, RequestBody> {

    static final ServiceRequestConverter INSTANCE = new ServiceRequestConverter();
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, value.getJson().toString());
    }
}
