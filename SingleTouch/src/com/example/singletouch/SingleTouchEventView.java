package com.example.singletouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchEventView extends View {
	private Paint paint = new Paint();
	private Path path = new Path();
	private float eventX, eventY;
	private int pointerID;
	private Canvas c;
	
	public SingleTouchEventView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint.setTextSize(40f);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(6f);
		paint.setColor(Color.YELLOW);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//canvas.drawPath(path, paint);
		canvas.drawCircle(eventX, eventY, 100, paint);
		//c = new Canvas();
		//c.drawPath(path,paint);
		canvas.drawText("Pointer ID: " +pointerID + "'s x position is " + (int) eventX + " and y position is " +
				(int) eventY, 50, 50, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		pointerID = event.getPointerId(pointerIndex);
		eventX = event.getX();
		eventY = event.getY();
		//c.drawCircle(eventX, eventY, 100, paint);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(eventX, eventY);
			return true;
		case MotionEvent.ACTION_MOVE:
			
			path.lineTo(eventX, eventY);
			break;
		case MotionEvent.ACTION_UP:
			// nothing to do
			break;
		default:
			return false;
		}

		// Schedules a repaint.
		invalidate();
		return true;
	}
}