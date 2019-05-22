package com.example.playandroid.fragment.official;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.corelib.entity.OffcialAccountTitleEntity;
import com.example.playandroid.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * 公众号列表页面
 * @author 彭翔宇
 */
public class OffcialFragment extends Fragment implements OffcialView {
    private TabLayout mTabTitles;
    private ViewPager mAccounts;
    private OffciaPersneter persneter;
    private List<OffciaAccountView> views;
    private OffciaAccountAdapter adapter;

    public OffcialFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        persneter = new OffciaPersneter(this);
        EventBus.getDefault().register(persneter);
        persneter.getOffciaAccountTitles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offcial, container, false);
        mTabTitles = view.findViewById(R.id.tablayout);
        mAccounts = view.findViewById(R.id.viewpager);
        mTabTitles.setupWithViewPager(mAccounts);
        return view;
    }

    /**
     * 获取公众号列表
     * @param entity
     */
    @Override
    public void setOffcialAccountTitltes(OffcialAccountTitleEntity entity) {
        views = new ArrayList<>();
        for (int i = 0; i < entity.getData().size(); i++) {
            views.add(OffciaAccountView.getInstace(entity.getData().get(i).getId()));
        }
        adapter = new OffciaAccountAdapter(getChildFragmentManager(),entity.getData(), views);
        mAccounts.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(persneter);
    }
}
