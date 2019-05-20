package com.example.playandroid.fragment.project;

import android.util.Log;

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
 * 项目分类请求Model类
 * @author pengxiangyu
 * @date 2019/4/9
 */

public class ProjectModel {

    /**
     * 获取项目分类列表数据
     */
    public void getProjectTitles() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().
                        sslSocketFactory(SslFactor.getSslSocketFactor())
                        .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier())
                        .build())
                .build();
        client.create(Api.class).getProjectTitles().enqueue(new Callback<ProjectTitleEntity>() {
            @Override
            public void onResponse(Call<ProjectTitleEntity> call, Response<ProjectTitleEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<ProjectTitleEntity> call, Throwable t) {
                Log.e("TAG","++++++++++++++++++++1");
            }
        });
    }

}
