package com.example.playandroid.fragment.project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 项目分类页面数据适配器
 * @author pengxiangyu
 * @date 2019/4/9
 */

public class ProjectAdapter extends FragmentPagerAdapter {
    private List<ProjectTitleEntity.DataBean> titles;
    private List<ProjectView> views;

    public ProjectAdapter(FragmentManager fm,List<ProjectTitleEntity.DataBean> titles, List<ProjectView> views) {
        super(fm);
        this.titles = titles;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }
}
