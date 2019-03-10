package com.example.corelib.net;

import com.example.corelib.configuration.ConfigKey;
import com.example.corelib.configuration.ProjectInit;
import com.example.corelib.net.rx.RxjavaService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 彭翔宇
 */
public class RetorfitCreate {
    private static final String BASE_URL = (String) ProjectInit.getConfig(ConfigKey.HOST);
    private static final Retrofit RETORFIT_CREATE = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpHolder.OK_HTTP_CLIENT)
            .build();

    private static class OkHttpHolder {
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private static class RetorfitServiceHolder {
        private static final RetorfitService RETORFIT_SERVICE = RETORFIT_CREATE.create(RetorfitService.class);
    }

    public static RetorfitService getRetorfitService() {
        return RetorfitServiceHolder.RETORFIT_SERVICE;
    }

    private static class RxjavaServiceHolder {
        private static final RxjavaService RXJAVA_SERVICE = RETORFIT_CREATE.create(RxjavaService.class);
    }

    public static RxjavaService getRxjavaService() {
        return RxjavaServiceHolder.RXJAVA_SERVICE;
    }
}
