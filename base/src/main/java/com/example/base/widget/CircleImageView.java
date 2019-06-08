package com.example.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.base.R;
import com.example.base.utils.UIUtils;

/**
 * Created by Administrator on 2018/1/30.
 */

public class CircleImageView extends AppCompatImageView {
    private int mCircleWidth;
    private int mCircleColor;
    private BitmapShader mShader;
    private Paint mBitmapPaint;
    private Paint mOuterPaint;
    private BitmapDrawable mDrawable;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
            mCircleWidth = (int) array.getDimension(R.styleable.CircleImageView_circle_width, UIUtils.dp2px(2));
            mCircleColor = array.getColor(R.styleable.CircleImageView_circle_color, Color.WHITE);
            array.recycle();
        }

        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setDither(true);
        mOuterPaint.setColor(mCircleColor);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeWidth(mCircleWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() instanceof BitmapDrawable) {
            if (mDrawable != getDrawable()) {
                mDrawable = ((BitmapDrawable) getDrawable());
                mShader = new BitmapShader(mDrawable.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                mBitmapPaint.setShader(mShader);
            }
            int radius = Math.min(getWidth() / 2, getHeight() /2) - mCircleWidth;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mOuterPaint);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mBitmapPaint);
        } else {
            super.onDraw(canvas);
        }
    }
}
