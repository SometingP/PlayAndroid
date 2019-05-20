package com.example.playandroid.search.model;

import android.util.Log;

import com.example.corelib.entity.HotKeysEntity;
import com.example.corelib.entity.SearchDataEntity;
import com.example.playandroid.SslFactor;
import com.example.playandroid.retorfit.Api;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchModel {
    private OkHttpClient mOkHttpClient;

    public SearchModel() {
        mOkHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(SslFactor.getSslSocketFactor())
                .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier())
                .build();
    }

    public void getHotKeys() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
        retrofit.create(Api.class).getHotKeys().enqueue(new Callback<HotKeysEntity>() {
            @Override
            public void onResponse(Call<HotKeysEntity> call, Response<HotKeysEntity> response) {
                HotKeysEntity entity = response.body();
                EventBus.getDefault().post(entity);
            }

            @Override
            public void onFailure(Call<HotKeysEntity> call, Throwable t) {

            }
        });
    }

    public void getSearchData(String search) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(Api.class).getSearchData(search).enqueue(new Callback<SearchDataEntity>() {
            @Override
            public void onResponse(Call<SearchDataEntity> call, Response<SearchDataEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<SearchDataEntity> call, Throwable t) {
                Log.e("TAG", "error：" + t.getMessage());
            }
        });
    }

}
