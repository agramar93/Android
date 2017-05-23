package com.example.finalproject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

	public class CivilWar extends Activity{
		int count,c;
		ArrayList<Locations> Carolina;
		ArrayList<Locations> Virginia;
		LatLng newBrunswick,FortSumter,HatterasInletBatteries,NewBer,NewBern,FortMacon,Secessionville,GoldsboroBridge,CharlestonHarbor,FortWagner,WhiteHall,Plymouth,AlbemarleSound,FortFisher,Averasborough,Bentonville,RoanokeIsland,SouthMills,Kinston,FortAnderson,WyseFork,Wilmington;
		LatLng TomsBrook,GuardHill, Waynesboro, Monocacy, FishersHill,Winchester,Manassas, CedarCreek, ClarkeCounty, CrossKeys,Lynchburg,PortRepublic, NewMarket, Sewell, AquiaCreek, BullRun, Dranesville, CockpitPoint,McDowell,NewKentCounty;
		CameraUpdate center;
		private HashMap<Marker,Locations> mMarkersHashMap;
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);


	    }
	    public class Locations{

	        String lname;

	        LatLng llatLng;

	        Uri Url;

	        Locations(String name, LatLng latlng, Uri u)

	        {

	        lname = name;

	        llatLng = latlng;

	        Url = u;

	        }

	        public String getname(){

	        return lname;

	        }

	        public LatLng getLatLng(){

	        return llatLng;

	        }

	        public Uri getUri_marker(){

	        return Url;

	        }

	        

	        };

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

	        MarkerOptions markerOption = new MarkerOptions().position(Loc.getLatLng());

	        Marker currentMarker = mMap.addMarker(markerOption);

	        mMarkersHashMap.put(currentMarker, Loc);

	        mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());  

	            mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

	    

	 
	    public void onInfoWindowClick(Marker arg0) {

	    // TODO Auto-generated method stub

	    Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_LONG).show();

	    for (int i=0;i<c;i++){

	    if (arg0.getPosition().longitude==Carolina.get(i).getLatLng().longitude){

	        Intent intent = new Intent(Intent.ACTION_VIEW, state.get(i).getUri_marker());

	        startActivity(intent);

	    }

	    }

	    }

	    

	    });

	        }
	    	return state.size();
	    }
	    public ArrayList<Locations> setCarolinaLocations(){
	    	//Carolina
	    	Carolina = new ArrayList<Locations>();
	    	
	    	FortSumter = new LatLng(32.75241, -79.87496);
	    	Locations FortSumterLoc = new Locations("FortSumter", FortSumter, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Fort_Sumter"));

	        HatterasInletBatteries  = new LatLng(35.1864, -75.7645); 

	        Locations HatterasInletBatteriesLoc = new Locations("HatterasInletBatteries", HatterasInletBatteries, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Hatteras_Inlet_Batteries"));

	        NewBern  =new LatLng(35.0428, -77.0148); 

	        Locations NewBernLoc = new Locations("NewBern", NewBern, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_New_Bern"));

	        FortMacon =new LatLng(34.696111, -76.678889); 

	        Locations FortMaconLoc = new Locations("FortMacon", FortMacon, Uri.parse("http://en.wikipedia.org/wiki/Siege_of_Fort_Macon"));

	        Secessionville =new LatLng(32.703889, -79.948056); 

	        Locations SecessionvilleLoc = new Locations("Secessionville", Secessionville, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Secessionville"));

	        GoldsboroBridge =new LatLng(35.3396, -78.0288);

	        Locations GoldsboroBridgeLoc = new Locations("GoldsboroBridge", GoldsboroBridge, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Goldsboro_Bridge"));

	        CharlestonHarbor =new LatLng(32.7526, -79.8745);

	        Locations CharlestonHarborLoc = new Locations("CharlestonHarbor", CharlestonHarbor, Uri.parse("http://en.wikipedia.org/wiki/First_Battle_of_Charleston_Harbor"));

	        FortWagner =new LatLng(32.73, -79.871111); 

	        Locations FortWagnerLoc = new Locations("FortWagner", FortWagner, Uri.parse("http://en.wikipedia.org/wiki/Fort_Wagner"));

	        WhiteHall=new LatLng(35.2272, -77.8464); 

	        Locations WhiteHallLoc = new Locations("WhiteHall", WhiteHall, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_White_Hall"));

	        Plymouth =new LatLng(35.86648, -76.7519); 

	        Locations PlymouthLoc = new Locations("Plymouth",Plymouth, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Plymouth_(1864)"));

	        AlbemarleSound =new LatLng(35.978561, -76.536942); 

	        Locations AlbemarleSoundLoc = new Locations("AlbemarleSound", AlbemarleSound, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Albemarle_Sound"));

	        FortFisher  =new LatLng(33.9715, -77.918); 

	        Locations FortFisherLoc = new Locations("FortFisher", FortFisher, Uri.parse("http://en.wikipedia.org/wiki/Fort_Fisher"));

	        Averasborough =new LatLng(35.2661, -78.6721); 

	        Locations AverasboroughLoc = new Locations("Averasborough", Averasborough, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Averasborough"));

	        Bentonville =new LatLng(35.2532, -78.6708); 

	        Locations BentonvilleLoc = new Locations("Bentonville",Bentonville, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Bentonville"));

	        RoanokeIsland =new LatLng(35.8783, -75.6678); 

	        Locations RoanokeIslandLoc = new Locations("RoanokeIsland", RoanokeIsland, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Roanoke_Island"));

	        SouthMills =new LatLng(36.4304, -76.3033); 

	        Locations SouthMillsLoc = new Locations("SouthMills", SouthMills, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_South_Mills"));

	        Kinston =new LatLng(35.2441, -77.5847); 

	        Locations KinstonLoc = new Locations("Kinston",Kinston, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Kinston"));

	        FortAnderson =new LatLng(35.1383, -77.0358);

	        Locations FortAndersonLoc = new Locations("FortAnderson",FortAnderson, Uri.parse("http://en.wikipedia.org/wiki/Fort_Anderson_(North_Carolina)"));

	        WyseFork= new LatLng(35.224, -77.5297);

	        Locations WyseForkLoc = new Locations("WyseFork",WyseFork, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Wyse_Fork"));

	        Wilmington=new LatLng(34.1795, -77.954);

	        Locations WilmingtonLoc = new Locations("Wilmington", Wilmington, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Wilmington"));

	        Carolina.add(FortSumterLoc);

	        Carolina.add(HatterasInletBatteriesLoc);

	        Carolina.add(NewBernLoc);

	        Carolina.add(SecessionvilleLoc);

	        Carolina.add(GoldsboroBridgeLoc);

	        Carolina.add(CharlestonHarborLoc);

	        Carolina.add(FortWagnerLoc);

	        Carolina.add(WhiteHallLoc);

	        Carolina.add(PlymouthLoc);

	        Carolina.add(AlbemarleSoundLoc);

	        Carolina.add(FortFisherLoc);

	        Carolina.add(AverasboroughLoc);

	        Carolina.add(BentonvilleLoc);

	        Carolina.add(RoanokeIslandLoc);

	        Carolina.add(SouthMillsLoc);

	        Carolina.add(KinstonLoc);

	        Carolina.add(FortAndersonLoc);

	        Carolina.add(WyseForkLoc);

	        Carolina.add(WilmingtonLoc);  

	        Carolina.add(FortMaconLoc);
	    	return Carolina;
	    }
	    
	    public ArrayList<CivilWar.Locations> setVirginiaLocations(){
	    	Virginia = new ArrayList<Locations>();
	    	//Virginia
	    	NewMarket = new LatLng(38.644, -78.6733);

	        Locations NewMarketLoc= new Locations("NewMarket", NewMarket, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_New_Market"));

	        TomsBrook = new LatLng(38.947, -78.4397);

	        Locations TomsBrookLoc= new Locations("TomsBrook",TomsBrook, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Tom's_Brook"));

	        Waynesboro = new LatLng(38.070, -78.8944);

	        Locations WaynesboroLoc= new Locations("Waynesboro",Waynesboro, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Waynesboro,_Virginia"));

	        PortRepublic = new LatLng(38.2962, -78.8106);

	        Locations PortRepublicLoc= new Locations("PortRepublic",PortRepublic, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Port_Republic"));

	        Lynchburg = new LatLng(37.4037,-79.1702);

	        Locations LynchburgLoc= new Locations("Lynchburg",Lynchburg, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Lynchburg"));

	        CrossKeys = new LatLng(38.3284,-78.868521);

	        Locations CrossKeysLoc= new Locations("CrossKeys",CrossKeys, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Cross_Keys"));

	        ClarkeCounty = new LatLng(39.1200,-78);

	        Locations ClarkeCountyLoc= new Locations("ClarkeCounty",ClarkeCounty, Uri.parse("http://markerhunter.wordpress.com/2008/02/13/civil-war-engagements-in-clarke-county-virginia/"));

	        CedarCreek = new LatLng(37.322,-75.855);

	        Locations CedarCreekLoc= new Locations("CedarCreek",CedarCreek, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Cedar_Creek"));

	        Manassas = new LatLng(38.9089,-78.0756);

	        Locations ManassasLoc= new Locations("Manassas",Manassas, Uri.parse("http://en.wikipedia.org/wiki/First_Battle_of_Bull_Run"));

	        Winchester = new LatLng(39.1833, -78.1667);

	        Locations WinchesterLoc= new Locations("Winchester",Winchester, Uri.parse("http://en.wikipedia.org/wiki/Winchester,_Virginia_in_the_American_Civil_War"));

	        FishersHill = new LatLng(38.9834, -78.4038);

	        Locations FishersHillLoc= new Locations("FishersHill",FishersHill, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Fisher's_Hill"));

	        Monocacy = new LatLng(39.453, -77.371);

	        Locations MonocacyLoc= new Locations("Monocacy",Monocacy, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Monocacy"));

	        GuardHill = new LatLng(38.957, -78.2008337);

	        Locations GuardHillLoc= new Locations("GuardHill",GuardHill, Uri.parse(""));

	        Sewell = new LatLng(36.955,-76.3269);

	        Locations SewellLoc= new Locations("Sewell",Sewell, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Sewell's_Point"));

	        AquiaCreek = new LatLng(38.4307,-77.3764);

	        Locations AquiaCreekLoc= new Locations("AquiaCreek",AquiaCreek, Uri.parse(""));

	        BullRun = new LatLng(38.785, -77.5236);

	        Locations BullRunLoc= new Locations("BullRun",BullRun, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Aquia_Creek"));

	        Dranesville = new LatLng(38.7850, -77.5236);

	        Locations DranesvilleLoc= new Locations("Dranesville",Dranesville, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Dranesville"));

	        CockpitPoint = new LatLng(38.56, -77.2569);

	        Locations CockpitPointLoc= new Locations("CockpitPoint",CockpitPoint, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Cockpit_Point"));

	        McDowell = new LatLng(38.3357, -79.4906);

	        Locations McDowellLoc= new Locations("McDowell",McDowell, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_McDowell"));

	        NewKentCounty = new LatLng(37.51, -77);

	        Locations NewKentCountyLoc = new Locations("NewKentCounty", NewKentCounty, Uri.parse("http://en.wikipedia.org/wiki/Battle_of_Eltham's_Landing"));

	        Virginia.add(NewMarketLoc);

	        Virginia.add(TomsBrookLoc);

	        Virginia.add(WaynesboroLoc);

	        Virginia.add(PortRepublicLoc);

	        Virginia.add(LynchburgLoc);

	        Virginia.add(CrossKeysLoc);

	        Virginia.add(ClarkeCountyLoc);

	        Virginia.add(CedarCreekLoc);

	        Virginia.add(ManassasLoc);

	        Virginia.add(WinchesterLoc);

	        Virginia.add(FishersHillLoc);

	        Virginia.add(MonocacyLoc);

	        Virginia.add(GuardHillLoc);

	        Virginia.add(CockpitPointLoc);

	        Virginia.add(DranesvilleLoc);

	        Virginia.add(BullRunLoc);

	        Virginia.add(AquiaCreekLoc);

	        Virginia.add(SewellLoc);

	        Virginia.add(McDowellLoc);

	        Virginia.add(NewKentCountyLoc);
	    	return Virginia;
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
	    
	    
   
	}


