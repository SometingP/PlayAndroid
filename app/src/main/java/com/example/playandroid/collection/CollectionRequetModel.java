package com.example.playandroid.collection;

import android.util.Log;

import com.example.playandroid.SslFactor;
import com.example.playandroid.retorfit.Api;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectionRequetModel {

    private OkHttpClient mOkHttpClient;
    private Retrofit retrofit;

    public CollectionRequetModel() {
        mOkHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(SslFactor.getSslSocketFactor())
                .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier())
                .addInterceptor(new AddCookiesInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public void setCollectionArticle(int id) {
        retrofit.create(Api.class).setCollectionArticle(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                   String result = response.body().string();
                    Log.e("TAG", "result：" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void clearCollectionArticle(int id) {
        retrofit.create(Api.class).setClearCollectionArticle(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.e("TAG", "result：" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getCollectionArticleListDatas(){
        retrofit.create(Api.class).getCollectionArticleListData().enqueue(new Callback<CollectionArticleEntity>() {
            @Override
            public void onResponse(Call<CollectionArticleEntity> call, Response<CollectionArticleEntity> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<CollectionArticleEntity> call, Throwable t) {}
        });
    }
}
