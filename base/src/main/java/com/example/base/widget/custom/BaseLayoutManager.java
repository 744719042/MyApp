package com.example.base.widget.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.utils.NumUtils;

public abstract class BaseLayoutManager implements ILayoutManager {
    protected float mAspect;

    public BaseLayoutManager(float mAspect) {
        this.mAspect = mAspect;
    }

    @Override
    public int onMeasure(int width, Context context) {
        return (int) (width * mAspect);
    }

    protected void measure(View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof AbNormalLayout.LayoutParams)) {
            throw new IllegalArgumentException("child layoutParam is not AbNormalLayout.LayoutParams");
        }

        AbNormalLayout.LayoutParams params = (AbNormalLayout.LayoutParams) layoutParams;
        params.width = (int) (width * params.mWidthRatio);
        if (!NumUtils.equals(params.mAspect, 0f)) { // 如果设置了aspect
            params.height = (int) (params.width * params.mAspect);
        } else {
            params.height = (int) (height * params.mHeightRatio);
        }

        view.measure(View.MeasureSpec.makeMeasureSpec(params.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(params.height, View.MeasureSpec.EXACTLY));
    }
}
