package com.example.compass_final;

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

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager, accManager;
	private Sensor sensor, accelerometer;
	
	private TextView acceleration;
	private Matrix matrix;
	private ImageView iv_compass;
	private Bitmap bitmap, rot_bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		accManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = accManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		accManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
		acceleration =(TextView) findViewById(R.id.acceleration);
		
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
		if(event.sensor==accelerometer){
			//ramp-speed - play with this value until satisfied
			float Filter_Factor = .99f;
			float accel[] = new float[3]; 


			//high-pass filter to eliminate gravity
			accel[0] = event.values[0] * Filter_Factor + accel[0] * (1.0f - Filter_Factor);
			accel[1] = event.values[1] * Filter_Factor + accel[1] * (1.0f - Filter_Factor);
			accel[2] = event.values[2] * Filter_Factor + accel[2] * (1.0f - Filter_Factor);
			float n_grav_x = event.values[0] = event.values[0] - accel[0];
			float n_grav_y = event.values[1] - accel[1];
			float n_grav_z = event.values[2] - accel[2];
		
			acceleration.setText("X(non-gravity): " + n_grav_x + "\nY(non-gravity): " + n_grav_y + "\nZ(non-gravity): "+ n_grav_z + 
					"\nX(gravity): " + event.values[0] + "\nY(gravity)"+ event.values[1] + "\nZ(gravity):" + event.values[2]);
		}
	}
}
