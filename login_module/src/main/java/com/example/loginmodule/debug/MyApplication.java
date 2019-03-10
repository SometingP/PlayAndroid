package com.example.loginmodule.debug;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 登录组件单独测试Application
 *
 * @author 彭翔宇
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
