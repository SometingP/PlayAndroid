package com.example.playandroid.fragment.knowledge.model;

import com.example.corelib.entity.ArticleEntity;
import com.example.playandroid.retorfit.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KnowledageItemModel {
    public void getKnowledgeItemList(int id, final KnowListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ArticleEntity> knowlegeItemDataList = api.getKnowlegeItemDataList(id);
        knowlegeItemDataList.enqueue(new Callback<ArticleEntity>() {
            @Override
            public void onResponse(Call<ArticleEntity> call, Response<ArticleEntity> response) {
                ArticleEntity entity = response.body();
                listener.onSucceed(entity);
            }

            @Override
            public void onFailure(Call<ArticleEntity> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });

    }
}
