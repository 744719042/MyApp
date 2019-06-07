package com.example.base.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface ILayoutManager {
    int onMeasure(int width, Context context);
    void onLayout(AbNormalLayout layout, int cellPadding);
    void measureChildView(AbNormalLayout parent, View view, int cellPadding);
}
