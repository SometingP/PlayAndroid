package com.example.playandroid.fragment.knowledge;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.corelib.entity.KnowlegeEntity;
import com.example.playandroid.R;
import com.example.playandroid.fragment.knowledge.adapter.KnowlegeAdapter;

import org.greenrobot.eventbus.EventBus;


/**
 * 知识体系主页Frament
 * @author 彭翔宇
 */
public class KnowledgeFragment extends Fragment implements KnowlegeView {
    private RecyclerView recyclerView;
    private KnowlegeAdapter adapter;
    private KnowlegePersenter persenter;

    public KnowledgeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        persenter = new KnowlegePersenter(this);
        EventBus.getDefault().register(persenter);
        persenter.loadKnowlegeDataList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);
        recyclerView = view.findViewById(R.id.know_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new KnowlegeAdapter(getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void loadKnowlegeDataList(KnowlegeEntity entity) {
        adapter.setKnowlegeDataList(entity.getData());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(persenter);
    }
}
