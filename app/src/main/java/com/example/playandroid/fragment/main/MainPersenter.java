package com.example.playandroid.fragment.main;

import com.example.corelib.base.BasePersenter;
import com.example.corelib.entity.ArticleEntity;
import com.example.corelib.entity.BannerEntity;
import com.example.playandroid.fragment.main.model.MainFModel;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

public class MainPersenter implements BasePersenter {
    private WeakReference<MainInterface> views;
    private MainFModel model;

    public MainPersenter(MainInterface view) {
        views = new WeakReference<>(view);
        model = new MainFModel();
    }

    public void getBannerData() {
        model.getBannerData();
    }

    public void getHomeArticlerList(int page) {
        model.getHomeArticleList(page);
    }

    @Subscribe()
    public void updateBannerData(BannerEntity bannerEntity) {
        views.get().loadBannerData(bannerEntity);
    }

    @Subscribe()
    public void updateHomeArticlerList(ArticleEntity articleEntity) {
        views.get().loadHomeArticleList(articleEntity);
    }

    @Override
    public void onDistroy() {

    }
}
