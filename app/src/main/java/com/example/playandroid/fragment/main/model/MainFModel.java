package com.example.playandroid.fragment.main.model;


import android.util.Log;

import com.example.corelib.entity.ArticleEntity;
import com.example.corelib.entity.BannerEntity;
import com.example.playandroid.SslFactor;
import com.example.playandroid.retorfit.Api;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取首页文章列表数据
 * @author 13973
 */
public class MainFModel {
  private OkHttpClient mOkHttpClient;

    public MainFModel(){
        mOkHttpClient  = new OkHttpClient.Builder()
                .sslSocketFactory(SslFactor.getSslSocketFactor())
                .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier())
                .build();
    }

    public void getBannerData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
        Api api = retrofit.create(Api.class);
        Call<BannerEntity> bannerData = api.getBannerData();
        bannerData.enqueue(new Callback<BannerEntity>() {
            @Override
            public void onResponse(Call<BannerEntity> call, Response<BannerEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<BannerEntity> call, Throwable t) {
                Log.e("TAG", "errorMsg：" + t.getMessage());
            }
        });
    }

    public void getHomeArticleList(int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
        Api api = retrofit.create(Api.class);
        api.getHomeArticleList(page).enqueue(new Callback<ArticleEntity>() {
            @Override
            public void onResponse(Call<ArticleEntity> call, Response<ArticleEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<ArticleEntity> call, Throwable t) {
                Log.e("TAG", "errorMsg：" + t.getMessage());
            }
        });

    }

}
