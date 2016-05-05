package com.tenpearls.android.service.converters;

import com.tenpearls.android.interfaces.WebServiceResponse;
import com.tenpearls.android.utilities.JavaUtility;
import com.tenpearls.android.utilities.JsonUtility;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created on 11/02/2016.
 */
@SuppressWarnings("unchecked")
final class ServiceResponseConverter<T extends WebServiceResponse> implements Converter<ResponseBody, T>{

    private T obj;

    ServiceResponseConverter(Type type) {

        try {
            obj = (T) JavaUtility.getClassOfTokenType(type).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        if(obj != null) {
            obj.loadJson(JsonUtility.parse(value.string()));
        }
        return obj;
    }
}
