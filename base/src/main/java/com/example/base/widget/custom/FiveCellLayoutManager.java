package com.example.base.widget.custom;

import android.view.View;

public class FiveCellLayoutManager extends BaseLayoutManager {
    public FiveCellLayoutManager(float mAspect) {
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

        x = x - view.getMeasuredWidth() - cellPadding;
        y = y + view.getMeasuredHeight() + cellPadding;
        view = layout.getChildAt(3);
        view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());

        x = x + view.getMeasuredWidth() + cellPadding;
        view = layout.getChildAt(4);
        view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
    }

    @Override
    public void measureChildView(AbNormalLayout parent, View view, int cellPadding) {
        int width = parent.getMeasuredWidth();
        int height = parent.getMeasuredHeight();
        width -= 2 * cellPadding + parent.getPaddingLeft() + parent.getPaddingRight();
        height -= parent.getPaddingBottom() + parent.getPaddingTop() - cellPadding;
        measure(view, width, height);
    }
}
