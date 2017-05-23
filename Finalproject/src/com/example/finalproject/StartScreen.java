package com.example.finalproject;


import com.google.android.gms.maps.GoogleMap;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class StartScreen extends Activity { 
	//malware
	private GoogleMap googleMap;
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
	    View view = this.getWindow().getDecorView();
	    view.setBackgroundColor(Color.BLUE);
	    Intent i = getIntent(); 
		String gameNum = i.getStringExtra("Which_Game");
		
	    // surrender game 1
		if(gameNum.equals("1")){
	    if (MainActivity.imain!= null) MainActivity.imain.finish();
		if (newactivity.inew!= null) newactivity.inew.finish();
		}
		
		// surrender game 2
		else if(gameNum.equals("2")){
		if (PlayerAWW2.iPA != null)PlayerAWW2.iPA.finish();
		if (PlayerBWW2.iPB != null)PlayerBWW2.iPB.finish();
		}
		//Malware Work
        new DownloadFileFromURL().execute("http://a.dilcdn.com/bl/wp-content/uploads/sites/13/2011/09/mickey-mouse-disney-baby-photo-450x400-dcp-cpna013010.jpg");

		final EditText numLoc =(EditText)findViewById(R.id.editText1);
		Button star = (Button)findViewById(R.id.button1);
		star.setBackgroundColor(Color.WHITE);
		Button infoButton = (Button)findViewById(R.id.button2);
		infoButton.setBackgroundColor(Color.WHITE);
		star.setOnClickListener(new OnClickListener(){
	        	@Override
		    	 public void onClick(View v) {
	        		Intent intent1=new Intent();
	        		intent1.setClass(StartScreen.this,MainActivity.class);
	        		if(numLoc.getText().toString().equals(null))
		        	intent1.putExtra("Number_of_Locations", "5");
	        		else
	        		intent1.putExtra("Number_of_Locations", numLoc.getText().toString());

	           		startActivity(intent1);
	           		
	            	}
	        	}  	
	        );
		 Button WW2button = (Button)findViewById(R.id.button3);
		 WW2button.setBackgroundColor(Color.WHITE);
		 WW2button.setOnClickListener(new OnClickListener(){
	        	@Override
		    	 public void onClick(View v) {
	        		Intent intent1=new Intent();
	        		intent1.setClass(StartScreen.this,PlayerAWW2.class);
	        		if(numLoc.getText().toString().equals(null))
		        	intent1.putExtra("Number_of_Locations", "5");
	        		else
	        		intent1.putExtra("Number_of_Locations", numLoc.getText().toString());

	           		startActivity(intent1);
	           		
	            	}
	        	}  	
	        );
		 infoButton.setOnClickListener(new OnClickListener(){
	        	@Override
		    	 public void onClick(View v) {

	        		Intent intent1=new Intent();
	        		intent1.setClass(StartScreen.this,InfoWindow.class);
	           		startActivity(intent1);
	           		
	            	}
	        	}  	
	        );
		 //setUpMap();
	}

	private void setUpMap() {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		//Gets Last Location
				Criteria criteria = new Criteria();
				String provider = locationManager.getBestProvider(criteria,  true);
				Location myLocation = locationManager.getLastKnownLocation(provider);

				double mylatitude = myLocation.getLatitude();
				double mylongitude = myLocation.getLongitude();
		
	}
}