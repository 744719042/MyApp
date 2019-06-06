package com.example.base.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.example.base.R;

public class TwoCellLayoutManager extends BaseLayoutManager {
    @Override
    public int onMeasure(int width, Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AbNormalLayout);
            mAspect = array.getFloat(R.styleable.AbNormalLayout_twoCardsAspect, mAspect);
            array.recycle();
        } else {
            mAspect = 0.5f;
        }
        return (int) (width * mAspect);
    }

    @Override
    public void onLayout(AbNormalLayout layout, int cellPadding) {
        int paddingTop = layout.getPaddingTop();
        int paddingLeft = layout.getPaddingLeft();

        View view = layout.getChildAt(0);
        int x = paddingLeft, y = paddingTop;
        view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());

        x = x + view.getMeasuredWidth() + cellPadding;
        view = layout.getChildAt(1);
        view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
    }

    @Override
    public void measureChildView(AbNormalLayout parent, View view, int cellPadding) {
        int width = parent.getMeasuredWidth();
        int height = parent.getMeasuredHeight();
        width -= cellPadding + parent.getPaddingLeft() + parent.getPaddingRight();
        height -= parent.getPaddingBottom() + parent.getPaddingTop();
        measure(view, width, height);
    }
}
