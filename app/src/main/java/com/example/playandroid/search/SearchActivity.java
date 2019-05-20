package com.example.playandroid.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corelib.config.DataConfig;
import com.example.corelib.entity.HotKeysEntity;
import com.example.corelib.entity.SearchDataEntity;
import com.example.corelib.view.flow.FlowLayout;
import com.example.corelib.view.flow.OnTipClickListener;
import com.example.playandroid.R;
import com.example.playandroid.search.persenter.SearchPersenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 热搜页面
 *
 * @author 13973
 */
public class SearchActivity extends AppCompatActivity implements SearchInterface, View.OnClickListener {
    private ImageView mCallback;
    private LinearLayout mLinear, mHosityClear;
    private EditText mSearchEdit;
    private Button mSearchBtn;
    private FlowLayout mFlow;
    private int mX;
    private int mY;
    private SearchPersenter persenter;
    private List<HotKeysEntity.DataBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mX = getIntent().getIntExtra("x", 0);
        mY = getIntent().getIntExtra("y", 0);
        persenter = new SearchPersenter(this);
        EventBus.getDefault().register(persenter);
        initViews();
    }

    private void initViews() {
        mCallback = findViewById(R.id.callback);
        mLinear = findViewById(R.id.linear);
        mHosityClear = findViewById(R.id.hosity_clear);
        mSearchBtn = findViewById(R.id.search_btn);
        mSearchEdit = findViewById(R.id.search_edit);
        mFlow = findViewById(R.id.flow_layout);

        mCallback.setOnClickListener(this);
        mHosityClear.setOnClickListener(this);
        mSearchBtn.setOnClickListener(this);

        mLinear.post(new Runnable() {
            @Override
            public void run() {
                persenter.getHotkeys();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = createRevealAnimator(false, mX, mY);
                    animator.start();
                }
            }
        });

        mFlow.setOnTipClickListener(new OnTipClickListener() {
            @Override
            public boolean getOnClickTipView(int postion) {
                String name = datas.get(postion).getName();
                if(!DataConfig.searchHosityMap.contains(name)) {
                    DataConfig.searchHosityMap.add(name);
                }
                persenter.getSearchData(name);
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator createRevealAnimator(boolean flag, int mX, int mY) {
        float hypot = (float) Math.hypot(mLinear.getWidth(), mLinear.getHeight());
        float startRadius = flag ? hypot : 0;
        float endRadius = flag ? 0 : hypot;
        Animator animator = ViewAnimationUtils.createCircularReveal(mLinear, mX, mY, startRadius, endRadius);
        if (flag) {
            animator.addListener(animatorListener);
        }
        animator.setDuration(600);
        return animator;
    }

    private Animator.AnimatorListener animatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            mLinear.setVisibility(View.GONE);
            finish();
            overridePendingTransition(0, 0);
        }
    };

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(true, mX, mY);
            animator.start();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callback:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = createRevealAnimator(true, mX, mY);
                    animator.start();
                }
                break;
            case R.id.hosity_clear:
                break;
            case R.id.search_btn:
                if (!mSearchEdit.getText().toString().equals("") && mSearchEdit.getText().toString() != null) {
                    String search = mSearchEdit.getText().toString();
                    if(!DataConfig.searchHosityMap.contains(search)) {
                        DataConfig.searchHosityMap.add(search);
                    }
                    persenter.getSearchData(search);
                    mSearchEdit.getText().clear();
                }
                break;
            default:
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void setHotKeys(HotKeysEntity entity) {
        datas = entity.getData();
        for (int i = 0; i < datas.size(); i++) {
            TextView textView = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(datas.get(i).
                    getName().length() * 40, 70);
            params.setMargins(0, 0, 0, 0);
            params.setMargins(20, 20, 20, 20);
            textView.setLayoutParams(params);
            textView.setText(datas.get(i).getName());
            textView.setTextSize(18f);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(R.color.lightslategray);
            textView.setTextColor(R.color.white);
            mFlow.addView(textView);
        }
    }

    @Override
    public void setSearchDatas(SearchDataEntity entity) {
        if (entity.getDatas() == null) {
            Toast.makeText(SearchActivity.this, "获取数据列表失败！", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(SearchActivity.this, SearchDataActivity.class);
            intent.putExtra("datas", entity);
            startActivity(intent);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(persenter);
    }
}
