package com.example.loginmodule.model;

import com.example.loginmodule.bean.LoginResultBean;
import com.example.loginmodule.bean.RegisterResultBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Retrofit 请求接口
 *
 * @author 彭翔宇
 */
public interface Api {

    @POST("user/login")
    @FormUrlEncoded
    Call<LoginResultBean> login(@Field("username") String userName, @Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Call<RegisterResultBean> register(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("repassword") String repassword);


}
