package com.example.corelib.util;

import android.content.Context;

/**
 * 缓存工具类  用于获取应用缓存、清理应用缓存
 *
 * @author 彭翔宇
 */
public class CacheUtils {
    private static CacheUtils instance;

    private CacheUtils() {
    }

    public static CacheUtils getInstance() {
        if (instance == null) {
            synchronized (CacheUtils.class) {
                if (instance == null) {
                    instance = new CacheUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取应用缓存大小
     * @param context
     * @return
     */
    public String getAppCacheSize(Context context) {


        return null;
    }

    /**
     * 清理应用缓存
     * @param context
     * @return
     */
    public boolean clearAppCache(Context context) {
        return true;
    }
}
