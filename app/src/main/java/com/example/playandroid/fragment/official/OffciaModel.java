package com.example.playandroid.fragment.official;

import com.example.corelib.entity.OffcialAccountTitleEntity;
import com.example.playandroid.SslFactor;
import com.example.playandroid.retorfit.Api;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OffciaModel {

    public void getOffciaAccountTitle() {
        new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().sslSocketFactory(SslFactor.getSslSocketFactor())
                        .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier()).build())
                .build().create(Api.class).getOffciaAccountTitle().enqueue(new Callback<OffcialAccountTitleEntity>() {
            @Override
            public void onResponse(Call<OffcialAccountTitleEntity> call, Response<OffcialAccountTitleEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<OffcialAccountTitleEntity> call, Throwable t) {

            }
        });
    }
}
