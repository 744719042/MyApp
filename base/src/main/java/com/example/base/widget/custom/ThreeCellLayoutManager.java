package com.example.base.widget.custom;

import android.view.View;

public class ThreeCellLayoutManager extends BaseLayoutManager {

    public ThreeCellLayoutManager(float mAspect) {
        super(mAspect);
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

        x = x + view.getMeasuredWidth() + cellPadding;
        view = layout.getChildAt(2);
        view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
    }

    @Override
    public void measureChildView(AbNormalLayout parent, View view, int cellPadding) {
        int width = parent.getMeasuredWidth();
        int height = parent.getMeasuredHeight();
        width -= 2 * cellPadding + parent.getPaddingLeft() + parent.getPaddingRight();
        height -= parent.getPaddingBottom() + parent.getPaddingTop();
        measure(view, width, height);
    }
}
