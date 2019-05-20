package com.example.playandroid.fragment.knowledge.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.fragment.knowledge.KnowView;

import java.util.List;

/**
 * 知识体系二级菜单适配器
 * @author 彭翔宇
 */
public class KnowItemAdapter extends FragmentPagerAdapter {
    private List<KnowView> views;
    private String[] tabNames;

    public KnowItemAdapter(FragmentManager fm, List<KnowView> views, String[] tabNames) {
        super(fm);
        this.views = views;
        this.tabNames = tabNames;
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
        return tabNames[position];
    }
}
