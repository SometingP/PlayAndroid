package com.example.playandroid.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.corelib.base.BaseActivity;
import com.example.playandroid.BottomNavigationHelper;
import com.example.playandroid.R;
import com.example.playandroid.setting.MyFragment;
import com.example.playandroid.fragment.project.ProjectFragmnet;
import com.example.playandroid.search.SearchActivity;
import com.example.playandroid.website.WebsiteActivity;
import com.example.playandroid.adapter.FragmentAdapter;
import com.example.playandroid.fragment.knowledge.KnowledgeFragment;
import com.example.playandroid.fragment.official.OffcialFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用主界面
 * @author 彭翔宇
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, View.OnClickListener {
    private BottomNavigationView mBottomNavigationView;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private Toolbar mToolBar;
    private TextView mTitle;
    private ImageView mCommonWebsite, mSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActivity(this);
        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new OffcialFragment());
        fragments.add(new ProjectFragmnet());
        fragments.add(new MyFragment());
        initViews();
    }


    private void initViews() {
        mToolBar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.title);
        viewPager = findViewById(R.id.viewpager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mCommonWebsite = findViewById(R.id.common_website);
        mSearch = findViewById(R.id.search_hot);
        mCommonWebsite.setOnClickListener(this);
        mSearch.setOnClickListener(this);

        mToolBar.setTitle("");
        mTitle.setText("首页");
        setSupportActionBar(mToolBar);
        BottomNavigationHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                setTitles(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.knowledge:
                setTitles(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.offcial:
                setTitles(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.project:
                setTitles(3);
                viewPager.setCurrentItem(3);
                break;
            case R.id.my:
                setTitles(4);
                viewPager.setCurrentItem(4);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setTitles(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setTitles(int postion) {
        switch (postion) {
            case 0:
                mTitle.setText("首页");
                break;
            case 1:
                mTitle.setText("知识体系");
                break;
            case 2:
                mTitle.setText("公众号");
                break;
            case 3:
                mTitle.setText("项目");
                break;
            case 4:
                mTitle.setText("我的");
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.common_website:
                intent = new Intent(MainActivity.this, WebsiteActivity.class);
                intent.putExtra("x", (mCommonWebsite.getLeft() + mCommonWebsite.getRight()) / 2);
                intent.putExtra("y", (mCommonWebsite.getTop() + mCommonWebsite.getBottom()) / 2);
                startActivity(intent);
                break;
            case R.id.search_hot:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("x", (mSearch.getLeft() + mSearch.getRight()) / 2);
                intent.putExtra("y", (mSearch.getTop() + mSearch.getBottom()) / 2);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
