package com.example.playandroid.fragment.official;

import com.example.corelib.entity.OffciaAccountEntity;
import com.example.playandroid.SslFactor;
import com.example.playandroid.retorfit.Api;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 公众号数据获取Model
 * @author 彭翔宇
 */
public class OffciaAccountItemoModel {

    /**
     * 根据指定ID获取该公众号历史文章
     *
     * @param id
     * @param listener
     */
    public void getOffCiaAccountDatas(int id, final OffciaListener listener) {
        new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().sslSocketFactory(SslFactor.getSslSocketFactor())
                        .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier()).build())
                .build().create(Api.class).getOffciaAccountDatas(id).enqueue(new Callback<OffciaAccountEntity>() {
            @Override
            public void onResponse(Call<OffciaAccountEntity> call, Response<OffciaAccountEntity> response) {
                listener.onSucceed(response.body());
            }

            @Override
            public void onFailure(Call<OffciaAccountEntity> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    /**
     * 根据关键字搜索历史文章
     * @param cid
     * @param text
     * @param listener
     */
    public void searchOffciaArticle(int cid, String text, final OffciaListener listener) {
        new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().sslSocketFactory(SslFactor.getSslSocketFactor())
                        .hostnameVerifier(new SslFactor.TrustAllHostnameVerifier()).build())
                .build().create(Api.class).getOffciaSearchDatas(cid, text).enqueue(new Callback<OffciaAccountEntity>() {
            @Override
            public void onResponse(Call<OffciaAccountEntity> call, Response<OffciaAccountEntity> response) {
                listener.onSucceed(response.body());
            }

            @Override
            public void onFailure(Call<OffciaAccountEntity> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
