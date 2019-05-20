package com.example.corelib.view.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.corelib.R;
import com.example.corelib.entity.BannerEntity;

import java.util.List;

/**
 * 自定义轮播图banner控件
 *
 * @author 彭翔宇
 */
public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private BannerAdapter adapter;
    private Context context;
    private boolean isData;
    //当前位置
    private int mCurrentPosition;
    //是否手动滑动
    private boolean isTouch = false;

    public BannerView(Context context) {
        this(context, null);
    }


    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }


    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化Viewpager
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_banner, null);
        mViewPager = view.findViewById(R.id.banner);
        mViewPager.setOnPageChangeListener(this);
        addView(view);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 设置Adapter数据
     *
     * @param datas
     */
    public void setDatas(List<BannerEntity.DataBean> datas) {
        isData = true;
        if (adapter == null) {
            adapter = new BannerAdapter(context, datas);
        }
        mViewPager.setAdapter(adapter);
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                    case MotionEvent.ACTION_MOVE:
                        isTouch = true;
                        stopTraning();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isTouch = false;
                        startTraning();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        int middle = adapter.getCount() / 2;
        mViewPager.setCurrentItem(middle - middle % datas.size());
        startTraning();
    }


    public void stopTraning() {
        this.removeCallbacks(runnable);
    }

    public void startTraning() {
        this.removeCallbacks(runnable);
        this.postDelayed(runnable, 2000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
           if(!isTouch){
               int currentItem = mViewPager.getCurrentItem();
               currentItem++;
               mViewPager.setCurrentItem(currentItem,true);
               startTraning();
           }
        }
    };
}
