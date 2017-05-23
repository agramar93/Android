package com.example.compass;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
	
	private SensorManager sensorManager;
	private Sensor sensor;
	
	private TextView acceleration;
	private Matrix matrix;
	private ImageView iv_compass;
	private Bitmap bitmap, rot_bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_GAME);
		
		iv_compass = (ImageView)findViewById(R.id.main_iv_compass);
		bitmap = BitmapFactory.decodeResource(getResources(),  R.drawable.compass_nadel);
		matrix = new Matrix();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy){
		
	}
	
	public void onSensorChanged(SensorEvent event){
		if(event.sensor == sensor){
			int winkel = (int)event.values[0];
			int swinkel = -winkel + (winkel>180?360:0);
			
			matrix.setRotate(swinkel);
			rot_bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
			iv_compass.setImageBitmap(rot_bitmap);
			
		}
	}


	
}
