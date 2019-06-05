package com.example.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.base.R;

/**
 * 带圆角的图片
 */
public class RoundCornerImageView extends AppCompatImageView {
    private float mXRadius;
    private float mYRadius;
    private Paint mPaint;
    private BitmapShader mShader;
    private RectF mBounds;
    private BitmapDrawable mBitmapDrawable;

    public RoundCornerImageView(Context context) {
        this(context, null);
    }

    public RoundCornerImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundCornerImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
            mXRadius = array.getDimension(R.styleable.RoundCornerImageView_xRadius, 0);
            mYRadius = array.getDimension(R.styleable.RoundCornerImageView_yRadius, 0);
           array.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() instanceof BitmapDrawable) {
            if (mBitmapDrawable != getDrawable()) {
                mShader = null;
            }
            mBitmapDrawable = (BitmapDrawable) getDrawable();
            Bitmap bitmap = mBitmapDrawable.getBitmap();
            if (mBounds == null || mShader == null) {
                mBounds = new RectF(0, 0, getWidth(), getHeight());
                mShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                mPaint.setShader(mShader);
            }

            canvas.drawRoundRect(mBounds, mXRadius, mYRadius, mPaint);
        } else {
            super.onDraw(canvas);
        }
    }
}
