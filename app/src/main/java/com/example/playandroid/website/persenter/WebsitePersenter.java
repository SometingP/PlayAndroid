package com.example.playandroid.website.persenter;

import com.example.corelib.entity.WebsiteEntity;
import com.example.playandroid.website.WebsiteInterface;
import com.example.playandroid.website.model.WebsiteModel;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

public class WebsitePersenter {
    private WeakReference<WebsiteInterface> view;
    private WebsiteModel model;

    public WebsitePersenter(WebsiteInterface websiteView) {
        view = new WeakReference<>(websiteView);
        model = new WebsiteModel();
    }

    public void getWebsiteDatas() {
        model.getWebsites();
    }

    @Subscribe()
    public void updateWebsite(WebsiteEntity entity) {
        view.get().setWebsites(entity);
    }
}
