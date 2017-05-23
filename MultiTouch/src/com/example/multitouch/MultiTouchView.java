package com.example.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchView extends View {
	private Paint p = new Paint();
	private int pointerID;
	private SparseArray<PointF> Dots; //PointF is used for convenience of finding x and y coordinates
	private int[] colors = { Color.RED, Color.GREEN, Color.BLUE,
		      Color.CYAN, Color.BLACK, Color.GRAY};
	
	public MultiTouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Dots = new SparseArray<PointF>();
		p.setTextSize(40f);
		p.setStrokeWidth(8f);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//iterate through each dot
		for (int size = Dots.size(), i = 0; i < size; i++) {
		      PointF point = Dots.valueAt(i);
		      if (point != null)
		        p.setColor(colors[i]);
		      canvas.drawCircle(point.x, point.y, 100, p);
		      canvas.drawText("Pointer ID: " +i + "'s x position is " + (int) point.x + " and y position is " +
						(int) point.y, 50, 50+i*50, p);
		    }
		  
		
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int pointerIndex = event.getActionIndex();
		pointerID = event.getPointerId(pointerIndex);
		
		//pointer information has been collection now add dots based on event captured
		switch (event.getActionMasked()) {
		
		//pressure has been applied
		case MotionEvent.ACTION_DOWN:
		//no break because every pointer must be collected
		case MotionEvent.ACTION_POINTER_DOWN:
			//only allow up to 5 dots for 5 fingers
			if(Dots.size()<5){
				PointF f = new PointF();
				//set x and y coordinate of dot
				f.x = event.getX(pointerIndex);
				f.y = event.getY(pointerIndex);
				Dots.put(pointerID, f);
			}
		    break;
			
		  //update x and y coordinate of dot based on movement
		case MotionEvent.ACTION_MOVE:
			if(event.getPointerCount()<6){
				for (int size = event.getPointerCount(), i = 0; i < size; i++) 
					{
					PointF point = Dots.get(event.getPointerId(i));
					if (point != null) {
						point.x = event.getX(i);
						point.y = event.getY(i);
					}
				}
			}
			
			break;
		//Pressure has been released so remove dot from screen
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			 Dots.remove(pointerID);
			 break;
		default:
			return false;
		}

		
		invalidate(); //repaint canvas
		return true;
	}
}