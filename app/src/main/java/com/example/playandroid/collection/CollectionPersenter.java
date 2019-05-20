package com.example.playandroid.collection;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

public class CollectionPersenter {
    private WeakReference<CollectionView> views;
    private CollectionRequetModel model;

    public CollectionPersenter(CollectionView view) {
        views = new WeakReference<>(view);
        model = new CollectionRequetModel();
    }

    public void getCollectionArticleList(){
        model.getCollectionArticleListDatas();
    }

    @Subscribe()
    public void updateCollectionArticleList(CollectionArticleEntity entity){
        views.get().setCollectionArticleList(entity);
    }
}
