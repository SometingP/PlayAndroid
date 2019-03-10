package com.example.loginmodule.login.persenter;

import com.example.corelib.base.BasePersenter;
import com.example.corelib.net.databus.RegisterRxbus;
import com.example.loginmodule.bean.LoginResultBean;
import com.example.loginmodule.login.view.LoginInterface;
import com.example.loginmodule.model.ModuleModel;
import com.example.loginmodule.model.ModuleModelImpl;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 * 登录页面的逻辑处理层
 * @author 彭翔宇
 */
public class LoginPersenter implements BasePersenter {
    WeakReference<LoginInterface> mView;
    ModuleModel model = new ModuleModelImpl();

    public LoginPersenter(LoginInterface view) {
        mView = new WeakReference<>(view);
    }

    public void login(String username, String password) {
        model.login(username, password);
    }

    /**
     * 登录请求返回数据送给View
     * @param resultBean
     */
    @Subscribe()
    public void uploadLoginStatu(LoginResultBean resultBean) {
        mView.get().loginResult(resultBean);
    }


    /**
     * 绑定Activity生命周期，
     */
    @Override
    public void onDistroy() {

    }
}
