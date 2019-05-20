package com.example.playandroid.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.corelib.entity.ArticleEntity;
import com.example.corelib.entity.BannerEntity;
import com.example.corelib.view.banner.BannerView;
import com.example.corelib.view.refresh.MyRefreshLayout;
import com.example.playandroid.R;
import com.example.playandroid.fragment.main.adapter.ArticleListAdapter;
import com.example.playandroid.fragment.main.adapter.HeaderAndFooterWrapper;
import com.example.playandroid.fragment.main.adapter.viewholder.FooterViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * @author 首页Fragment
 */
public class MainFragment extends Fragment implements MainInterface, SwipeRefreshLayout.OnRefreshListener {
    private MainPersenter persent;
    private BannerView bannerView;
    private RecyclerView recyclerView;
    private int defultPage = 0;
    private ArticleListAdapter articleListAdapter;
    private MyRefreshLayout mRefreshLayout;
    private HeaderAndFooterWrapper mWrapper;
    private View footerView;

    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        persent = new MainPersenter(this);
        EventBus.getDefault().register(persent);
        persent.getBannerData();
        persent.getHomeArticlerList(defultPage);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initHeaderAndFooterView();
        mRefreshLayout = view.findViewById(R.id.refresh);
        mRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.recycler);
        mRefreshLayout.setRefreshing(true);
        return view;
    }

    /**
     * 初始化BannerView和底部上拉加载View
     */
    private void initHeaderAndFooterView() {
        View banView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout, null);
        bannerView = banView.findViewById(R.id.banner);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        layoutParams.setMargins(70, 100, 70, 0);
        bannerView.setLayoutParams(layoutParams);
        footerView = View.inflate(getActivity(), R.layout.footer_item, null);
        footerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
    }


    /**
     * 初始化配置RecyclerView
     * @param datas
     */
    private void initRecycler(List<ArticleEntity.DataBean.DatasBean> datas) {
        if (articleListAdapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            articleListAdapter = new ArticleListAdapter(getContext(), datas);
            mWrapper = new HeaderAndFooterWrapper(articleListAdapter);
            mWrapper.addHeaderView(bannerView);
            mWrapper.addFooterView(footerView);
            recyclerView.setAdapter(mWrapper);
            //监听RecyclerView是否到达最后
            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
                @Override
                public void onLoadMore() {
                    defultPage++;
                    mWrapper.setLoadingStatus(FooterViewHolder.LOADING);
                    persent.getHomeArticlerList(defultPage);
                }
            });
        } else {
            articleListAdapter.setArticleListDatas(datas);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (bannerView != null) {
            if (isVisibleToUser) {
                bannerView.startTraning();
            } else {
                bannerView.stopTraning();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(persent);
    }

    /**
     * 加载Banner图片以及title
     *
     * @param bannerEntity
     */
    @Override
    public void loadBannerData(BannerEntity bannerEntity) {
        bannerView.setDatas(bannerEntity.getData());
    }

    /**
     * 加载首页文章列表
     *
     * @param articleEntity
     */
    @Override
    public void loadHomeArticleList(ArticleEntity articleEntity) {
        mRefreshLayout.setRefreshing(false);
        initRecycler(articleEntity.getData().getDatas());
        mWrapper.setLoadingStatus(FooterViewHolder.LOADING_COMPLETE);
    }


    @Override
    public void onRefresh() {
        persent.getHomeArticlerList(defultPage);
    }

}
