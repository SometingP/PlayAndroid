package com.example.playandroid.fragment.main;

import com.example.corelib.entity.ArticleEntity;
import com.example.corelib.entity.BannerEntity;

public interface MainInterface {
    void loadBannerData(BannerEntity bannerEntity);

    void loadHomeArticleList(ArticleEntity articleEntity);
}
