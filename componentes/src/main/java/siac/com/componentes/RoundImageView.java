package siac.com.componentes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

public class RoundImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int borderWidth;
    private ColorStateList borderColor;
    private boolean shadow;
    private int viewWidth;
    private int viewHeight;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private BitmapShader shader;

    public RoundImageView(Context context)
    {
        super(context);
        obtainStyledAttributes(context, null, 0);
        setup();
    }

    public RoundImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        setup();
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        obtainStyledAttributes(context, attrs, defStyle);
        setup();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);

            borderWidth = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderWidth, 4);
            borderColor = typedArray.getColorStateList(R.styleable.RoundImageView_borderColor);
            shadow = typedArray.getBoolean(R.styleable.RoundImageView_shadow, false);

            return;
        }

    }

    private void setup()
    {
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setColor(borderColor.getDefaultColor());

        if(shadow) {
            paintBorder.setShadowLayer(5.0f, 3.0f, 3.0f, Color.argb(90, 0, 0, 0));
        }
    }

    public void setBorderWidth(int borderWidth)
    {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor)
    {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

        this.invalidate();
    }

    private void loadBitmap()
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();

        if (bitmapDrawable != null)
            image = bitmapDrawable.getBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas)
    {
        loadBitmap();

        if (image != null)
        {
            shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(), canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            int circleCenter = viewWidth / 2;
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - 4.0f, paintBorder);
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - 4.0f, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

        viewWidth = width - (borderWidth * 2);
        viewHeight = height - (borderWidth * 2);

        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec)
    {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        }
        else
        {
            // Measure the text
            result = viewWidth;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight, int measureSpecWidth)
    {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        }
        else
        {
            result = viewHeight;
        }

        return (result + 2);
    }
}
