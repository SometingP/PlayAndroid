package com.example.playandroid;

import android.os.Bundle;

import com.example.corelib.base.BaseActivity;

/**
 * @author 彭翔宇
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActivity(this);
    }
}
