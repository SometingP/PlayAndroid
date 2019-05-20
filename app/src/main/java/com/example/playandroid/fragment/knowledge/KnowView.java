package com.example.playandroid.fragment.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.corelib.entity.ArticleEntity;
import com.example.playandroid.R;
import com.example.playandroid.fragment.knowledge.adapter.KnowItemRecyclerAdapter;
import com.example.playandroid.fragment.knowledge.model.KnowListener;
import com.example.playandroid.fragment.knowledge.model.KnowledageItemModel;

/**
 * 知识体系二级菜单页面
 * @author 彭翔宇
 */
public class KnowView extends Fragment {
    private int id;
    private RecyclerView mRecycler;
    private KnowItemRecyclerAdapter adapter;
    private KnowledageItemModel model;

    public static KnowView getInstace(int id) {
        KnowView knowView = new KnowView();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        knowView.setArguments(bundle);
        return knowView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
        model = new KnowledageItemModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.knowlege_item_fragment, container, false);
        mRecycler = view.findViewById(R.id.know_item_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        adapter = new KnowItemRecyclerAdapter(getActivity());
        mRecycler.setAdapter(adapter);
        model.getKnowledgeItemList(id, new KnowListener() {
            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onSucceed(final ArticleEntity entity) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setKnowItemRecyclerDatas(entity.getData().getDatas());
                    }
                });
            }
        });
        return view;
    }
}
