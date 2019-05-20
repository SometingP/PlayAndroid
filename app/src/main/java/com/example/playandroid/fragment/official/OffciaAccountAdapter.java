package com.example.playandroid.fragment.official;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.corelib.entity.OffcialAccountTitleEntity;

import java.util.List;

/**
 * 公众号列表数据适配器
 * @author 彭翔宇
 */
public class OffciaAccountAdapter extends FragmentPagerAdapter {
    private List<OffcialAccountTitleEntity.DataBean> datas;
    private List<OffciaAccountView> accountViews;

    public OffciaAccountAdapter(FragmentManager fm, List<OffcialAccountTitleEntity.DataBean> datas, List<OffciaAccountView> accountViews) {
        super(fm);
        this.datas = datas;
        this.accountViews = accountViews;
    }

    @Override
    public Fragment getItem(int position) {
        return accountViews.get(position);
    }

    @Override
    public int getCount() {
        return accountViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getName();
    }
}
