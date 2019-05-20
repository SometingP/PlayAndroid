package com.example.playandroid.fragment.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.fragment.main.adapter.viewholder.FooterViewHolder;
import com.example.playandroid.fragment.main.adapter.viewholder.HeaderViewHolder;

public class HeaderAndFooterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SparseArray<View> headerViews = new SparseArray<>();
    private SparseArray<View> footerViews = new SparseArray<>();
    private static final int HEADER_VIEW_TYPE = 10000;
    private static final int FOOTER_VIEW_TYPE = 20000;
    private RecyclerView.Adapter adapter;
    private int status;
    private FooterViewHolder mFooterViewHolder;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return headerViews.keyAt(position);
        }
        if (isFooterViewPos(position)) {
            return footerViews.keyAt(position - getHeaderViewCount() - getReadItemCount());
        }

        return adapter.getItemViewType(position - getHeaderViewCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType) != null) {
            return new HeaderViewHolder(headerViews.get(viewType));
        }
        if (footerViews.get(viewType) != null) {
            mFooterViewHolder = new FooterViewHolder(footerViews.get(viewType));
            return mFooterViewHolder;
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) {
            return;
        }

        if (isFooterViewPos(position)) {
            mFooterViewHolder.setLoadingStatus(status);
            return;
        }

        adapter.onBindViewHolder(holder, position - getHeaderViewCount());
    }

    @Override
    public int getItemCount() {
        return getHeaderViewCount() + getReadItemCount() + getFooterViewCount();
    }

    public void addHeaderView(View view) {
        headerViews.append(headerViews.size() + HEADER_VIEW_TYPE, view);
    }

    public void addFooterView(View view) {
        footerViews.append(footerViews.size() + FOOTER_VIEW_TYPE, view);
    }

    private int getHeaderViewCount() {
        return headerViews.size();
    }

    private int getFooterViewCount() {
        return footerViews.size();
    }

    private int getReadItemCount() {
        return adapter.getItemCount();
    }

    private boolean isHeaderViewPos(int position) {
        return position <= 0;
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeaderViewCount() + getReadItemCount();
    }

    public void setLoadingStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }
}
