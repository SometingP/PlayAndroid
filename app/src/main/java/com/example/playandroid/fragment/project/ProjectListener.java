package com.example.playandroid.fragment.project;

/**
 * @author pengxiangyu
 * @date 2019/4/9
 */

public interface ProjectListener {
    void onSuccess(ProjectEntity entity);
    void onFail(String errorMsg);
}
