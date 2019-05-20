package com.example.playandroid.fragment.knowledge.model;

import android.util.Log;

import com.example.corelib.entity.KnowlegeEntity;
import com.example.playandroid.retorfit.Api;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 知识体系数据请求
 * @author 彭翔宇
 */
public class KnowlegeModel {
    public void loadKnowlegeDataList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(Api.class).getKnowlegeDataList().enqueue(new Callback<KnowlegeEntity>() {
            @Override
            public void onResponse(Call<KnowlegeEntity> call, Response<KnowlegeEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<KnowlegeEntity> call, Throwable t) {
                Log.e("TAG", "errorMsg：" + t.getMessage());
            }
        });
    }
}
