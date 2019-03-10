package com.example.loginmodule.model;

/**
 * 登录模块的model接口类
 *
 * @author 彭翔宇
 */
public interface ModuleModel {
    void login(String username, String password);

    void register(String username, String password, String repassword);
}
