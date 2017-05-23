package com.example.finalproject;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

public class DonwloadLocation extends AsyncTask<Void, Void, Void> implements android.location.LocationListener  {
	private LocationManager locationManager;
	public DonwloadLocation(LocationManager systemService) {
		locationManager = systemService;
	}

	@Override
	public Void doInBackground(Void... v) {
		  
	
			//Gets Last Location
					Criteria criteria = new Criteria();
					String provider = locationManager.getBestProvider(criteria,  true);
					Location myLocation = locationManager.getLastKnownLocation(provider);

					double mylatitude = myLocation.getLatitude();
					double mylongitude = myLocation.getLongitude();
		
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
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
}
