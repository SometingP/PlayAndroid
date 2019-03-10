package com.example.corelib.configuration;

import android.content.Context;

public class ProjectInit {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getConfigs().put(ConfigKey.APPLICATIONCONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

   public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurator().getConfigurator(ConfigKey.APPLICATIONCONTEXT);
    }

    public static Object getConfig(ConfigKey key){
     return getConfigurator().getConfigurator(key);
    }
}
