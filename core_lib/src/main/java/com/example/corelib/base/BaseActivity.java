package com.example.corelib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity管理类
 * @author 彭翔宇
 */
public class BaseActivity extends AppCompatActivity {

    List<WeakReference<Activity>> activitys = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitys = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activitys.add(new WeakReference<>(activity));
    }

    /**
     * 关闭指定Activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activitys.remove(new WeakReference<>(activity));
    }

    /**
     * 关闭所有Activity
     */
    public void removeActivitys() {
        for (int i = 0; i < activitys.size(); i++) {
            activitys.remove(i).get().finish();
        }
    }

    /**
     * 退出应用
     *
     * @param context
     */
    public void exitApp(Context context) {
        removeActivitys();
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.killBackgroundProcesses(context.getPackageName());
    }
}
