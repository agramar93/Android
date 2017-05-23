package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InfoWindow extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.infowindow);
	Button goBack = (Button)findViewById(R.id.button1);
	goBack.setOnClickListener(new OnClickListener(){
    	@Override
    	 public void onClick(View v) {
    		Intent intent1=new Intent();
    		intent1.setClass(InfoWindow.this,StartScreen.class);
    		intent1.putExtra("Which_Game","0");
       		startActivity(intent1);
       		
       		
        	}
    	}  	
    );
	}

}
