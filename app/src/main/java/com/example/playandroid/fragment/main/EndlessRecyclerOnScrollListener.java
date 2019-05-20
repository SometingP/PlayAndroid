package com.example.playandroid.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView滑动监听
 * @author 13973
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    //用来标记是否正在向上滑动
    private boolean isSlidingUpward = false;
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //滑动停止
            int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();
            if(lastPosition ==(itemCount-1) && isSlidingUpward){
               onLoadMore();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isSlidingUpward = dy>0;
    }

    public abstract void onLoadMore();
}
