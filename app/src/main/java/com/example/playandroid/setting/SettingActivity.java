package com.example.playandroid.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.playandroid.R;

/**
 * 设置页面
 *
 * @author 彭翔宇
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mRelaCache, mRelaModel, mRelaClear;
    private CheckBox mCacheBox, mModelBox;
    private TextView mCacheSize;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedPreferences = this.getSharedPreferences("status", Context.MODE_PRIVATE);
        initViews();
    }

    private void initViews() {
        mRelaCache = findViewById(R.id.automatic_cache);
        mRelaModel = findViewById(R.id.planless_model);
        mRelaClear = findViewById(R.id.clear_cache);

        mCacheBox = findViewById(R.id.cache_checkbox);
        mModelBox = findViewById(R.id.planless_checkbox);
        mCacheSize = findViewById(R.id.cache_sezi);

        mCacheBox.setChecked(sharedPreferences.getBoolean("cache_statu", false));
        mModelBox.setChecked(sharedPreferences.getBoolean("model_statu", false));

        mRelaCache.setOnClickListener(this);
        mRelaModel.setOnClickListener(this);
        mRelaClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.automatic_cache:
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (mCacheBox.isChecked()) {
                    edit.putBoolean("cache_statu", false);
                    mCacheBox.setChecked(false);
                } else {
                    edit.putBoolean("cache_statu", true);
                    mCacheBox.setChecked(true);
                }
                edit.commit();
                break;
            case R.id.planless_model:
                SharedPreferences.Editor shEdit = sharedPreferences.edit();
                if (mModelBox.isChecked()) {
                    shEdit.putBoolean("model_statu", false);
                    mModelBox.setChecked(false);
                } else {
                    shEdit.putBoolean("model_statu", true);
                    mModelBox.setChecked(true);
                }
                shEdit.commit();
                break;
            case R.id.clear_cache:
                break;
            default:
                break;
        }

    }
}
