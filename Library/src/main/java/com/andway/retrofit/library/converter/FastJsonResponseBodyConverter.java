package com.andway.retrofit.library.converter;

import com.alibaba.fastjson.JSONReader;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by WayneSuper on 25/01/2016.
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private Type type;
    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            JSONReader reader = new JSONReader(value.charStream());
            return  reader.readObject(type);
        } finally {
            value.close();
        }
    }
}
