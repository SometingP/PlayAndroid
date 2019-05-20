package com.example.playandroid.fragment.project;


import com.example.playandroid.SslFactor;
import com.example.playandroid.retorfit.Api;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pengxiangyu on 2019/4/9.
 */

public class ProjectViewModel {
    public void getProjectDatas(int cid, final ProjectListener listener) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().
                        sslSocketFactory(SslFactor.getSslSocketFactor())
                        .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier())
                        .build())
                .build();
        client.create(Api.class).getProjectDatas(cid).enqueue(new Callback<ProjectEntity>() {
            @Override
            public void onResponse(Call<ProjectEntity> call, Response<ProjectEntity> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProjectEntity> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
