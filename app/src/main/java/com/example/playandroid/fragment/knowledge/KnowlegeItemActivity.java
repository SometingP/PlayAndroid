package com.example.playandroid.fragment.knowledge;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.corelib.entity.KnowlegeEntity;
import com.example.playandroid.R;
import com.example.playandroid.fragment.knowledge.adapter.KnowItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识体系二级菜单
 * @author 彭翔宇
 */
public class KnowlegeItemActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private KnowlegeEntity.DataBean dataBean;
    private ViewPager mViewpager;
    private String[] tabNames ;
    private KnowItemAdapter adapter;
    private List<KnowView> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowlege_item);
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (KnowlegeEntity.DataBean) intent.getSerializableExtra("data");
        }
        tabNames = new String[dataBean.getChildren().size()];
        initDatas();
        mTabLayout = findViewById(R.id.tab_layout);
        mViewpager = findViewById(R.id.know_viewpager);
        adapter = new KnowItemAdapter(getSupportFragmentManager(), views, tabNames);
        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        views = new ArrayList<>();
        for (int i = 0; i < dataBean.getChildren().size(); i++) {
            tabNames[i] = dataBean.getChildren().get(i).getName();
            views.add(KnowView.getInstace(dataBean.getChildren().get(i).getId()));
        }
    }
}
