package com.example.playandroid.website.model;

import com.example.corelib.entity.WebsiteEntity;
import com.example.playandroid.retorfit.Api;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebsiteModel {

    public void getWebsites() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(Api.class).getWebsiteDatas().enqueue(new Callback<WebsiteEntity>() {
            @Override
            public void onResponse(Call<WebsiteEntity> call, Response<WebsiteEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<WebsiteEntity> call, Throwable t) {

            }
        });
    }
}
