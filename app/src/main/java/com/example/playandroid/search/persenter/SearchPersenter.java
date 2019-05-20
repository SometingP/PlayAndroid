package com.example.playandroid.search.persenter;

import com.example.corelib.entity.HotKeysEntity;
import com.example.corelib.entity.SearchDataEntity;
import com.example.playandroid.search.SearchInterface;
import com.example.playandroid.search.model.SearchModel;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

public class SearchPersenter {
    private WeakReference<SearchInterface> view;
    private SearchModel model;

    public SearchPersenter(SearchInterface searchView) {
        view = new WeakReference<>(searchView);
        model = new SearchModel();
    }

    public void getHotkeys() {
        model.getHotKeys();
    }

    public void getSearchData(String search) {
        model.getSearchData(search);
    }

    @Subscribe()
    public void updateHotkeys(HotKeysEntity entity) {
        view.get().setHotKeys(entity);
    }

    @Subscribe()
    public void updateSearchData(SearchDataEntity entity) {
        view.get().setSearchDatas(entity);
    }

}
