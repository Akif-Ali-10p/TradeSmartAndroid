package com.tenpearls.android.service.converters;

import com.tenpearls.android.interfaces.WebServiceRequest;
import com.tenpearls.android.interfaces.WebServiceResponse;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.BooleanResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.ByteResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.CharacterResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.DoubleResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.FloatResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.IntegerResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.LongResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.ShortResponseBodyConverter;
import com.tenpearls.android.service.converters.ScalarResponseBodyConverters.StringResponseBodyConverter;
import com.tenpearls.android.utilities.JavaUtility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * Created on 11/02/2016.
 */
@SuppressWarnings("unchecked")
public class ServiceConverterFactory extends Converter.Factory{

    @Override public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                                    Annotation[] parameterAnnotations,
                                                                    Annotation[] methodAnnotations, Retrofit retrofit) {

        Class<?> responseClass = JavaUtility.getClassOfTokenType(type);

        if(WebServiceRequest.class.isAssignableFrom(responseClass)) {
            return ServiceRequestConverter.INSTANCE;
        }

        if (type == String.class
                || type == boolean.class
                || type == Boolean.class
                || type == byte.class
                || type == Byte.class
                || type == char.class
                || type == Character.class
                || type == double.class
                || type == Double.class
                || type == float.class
                || type == Float.class
                || type == int.class
                || type == Integer.class
                || type == long.class
                || type == Long.class
                || type == short.class
                || type == Short.class) {
            return ScalarRequestBodyConverter.INSTANCE;
        }

        return null;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        Class<?> responseClass = JavaUtility.getClassOfTokenType(type);
        if(WebServiceResponse.class.isAssignableFrom(responseClass)) {
            return new ServiceResponseConverter(type);
        }

        if (type == String.class) {
            return StringResponseBodyConverter.INSTANCE;
        }
        if (type == Boolean.class) {
            return BooleanResponseBodyConverter.INSTANCE;
        }
        if (type == Byte.class) {
            return ByteResponseBodyConverter.INSTANCE;
        }
        if (type == Character.class) {
            return CharacterResponseBodyConverter.INSTANCE;
        }
        if (type == Double.class) {
            return DoubleResponseBodyConverter.INSTANCE;
        }
        if (type == Float.class) {
            return FloatResponseBodyConverter.INSTANCE;
        }
        if (type == Integer.class) {
            return IntegerResponseBodyConverter.INSTANCE;
        }
        if (type == Long.class) {
            return LongResponseBodyConverter.INSTANCE;
        }
        if (type == Short.class) {
            return ShortResponseBodyConverter.INSTANCE;
        }

        return null;
    }
}
