package com.example.corelib.view.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 自定义下拉刷新控件
 *
 * @author 彭翔宇
 */
public class MyRefreshLayout extends SwipeRefreshLayout {
    private boolean mIsDragger = false;
    private float startX;
    private float startY;
    private int mTouchSlop;

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = getX();
                startY = getY();
                mIsDragger = false;

                break;
            case MotionEvent.ACTION_MOVE:
                if(mIsDragger){
                    return false;
                }
                float endX = getX();
                float endY= getY();
                float x = Math.abs(endX-startX);
                float y = Math.abs(endY-startY);
                if(x>y&&x>mTouchSlop){
                    mIsDragger = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                mIsDragger = false;
                break;
            default:
                break;
        }
        return super.onInterceptHoverEvent(event);
    }
}
