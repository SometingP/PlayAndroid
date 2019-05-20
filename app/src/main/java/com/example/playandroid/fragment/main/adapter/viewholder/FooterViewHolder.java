package com.example.playandroid.fragment.main.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.playandroid.R;


/**
 * 上拉等待层View
 * @author 彭翔宇
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {
    //加载中
    public static final int LOADING = 0;
    // 加载完成
    public static final int LOADING_COMPLETE = 2;
    public static final int END = 1;

    ProgressBar pbLoading;
    TextView tvLoading;
    LinearLayout llEnd;

    public FooterViewHolder(View itemView) {
        super(itemView);
        pbLoading = itemView.findViewById(R.id.pb_loading);
        tvLoading = itemView.findViewById(R.id.tv_loading);
        llEnd = itemView.findViewById(R.id.ll_end);
    }

    public void setLoadingStatus(int status) {
        switch (status) {
            // 正在加载
            case LOADING:
                pbLoading.setVisibility(View.VISIBLE);
                tvLoading.setVisibility(View.VISIBLE);
                llEnd.setVisibility(View.GONE);
                break;
            // 加载完成
            case LOADING_COMPLETE:
                pbLoading.setVisibility(View.INVISIBLE);
                tvLoading.setVisibility(View.INVISIBLE);
                llEnd.setVisibility(View.GONE);
                break;
            // 加载到底
            case END:
                pbLoading.setVisibility(View.GONE);
                tvLoading.setVisibility(View.GONE);
                llEnd.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
