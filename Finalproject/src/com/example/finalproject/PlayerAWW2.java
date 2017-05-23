package com.example.finalproject;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Random;

import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.WorldWar2.Locations;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlayerAWW2 extends Activity {
	int markerdestroy = 0 ;
	public static Activity iPA= null;
	boolean alreadytouched = false;
	boolean Playerwon= false;
	private HashMap<Marker,Locations> mMarkersHashMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		Playerwon= false;
		iPA=this;
        mMarkersHashMap = new HashMap<Marker,Locations>();
		setContentView(R.layout.playeraww2);
		final int numLocInt;
		Intent i = getIntent(); 
		String numLoc = i.getStringExtra("Number_of_Locations");
		String myUrl = i.getStringExtra("infoWindowUrl");
		if(myUrl!= null){
			Uri myUriUrl = Uri.parse(myUrl);
			Intent intent2 = new Intent(Intent.ACTION_VIEW, myUriUrl);

			startActivity(intent2);
		}

		if(numLoc.equals("") || Integer.valueOf(numLoc) < 5 || Integer.valueOf(numLoc) > 10)
			numLocInt = 5;
		else
			numLocInt = Integer.valueOf(numLoc);
		
		


		GoogleMap mMap;
		final TextView markercounter = (TextView)findViewById(R.id.textView1);
		final LatLng cameracenter= new LatLng(37.22,112.10);
		
		Button surrender = (Button) findViewById(R.id.button1);
		surrender.setOnClickListener(new OnClickListener(){
			Handler mHandler1 = new Handler();
        	private Runnable mLaunchTask1 = new Runnable(){
        		@Override
				public void run(){
	        		Intent intent1=new Intent();
	        		intent1.setClass(PlayerAWW2.this,StartScreen.class);
	        		intent1.putExtra("Which_Game", "2");
	           		startActivity(intent1);
	           	
        		}
        		
        	};
        	@Override
	    	 public void onClick(View v) {
        		Playerwon = true ;
        		Toast.makeText(getApplicationContext(),"Player B wins the game!", Toast.LENGTH_SHORT).show();
        		mHandler1.postDelayed(mLaunchTask1, 2000);
        	}  	
        });

	    mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	            .getMap();
	    WorldWar2 ChinaWW2 = new WorldWar2();
	    ArrayList<WorldWar2.Locations> myChinaLoc = ChinaWW2.setChinaLocation();
	    final int size = createMarkers(numLocInt, myChinaLoc, mMap);
	    Toast.makeText(getApplicationContext(), "Number of locations selected: " + numLocInt, Toast.LENGTH_SHORT).show();
	    
	    mMap.getUiSettings().setAllGesturesEnabled(false);
	    mMap.getUiSettings().setZoomControlsEnabled(false);
	    mMap.getUiSettings().setScrollGesturesEnabled(false);
	    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameracenter, (float)4.0));


	        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
            {	
	        	Handler mHandler = new Handler();
	        	private Runnable mLaunchTask = new Runnable(){
	        		@Override
					public void run(){
		        		Intent intent1=new Intent();
		        		intent1.setClass(PlayerAWW2.this,PlayerBWW2.class);
		        		intent1.putExtra("Number_of_Locations", numLocInt);
		           		startActivity(intent1);
	        		}
	        		
	        	};
	        	Handler mHandler1 = new Handler();
	        	private Runnable mLaunchTask1 = new Runnable(){
	        		@Override
					public void run(){
		        		Intent intent1=new Intent();
		        		intent1.setClass(PlayerAWW2.this,StartScreen.class);
		        		intent1.putExtra("Which_Game", "2");
		           		startActivity(intent1);
		           	
	        		}
	        		
	        	};
                @Override
                public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker)
                {
                	if(!alreadytouched)
                	{
                	if(marker.getAlpha() == 0)
                	{
                	alreadytouched = true;
                    marker.setVisible(true);
                    marker.setAlpha(1);
                    markerdestroy++;
                    markercounter.setText(markerdestroy +"/"+size+ " destroyed");
                    Toast.makeText(getApplicationContext(), "This location was destroyed: " + marker.getTitle()+ "\nTotal locations destroyed: "+ markerdestroy, Toast.LENGTH_SHORT).show();
                    
                    marker.showInfoWindow();
                    if (markerdestroy == size )
                    	{Toast.makeText(getApplicationContext(),"You win \nAll locations destroyed!", Toast.LENGTH_SHORT).show();
                    markercounter.setText(markerdestroy +"/"+size+ " destroyed  You won!");
                    mHandler1.postDelayed(mLaunchTask1, 2000);}
                    else                     mHandler.postDelayed(mLaunchTask, 2000);
                	}
                	else{
                		Toast.makeText(getApplicationContext(),"You already destroyed this location, try again." , Toast.LENGTH_SHORT).show();
                		marker.showInfoWindow();
                	}
                	}


                    return true;
                }
            });
	        
	        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
	        	Handler mHandler = new Handler();
	        	private Runnable mLaunchTask = new Runnable(){
	        		@Override
					public void run(){
		        		Intent intent1=new Intent();
		        		intent1.setClass(PlayerAWW2.this,PlayerBWW2.class);
		        		intent1.putExtra("Number_of_Locations", numLocInt);
		           		startActivity(intent1);
	        		}
	        	};
	        	@Override
	        	public void onMapClick (LatLng arg0){
	        		if(!alreadytouched)
	        		{
	        		alreadytouched = true;
	        		Toast.makeText(getApplicationContext(),"You missed" , Toast.LENGTH_SHORT).show();
	        		mHandler.postDelayed(mLaunchTask, 2000);
	        		
	        		}

	        	}
	        });
	}
    public class MarkerInfoWindowAdapter implements InfoWindowAdapter{

        public MarkerInfoWindowAdapter()

        {

        }

        @Override

        public View getInfoWindow(Marker marker)

        {

            return null;

        }



        @Override

        public View getInfoContents(Marker marker)

        {

            View v  = getLayoutInflater().inflate(R.layout.info, null);

            Locations myMarker = mMarkersHashMap.get(marker);

            TextView markerName = (TextView)v.findViewById(R.id.textView1);

            markerName.setText(myMarker.getname());

            return v;

        }

    }
	public int createMarkers(final int c, final ArrayList<Locations> state, GoogleMap mMap){
    	Random randomGeneratorRandom = new Random();
        mMarkersHashMap = new HashMap<Marker,Locations>();
    	while (state.size()>c){
    		int randomInt=randomGeneratorRandom.nextInt(state.size()-1);
    		state.remove(randomInt);   
    	}
        for (Locations Loc : state)

        {

        

            // Create user marker with custom icon and other options

        MarkerOptions markerOption = new MarkerOptions().position(Loc.getLatLng()).alpha(0).title(Loc.getname());

        Marker currentMarker = mMap.addMarker(markerOption);

        mMarkersHashMap.put(currentMarker, Loc);

        mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());  

            mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

    

 
    public void onInfoWindowClick(Marker arg0) {

    // TODO Auto-generated method stub

    	if (!Playerwon){

    	if(!alreadytouched){
    for (int i=0;i<c;i++){

    if (arg0.getPosition().longitude==state.get(i).getLatLng().longitude){

        Intent intent = new Intent(Intent.ACTION_VIEW, state.get(i).getUri_marker());

        startActivity(intent);

    }

    }
    	}
    	else
    	{
    	    for (int i=0;i<c;i++){

    	        if (arg0.getPosition().longitude==state.get(i).getLatLng().longitude){

    	            Uri myUrl = state.get(i).getUri_marker();
	        		Intent intent1=new Intent();
	        		intent1.setClass(PlayerAWW2.this,PlayerBWW2.class);
	        		intent1.putExtra("infoWindowUrl", myUrl.toString());
	           		startActivity(intent1);
    	            

    	        }

    	        }
    		
    	}

    }
    }

    

    });

        }
    	return state.size();
    }
	@Override
	public void finish (){
		super.finish();
		iPA=null;
		
	}
	@Override
	protected void onRestart(){
		alreadytouched = false;
		super.onStart();
	}

}