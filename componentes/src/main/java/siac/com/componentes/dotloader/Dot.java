package siac.com.componentes.dotloader;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import siac.com.componentes.DotLoader;

/**
 * Created by Bhargav on 7/20/2016.
 */
public class Dot {

    private Paint mPaint;
    public int mCurrentColorIndex;
    private int mDotRadius;
    private Integer[] mColors;
    public float cx;
    public float cy;
    public int position;
    public ValueAnimator positionAnimator;
    public ValueAnimator colorAnimator;

    public Dot(DotLoader parent, int dotRadius, int position) {
        this.position = position;
        mColors = parent.mColors;
        mCurrentColorIndex = 0;
        mDotRadius = dotRadius;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColors[mCurrentColorIndex]);
        mPaint.setShadowLayer(5.5f, 6.0f, 6.0f, Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setColorIndex(int index) {
        mCurrentColorIndex = index;
        mPaint.setColor(mColors[index]);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    private int getCurrentColor() {
        return mColors[mCurrentColorIndex];
    }

    public int incrementAndGetColor() {
        incrementColorIndex();
        return getCurrentColor();
    }

    public void applyNextColor() {
        mCurrentColorIndex++;
        if (mCurrentColorIndex >= mColors.length)
            mCurrentColorIndex = 0;
        mPaint.setColor(mColors[mCurrentColorIndex]);
    }

    public int incrementColorIndex() {
        mCurrentColorIndex++;
        if (mCurrentColorIndex >= mColors.length)
            mCurrentColorIndex = 0;
        return mCurrentColorIndex;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, mDotRadius, mPaint);
    }
}
