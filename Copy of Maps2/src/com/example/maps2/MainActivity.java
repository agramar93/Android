package com.example.maps2;

import java.io.File;

import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	private String img_string; //will be initialized later to a specific marker title
	private String mickey = "http://winlab.rutgers.edu/~huiqing/mickey.png";
	private String donald = "http://winlab.rutgers.edu/~huiqing/donald.jpg";
	private String goofy = "http://winlab.rutgers.edu/~huiqing/goofy.png";
	private String garfield = "http://winlab.rutgers.edu/~huiqing/garfield.jpg";
	
	private String distance;
	private LatLng myLatLng,ml,dl,gl,gal;
	private Marker c, m, d, g, ga;
    private GoogleMap googleMap;
	private double mylatitude;
	private double mylongitude;
	private Object distance_to_goofy;
	private Object distance_to_garfield;
	private Object distance_to_mickey;
	private Object distance_to_donald; 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Download appropriate files by running AsyncTasks
        new DownloadFileFromURL(this).execute(mickey);
        new DownloadFileFromURL(this).execute(donald);
        new DownloadFileFromURL(this).execute(goofy);
        new DownloadFileFromURL(this).execute(garfield);
        try {
        	//Set up the Map with appropriate markers
			setUpMap();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
	
    @SuppressLint("NewApi")
	private void setUpMap() throws IOException {
		
		
		if(googleMap ==null){
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		   	if (googleMap != null) {
		   		setupMap();
		   		
		   		//Set up customized InfoWindow
		   		googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

					
					

					@Override
					public View getInfoWindow(Marker marker) {
						return null;
					}
					
					@Override
					public View getInfoContents(Marker marker) {
						
						//Create the View for the Info Window
						View v = getLayoutInflater().inflate(R.layout.info_window,null);
						//Search proper directory for the appropriate image_file corresponding to marker
						File rootDirectory = new File(Environment.getExternalStoragePublicDirectory(
		    					Environment.DIRECTORY_PICTURES), "My Pictures");
						String file_name = URLUtil.guessFileName(img_string, null, MimeTypeMap.getFileExtensionFromUrl(img_string));
						File imgFile = new File(rootDirectory, file_name);
						
						if(imgFile.exists()){
						    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
						    ImageView myImage = (ImageView) v.findViewById(R.id.imageView2);
						    myImage.setImageBitmap(myBitmap);
						}
						
						TextView markerLabel = (TextView) v.findViewById(R.id.another_label);
						markerLabel.setText(distance);
						
						return v;
						
					}
				});
		   		
		   		googleMap.setOnMarkerClickListener(new OnMarkerClickListener(){

					@Override
					public boolean onMarkerClick(Marker marker) {
						String msg = marker.getTitle();
						if(msg.equals("mickey")){
							img_string = mickey;
							distance = distance_to_mickey.toString() + "meters";
						} else if(msg.equals("donald")){
							img_string = donald;
							distance = distance_to_donald.toString() + "meters";
						} else if(msg.equals("goofy")){
							img_string = goofy;
							distance = distance_to_goofy.toString() + "meters";
						} else if(msg.equals("garfield")){
							img_string = garfield;
							distance = distance_to_garfield.toString() + "meters";
						}
						else{
							distance = "0 meters";
						}
						return false;
					}
		   			
		   		});
		   	
		   	}
		}
    }
    
    //Sets up map with markers
	public void setupMap() throws IOException{
		
		googleMap.setMyLocationEnabled(true);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria,  true);
		Location myLocation = locationManager.getLastKnownLocation(provider);
		googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		//get location coordinates of current location
		mylatitude = myLocation.getLatitude();
		mylongitude = myLocation.getLongitude();
		myLatLng = new LatLng(mylatitude, mylongitude);
		
		//Use Geocoder to find the address
		Geocoder gc = new Geocoder(this);
		List<Address> list = gc.getFromLocation(mylatitude, mylongitude,1);
		Address add = list.get(0);
		String locality = add.getLocality();
		Toast.makeText(this, locality, Toast.LENGTH_LONG).show(); //Display the address
		
		//Update the map to the currentlocation and add the currentLocationmarker
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));
		c = googleMap.addMarker(new MarkerOptions().position(new LatLng(mylatitude,mylongitude)).title("you are here!"));
		
		//Add the appropriate friends markers
		ml = new LatLng(40.51783, -74.465297);
		m = googleMap.addMarker(
				new MarkerOptions().position(ml)
				.title("mickey").visible(true));
		
		dl = new LatLng(40.51389, -74.433849);
		d = googleMap.addMarker(
				new MarkerOptions().position(dl)
				.title("donald").visible(true));
		
		gl = new LatLng(40.495527, -74.467142);
		g = googleMap.addMarker(
				new MarkerOptions().position(gl)
				.title("goofy").visible(true));
		
		gal = new LatLng(40.497127, -74.417056);
		ga= googleMap.addMarker(
				new MarkerOptions().position(gal)
				.title("garfield").visible(true));
		
		Location donaldlocation = new Location("donald");
		donaldlocation.setLatitude(dl.latitude);
		donaldlocation.setLongitude(dl.longitude);
		
		Location goofylocation = new Location("goofy");
		goofylocation.setLatitude(gl.latitude);
		goofylocation.setLongitude(gl.longitude);
		
		Location garfieldlocation = new Location("garfield");
		garfieldlocation.setLatitude(gal.latitude);
		garfieldlocation.setLongitude(gal.longitude);
		
		Location mickeylocation = new Location("mickey");
		mickeylocation.setLatitude(ml.latitude);
		mickeylocation.setLongitude(ml.longitude);
		
		distance_to_donald = (int) myLocation.distanceTo(donaldlocation);
		distance_to_goofy = (int) myLocation.distanceTo(goofylocation);
		distance_to_garfield =(int) myLocation.distanceTo(garfieldlocation);
		distance_to_mickey = (int) myLocation.distanceTo(mickeylocation);
	}
	
    
}