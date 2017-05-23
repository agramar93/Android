package com.example.singletouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchEventView extends View {
	private Paint paint = new Paint();
	private Path path = new Path();
	private float eventX, eventY;
	private int pointerID;
	private SparseArray<PointF> mActivePointers;
	private int[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA,
		      Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
		      Color.LTGRAY, Color.YELLOW };
	
	public SingleTouchEventView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mActivePointers = new SparseArray<PointF>();
		paint.setTextSize(40f);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(6f);
		paint.setColor(Color.YELLOW);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int size = mActivePointers.size(), i = 0; i < size; i++) {
		      PointF point = mActivePointers.valueAt(i);
		      if (point != null)
		        paint.setColor(colors[i % 9]);
		      canvas.drawCircle(point.x, point.y, 100, paint);
		      canvas.drawText("Pointer ID: " +i + "'s x position is " + (int) point.x + " and y position is " +
						(int) point.y, 50, 50+i*50, paint);
		    }
		  
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		pointerID = event.getPointerId(pointerIndex);
		eventX = event.getX();
		eventY = event.getY();
		
		switch (event.getActionMasked()) {
		
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			if(mActivePointers.size()<5){
			PointF f = new PointF();
		      f.x = event.getX(pointerIndex);
		      f.y = event.getY(pointerIndex);
		      mActivePointers.put(pointerID, f);
			}
		      break;
			
		case MotionEvent.ACTION_MOVE:
			if(event.getPointerCount()<6){
			for (int size = event.getPointerCount(), i = 0; i < size; i++) {
		        PointF point = mActivePointers.get(event.getPointerId(i));
		        if (point != null) {
		          point.x = event.getX(i);
		          point.y = event.getY(i);
		        }
		      }
			}
			
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			 mActivePointers.remove(pointerID);
			 break;
		case MotionEvent.ACTION_CANCEL: {
		      mActivePointers.remove(pointerID);
		      break;
		    }
		default:
			return false;
		}

		// Schedules a repaint.
		invalidate();
		return true;
	}
}