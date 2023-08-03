package com.chen.lockmove;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private Paint paint;
    private PointF startPoint;
    private PointF endPoint;
    private boolean isDragging;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5f);

        startPoint = new PointF(600f, 100f);
        endPoint = new PointF(300f, 100f);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);

        //canvas.drawCircle(startPoint.x, startPoint.y, 10f, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() >= startPoint.x - 50 && event.getX() <= startPoint.x + 50
                        && event.getY() >= startPoint.y - 50 && event.getY() <= startPoint.y + 50) {
                    isDragging = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    startPoint.x = event.getX();
                    startPoint.y = event.getY();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                break;
        }
        return true;
    }
}
