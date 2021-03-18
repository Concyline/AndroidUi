package br.com.calculadora;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class FillTextView extends TextView {

    private static final float VERTICAL_FONT_SCALING_FACTOR = 0.5f;
    private Paint mTestPaint;

    public FillTextView(Context context) {
        super(context);
        initialise();
    }

    public FillTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        mTestPaint = new Paint();
        mTestPaint.set(this.getPaint());
    }

    private void refitText(String text, int textWidth, int textHeight) {
        if (textHeight <= 0 || textWidth <= 0) {
            return;
        }

        float targetTextSizeVertical = (textHeight - this.getPaddingTop() - this.getPaddingBottom()) * VERTICAL_FONT_SCALING_FACTOR;

        float targetWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();

        float hi = 800;
        float lo = 2;
        final float threshold = 0.5f;

        mTestPaint.set(this.getPaint());

        while ((hi - lo) > threshold) {
            float size = (hi + lo) / 2;
            mTestPaint.setTextSize(size);
            if (mTestPaint.measureText(text) >= targetWidth)
                hi = size;
            else
                lo = size;
        }
        float targetTextSizeHorizontal = lo;

        float targetTextSize = Math.min(targetTextSizeVertical, targetTextSizeHorizontal);

        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) targetTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

        refitText(this.getText().toString(), parentWidth, parentHeight);
        this.setMeasuredDimension(parentWidth, parentHeight);
    }

    @Override
    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
        refitText(text.toString(), this.getWidth(), this.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (h != oldh || w != oldw) {
            refitText(this.getText().toString(), w, h);
        }
    }

}