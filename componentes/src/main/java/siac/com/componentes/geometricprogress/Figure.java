package siac.com.componentes.geometricprogress;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Figure {

    private Path mPath;
    private Paint mPaint;

    public Figure(Path path, int color, int alpha) {
        mPath = path;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        mPaint.setAlpha(alpha);
        mPaint.setStrokeWidth(0);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }
}