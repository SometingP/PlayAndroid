package com.example.playandroid.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.corelib.entity.SearchDataEntity;
import com.example.playandroid.R;
import com.example.playandroid.search.adapter.SearchDataApapter;

/**
 * @author 热词以及搜索数据页面
 */
public class SearchDataActivity extends AppCompatActivity {
    private RecyclerView mSearchRecycler;
    private TextView mTitle;
    private SearchDataApapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data);
        SearchDataEntity datas = (SearchDataEntity) getIntent().getSerializableExtra("datas");
        mTitle = findViewById(R.id.title);
        mSearchRecycler = findViewById(R.id.search_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSearchRecycler.setLayoutManager(manager);
        adapter = new SearchDataApapter(this, datas.getDatas());
        mSearchRecycler.setAdapter(adapter);
    }

}
