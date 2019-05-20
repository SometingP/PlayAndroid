package com.example.playandroid.fragment.official;


import com.example.corelib.entity.OffciaAccountEntity;

/**
 * 个人公众号请求回调接口
 * @author 彭翔宇
 */
public interface OffciaListener {
    void onFailure(String message);
    void onSucceed(OffciaAccountEntity entity);
}
