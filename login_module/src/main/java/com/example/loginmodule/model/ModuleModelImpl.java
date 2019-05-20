package com.example.loginmodule.model;

import com.example.loginmodule.SslFactor;
import com.example.loginmodule.bean.LoginResultBean;
import com.example.loginmodule.bean.RegisterResultBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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

    private  OkHttpClient mOkHttpClient;
    private Retrofit retrofit;

    public ModuleModelImpl() {
        mOkHttpClient  = new OkHttpClient.Builder()
                .sslSocketFactory(SslFactor.getSslSocketFactor())
                .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier())
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response originalResponse = chain.proceed(chain.request());
                        //这里获取请求返回的cookie
                        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                            final StringBuffer cookieBuffer = new StringBuffer();
                        }
                        return originalResponse;
                    }
                })
                .build();
        retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    /**
     * 登录
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password) {
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
