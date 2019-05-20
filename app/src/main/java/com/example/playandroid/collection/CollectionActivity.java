package com.example.playandroid.collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.playandroid.R;

import org.greenrobot.eventbus.EventBus;

public class CollectionActivity extends AppCompatActivity implements CollectionView {

    private ImageView mCallBack;
    private RecyclerView mCollectionArticleList;
    private CollectionAdapter adapter;
    private CollectionPersenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        persenter = new CollectionPersenter(this);
        persenter.getCollectionArticleList();
        EventBus.getDefault().register(persenter);
        mCallBack = findViewById(R.id.collection_callback);
        mCollectionArticleList = findViewById(R.id.collection_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCollectionArticleList.setLayoutManager(manager);
    }

    @Override
    public void setCollectionArticleList(CollectionArticleEntity entity) {
        adapter = new CollectionAdapter(this, entity.getData().getDatas());
        mCollectionArticleList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(persenter);
    }
}
