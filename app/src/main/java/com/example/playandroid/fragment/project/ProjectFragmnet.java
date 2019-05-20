package com.example.playandroid.fragment.project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目分类页面
 * @author 彭翔宇
 */
public class ProjectFragmnet extends Fragment implements ProjectInterface {
    private TabLayout mTabTitles;
    private ViewPager mViewPager;
    private ProjectPersenter persenter;
    private   List<ProjectView> views;
    private ProjectAdapter adapter;

    public ProjectFragmnet() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new ArrayList<>();
        persenter = new ProjectPersenter(this);
        EventBus.getDefault().register(persenter);
        persenter.getProjectTitles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        mTabTitles = view.findViewById(R.id.tablayout);
        mViewPager = view.findViewById(R.id.viewpager);
        mTabTitles.setupWithViewPager(mViewPager);
        return view;
    }

    /**
     * 获取项目分类数据
     */
    @Override
    public void setProjectTitles(ProjectTitleEntity entity) {
      
        for (int i = 0; i < entity.getData().size(); i++) {
            views.add(ProjectView.newInstance(entity.getData().get(i).getId()));
        }
        adapter = new ProjectAdapter(getChildFragmentManager(), entity.getData(), views);
        mViewPager.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(persenter);
    }
}
