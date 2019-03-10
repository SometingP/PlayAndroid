package com.example.loginmodule.model;

import com.example.corelib.net.RetorfitService;
import com.example.corelib.net.rx.RxjavaClient;
import com.example.corelib.net.rx.RxjavaService;
import com.example.loginmodule.bean.LoginResultBean;
import com.example.loginmodule.bean.RegisterResultBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 登录模块请求实现类
 *
 * @author 彭翔宇
 */
public class ModuleModelImpl implements ModuleModel {
    /**
     * 登录
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(Api.class).login(username,password).enqueue(new Callback<LoginResultBean>() {
            @Override
            public void onResponse(Call<LoginResultBean> call, Response<LoginResultBean> response) {
                LoginResultBean body = response.body();
                EventBus.getDefault().post(body);
            }

            @Override
            public void onFailure(Call<LoginResultBean> call, Throwable t) {
            }
        });
    }

    /**
     *
     *
     */
    @Override
    public void register(String username,String password,String repassword) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(Api.class).register(username,password,repassword).enqueue(new Callback<RegisterResultBean>() {
            @Override
            public void onResponse(Call<RegisterResultBean> call, Response<RegisterResultBean> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<RegisterResultBean> call, Throwable t) {

            }
        });
    }
}
