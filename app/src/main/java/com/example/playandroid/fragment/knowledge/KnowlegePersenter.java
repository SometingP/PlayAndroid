package com.example.playandroid.fragment.knowledge;

import com.example.corelib.base.BasePersenter;
import com.example.corelib.entity.KnowlegeEntity;
import com.example.playandroid.fragment.knowledge.model.KnowlegeModel;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 * 知识体系主页处理器
 * @author 彭翔宇
 */
public class KnowlegePersenter implements BasePersenter {
    private WeakReference<KnowlegeView> views;
    private KnowlegeModel model;

    public KnowlegePersenter(KnowlegeView view) {
        views = new WeakReference<>(view);
        model = new KnowlegeModel();
    }

    /**
     * 调用Model层方法请求知识体系列表数据
     */
    public void loadKnowlegeDataList() {
        model.loadKnowlegeDataList();
    }

    /**
     * 将请求知识体系列表返回数据传递给列表更新显示
     *
     * @param entity
     */
    @Subscribe()
    public void uploadKnowlege(KnowlegeEntity entity) {
        views.get().loadKnowlegeDataList(entity);
    }

    @Override
    public void onDistroy() {
    }
}
