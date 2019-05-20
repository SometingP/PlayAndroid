package com.example.corelib.view.flow;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义流式布局
 *
 * @author 彭翔宇
 */
public class FlowLayout extends ViewGroup {
    private MotionEvent mMotionEvent;
    private OnTipClickListener onTipClickListener;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * ViweGroup默认不处理margin属性，使用MarginLayouParams
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        int tempWidth = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 根据流式布局规则测量FlowLayout的宽高
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            //测量子View的宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            //获取子Viwe的宽高
            int childWidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            //判断子View宽度累加是否大于布局宽度如果大于则换行，并记录最大容器的宽高
            if (tempWidth + childWidth > widthSize) {
                measureHeight += childHeight;
                measureWidth = Math.max(tempWidth, widthSize);
                tempWidth = childWidth;
            } else {
                tempWidth += childWidth;
                measureHeight = Math.max(measureHeight, childHeight);
                //如果所有子View宽度加起来都没有达到布局的宽度
                // 则直接将累加的宽度设置成容器的最大宽度
                if (i == getChildCount() - 1) {
                    measureWidth = Math.max(tempWidth, measureWidth);
                }
            }
        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : measureWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : measureHeight);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            MarginLayoutParams margin = (MarginLayoutParams) childView.getLayoutParams();
            if (i == 0) {
                left += margin.leftMargin;
                top += margin.topMargin-128 ;
            }
            right = left + childView.getMeasuredWidth();
            bottom = top + childView.getMeasuredHeight();
            if (right > getMeasuredWidth()) {
                left = margin.leftMargin;
                top = bottom + margin.bottomMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
            }
            childView.layout(left, top, right, bottom);
            left = right + margin.rightMargin;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mMotionEvent = MotionEvent.obtain(event);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        int x = (int) mMotionEvent.getX();
        int y = (int) mMotionEvent.getY();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Rect rect = new Rect();
            childAt.getHitRect(rect);

            if (rect.contains(x, y)) {
                return onTipClickListener.getOnClickTipView(i);
            }
        }
        return super.performClick();
    }

    public void setOnTipClickListener(OnTipClickListener onTipClickListener) {
        this.onTipClickListener = onTipClickListener;
        setClickable(true);
    }

}
