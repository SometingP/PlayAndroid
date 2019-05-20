package com.example.playandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.corelib.util.AppUtils;

/**
 * 文章展示
 *
 * @author 彭翔宇
 */
public class ArticleActivity extends AppCompatActivity {
    private WebView mWebView;
    private ImageView mCallBack;
    private TextView mTitle;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = null;
        if (getIntent() != null) {
            intent = getIntent();
        }
        preferences = this.getSharedPreferences("status", Context.MODE_PRIVATE);
        mTitle = findViewById(R.id.title);
        mCallBack = findViewById(R.id.callback);
        mWebView = findViewById(R.id.webview);
        mTitle.setText(intent.getStringExtra("title"));
        setWebView(intent.getStringExtra("url"));

        mCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setWebView(String url) {
        mWebView.getSettings().setBlockNetworkImage(preferences.getBoolean("model_statu", false));
        setWebViewSelfAdaption();
        if (preferences.getBoolean("cache_statu", false)) {
            setWebViewCache();
        }
        mWebView.setWebViewClient(webViewClient);
        mWebView.loadUrl(url);
    }

    /**
     * 设置WebView缓存
     */
    private void setWebViewCache() {
        //设置缓存模式
        if (AppUtils.getInstance().isNetWork(this)) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        // 开启 DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "webview_cache";
        //设置  Application Caches 缓存目录
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        mWebView.getSettings().setAppCacheEnabled(true);

    }

    /**
     * 设置WebView加载页面自适应
     */
    private void setWebViewSelfAdaption() {
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        //设置页面自适应屏幕
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        //针对pad设置页面自适应屏幕
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 120) {
            mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == 160) {
            mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 240) {
            mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

        }
    }

    WebViewClient webViewClient = new WebViewClient() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request.getUrl()));
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }
    };
}
