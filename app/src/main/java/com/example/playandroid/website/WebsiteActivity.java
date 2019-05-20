package com.example.playandroid.website;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.corelib.entity.WebsiteEntity;
import com.example.corelib.view.flow.FlowLayout;
import com.example.corelib.view.flow.OnTipClickListener;
import com.example.playandroid.ArticleActivity;
import com.example.playandroid.R;
import com.example.playandroid.website.persenter.WebsitePersenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 常用网站页面
 * @author 彭翔宇
 */
public class WebsiteActivity extends AppCompatActivity implements WebsiteInterface {
    private LinearLayout mLinear;
    private ImageView mCallBack;
    private FlowLayout mFlow;
    private int mX;
    private int mY;
    private WebsitePersenter persenter;
    private List<WebsiteEntity.DataBean> websites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        mX = getIntent().getIntExtra("x", 0);
        mY = getIntent().getIntExtra("y", 0);
        persenter = new WebsitePersenter(this);
        EventBus.getDefault().register(persenter);
        initViews();
    }

    private void initViews() {
        mLinear = findViewById(R.id.linear);
        mCallBack = findViewById(R.id.callback);
        mFlow = findViewById(R.id.flow);
        mLinear.post(new Runnable() {
            @Override
            public void run() {
                persenter.getWebsiteDatas();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = createRevealAnimator(false, mX, mY);
                    animator.start();
                }
            }
        });
        mFlow.setOnTipClickListener(new OnTipClickListener() {
            @Override
            public boolean getOnClickTipView(int postion) {
                Intent intent = new Intent(WebsiteActivity.this, ArticleActivity.class);
                intent.putExtra("url", websites.get(postion).getLink());
                intent.putExtra("title",websites.get(postion).getName());
                startActivity(intent);
                return true;
            }
        });
        mCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = createRevealAnimator(true, mX, mY);
                    animator.start();
                } else {
                    finish();
                }
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void setWebsites(WebsiteEntity entity) {
        websites = entity.getData();
        for (int i = 0; i < websites.size(); i++) {
            TextView textView = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(websites.get(i).
                    getName().length() * 40, 70);
            params.setMargins(20, 20, 20, 20);
            textView.setLayoutParams(params);
            textView.setText(websites.get(i).getName());
            textView.setTextSize(18f);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(R.color.lightslategray);
            textView.setTextColor(R.color.white);
            mFlow.addView(textView);
        }
    }
}
