package com.example.corelib.configuration;

import android.content.Context;

import java.util.HashMap;

/**
 * 基本配置类
 */
class Configurator {
    final HashMap<Object, Object> CONFIGS;

    private Configurator() {
        CONFIGS = new HashMap<>();
        CONFIGS.put(ConfigKey.CONFIGURATOR_STATE, false);
    }

    /**
     * 单例
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取配置集合
     *
     * @return
     */
    public HashMap<Object, Object> getConfigs() {
        if(checkConfigState()){
            return CONFIGS;
        }else{
            throw new NullPointerException( "CONFIGS IS NULL");
        }

    }

    /**
     * 获取单个配置
     *
     * @param key
     * @return
     */
    public Object getConfigurator(Object key) {
        if(checkConfigState()) {
            Object o = CONFIGS.get(key);
            return o;
        }else {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
    }

    /**
     * 配置IP
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigKey.HOST, host);
        return this;
    }

    public final Configurator withContext(Context context) {
        CONFIGS.put(ConfigKey.APPLICATIONCONTEXT, context);
        return this;
    }

    public final void configurator() {
        CONFIGS.put(ConfigKey.CONFIGURATOR_STATE, true);
    }

    private final boolean checkConfigState() {
        return (boolean) CONFIGS.get(ConfigKey.CONFIGURATOR_STATE);
    }

}
