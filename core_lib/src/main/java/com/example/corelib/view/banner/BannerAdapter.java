package com.example.corelib.view.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.corelib.R;
import com.example.corelib.entity.BannerEntity;
import java.util.List;

/**
 * banner适配器
 *
 * @author 彭翔宇
 * @data 2019/03/15
 */
public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<BannerEntity.DataBean> datas;

    public BannerAdapter(Context context, List<BannerEntity.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 返回Viewpage显示多少页
     *
     * @return
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 构建显示View并设置数据以及添加到容器中，最后返回
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        ImageView bannerImageView = view.findViewById(R.id.banner_image);
        BannerEntity.DataBean dataBean = datas.get(position % datas.size());
        TextView mMessage = view.findViewById(R.id.banner_message);
        TextView mNumber = view.findViewById(R.id.number);
        mMessage.setText(dataBean.getTitle());
        mNumber.setText((position % datas.size() + 1) + "/" + datas.size());
        Glide.with(context).load(dataBean.getImagePath()).into(bannerImageView);
        container.addView(view);
        return view;
    }

    /**
     * 销毁条目
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
