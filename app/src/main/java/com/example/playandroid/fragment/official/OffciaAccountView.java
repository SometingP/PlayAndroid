package com.example.playandroid.fragment.official;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.corelib.entity.OffciaAccountEntity;
import com.example.playandroid.R;


/**
 * 个人公众号页面
 * @author 彭翔宇
 */
public class OffciaAccountView extends Fragment {
    private int id;
    private OffciaAccountItemoModel model;
    private RecyclerView mRecycler;
    private OffciaAccountItemAdapter adapter;
    private EditText mSearchEdit;
    private Button mSearchBtn;

    public static OffciaAccountView getInstace(int id) {
        OffciaAccountView offciaAccountView = new OffciaAccountView();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        offciaAccountView.setArguments(bundle);
        return offciaAccountView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
        model = new OffciaAccountItemoModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offcia_account_item, container, false);
        mRecycler = view.findViewById(R.id.recycler);
        mSearchEdit = view.findViewById(R.id.edit_search);
        mSearchBtn = view.findViewById(R.id.btn_search);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        adapter = new OffciaAccountItemAdapter(getActivity());
        mRecycler.setAdapter(adapter);
        initOnClickListener();
        initMethod();
        return view;
    }

    /**
     * 设置搜索按钮点击事件
     */
    private void initOnClickListener() {
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mSearchEdit.getText().toString().trim();
                if (text != null && !"".equals(text)) {
                    /**
                     * 搜索历史文章
                     */
                    model.searchOffciaArticle(id, text, new OffciaListener() {
                        @Override
                        public void onFailure(String message) {
                        }

                        @Override
                        public void onSucceed(final OffciaAccountEntity entity) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setOffciaRecyclerDatas(entity.getData().getDatas());
                                }
                            });
                        }
                    });
                    mSearchEdit.getText().clear();
                }
            }
        });
    }

    /**
     * 获取指定ID公众号的历史数据
     */
    private void initMethod() {
        model.getOffCiaAccountDatas(id, new OffciaListener() {
            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onSucceed(final OffciaAccountEntity entity) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setOffciaRecyclerDatas(entity.getData().getDatas());
                    }
                });
            }
        });
    }
}
