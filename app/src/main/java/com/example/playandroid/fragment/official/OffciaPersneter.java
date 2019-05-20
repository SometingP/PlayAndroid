package com.example.playandroid.fragment.official;


import com.example.corelib.entity.OffcialAccountTitleEntity;
import org.greenrobot.eventbus.Subscribe;
import java.lang.ref.WeakReference;

/**
 * 公众号列表页面逻辑处理类
 *
 * @author 彭翔宇
 */
public class OffciaPersneter {
    private WeakReference<OffcialView> view;
    private OffciaModel model;

    public OffciaPersneter(OffcialView offcialView) {
        view = new WeakReference<>(offcialView);
        model = new OffciaModel();
    }

    /**
     * 获取公众号列表数据接口
     */
    public void getOffciaAccountTitles() {
        model.getOffciaAccountTitle();
    }

    /**
     * 公众号列表通过EventBus传入该方法
     * @param entity
     */
    @Subscribe()
    public void updateTitles(OffcialAccountTitleEntity entity) {
        view.get().setOffcialAccountTitltes(entity);
    }
}
