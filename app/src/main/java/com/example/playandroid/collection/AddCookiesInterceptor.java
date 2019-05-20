package com.example.playandroid.collection;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie","JSESSIONID=A39A318F24C2B60A3CB4B2B27318125C");
        builder.addHeader("Cookie", "loginUserName=412406203%40qq.com");
        builder.addHeader("Cookie","token_pass=2589c6ca18c41a74843d74a8a40eac9b");
        builder.addHeader("Cookie","loginUserName_wanandroid_com=412406203%40qq.com");
        builder.addHeader("Cookie","token_pass_wanandroid_com=2589c6ca18c41a74843d74a8a40eac9b");
        return chain.proceed(builder.build());
    }
}
