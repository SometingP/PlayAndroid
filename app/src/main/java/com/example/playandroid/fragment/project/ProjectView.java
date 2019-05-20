package com.example.playandroid.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.R;

/**
 * Created by pengxiangyu on 2019/4/9.
 */

public class ProjectView extends Fragment {
    private int cid;
    private RecyclerView mRecycler;
    private ProjectViewAdapter adapter;
    private ProjectViewModel model;

    public static ProjectView newInstance(int cid) {
        Log.e("TAG","CID:  "+cid);
        Bundle args = new Bundle();
        ProjectView fragment = new ProjectView();
        args.putInt("cid", cid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        cid = arguments.getInt("cid");
        model = new ProjectViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_view, container, false);
        mRecycler = view.findViewById(R.id.prject_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        adapter = new ProjectViewAdapter(getActivity());
        mRecycler.setAdapter(adapter);
        model.getProjectDatas(cid, new ProjectListener() {
            @Override
            public void onSuccess(final ProjectEntity entity) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setProjectDatas(entity.getData().getDatas());
                    }
                });

            }

            @Override
            public void onFail(String errorMsg) {

            }
        });
        return view;
    }
}
