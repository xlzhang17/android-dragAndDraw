package com.xlzhang.android.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";

    private Box mCurrentBox;
    private ArrayList<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroudPaint;

    public ArrayList<Box> getBoxes() {
        return mBoxes;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        mBoxes = boxes;
    }

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroudPaint = new Paint();
        mBackgroudPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF curr = new PointF(event.getX(), event.getY());

        Log.i(TAG, "Reveived event at x=" + curr.x + ", y=" + curr.y + ":");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(curr);
                mBoxes.add(mCurrentBox);
                Log.i(TAG, "Action down");
                break;
            case MotionEvent.ACTION_MOVE:
                if(mCurrentBox != null){
                    mCurrentBox.setCurrent(curr);
                    invalidate();
                }
                Log.i(TAG, "action move");
                break;
            case MotionEvent.ACTION_UP:
                mCurrentBox = null;
                Log.i(TAG, "action up");
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrentBox = null;
                Log.i(TAG, "action cancel");
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 先画背景
        canvas.drawPaint(mBackgroudPaint);

        for (Box box: mBoxes){
            float left = box.getOrigin().x;
            float right = box.getCurrent().x;
            float top = box.getOrigin().y;
            float bottom = box.getCurrent().y;

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }
}
