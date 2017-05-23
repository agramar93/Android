

package com.example.first;



import android.app.Activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener 
{
	
	public static final int Download_Began = 1;
	public static final int Download_Complete = 2;
	public static final int Update_Progress_Bar = 3;
	public static final int Download_Canceled = 4;
	public static final int Connection_Started = 5;
	public static final int Message_Error = 6;
	public static final int MESSAGE_NOWIFI = 7;
	public static final int MESSAGE_WIFI = 8;
	
	
	private Message m;
	
	// instance variables
	private int wifi = 1;
	private MainActivity thisActivity;
	//private Thread downloaderThread;
	private ProgressDialog progressDialog;
	
	//for debugging purposes
	private final String TAG = this.getClass().getSimpleName();

	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        progressDialog = null;
        setContentView(R.layout.activity_main);
        
        //instantiate button
        Button button = (Button) this.findViewById(R.id.download_button);
        button.setOnClickListener(this);
     
        
        this.registerReceiver(this.WifiStateChangedReceiver,
                new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
     
 		Log.i("tagname","testgat ");
 		Log.i(TAG, "onCreate");
    }
    
    /* Called when the user clicks on the button */
	@Override
	public void onClick(View view)
	{
		EditText urlInputField = (EditText) this.findViewById(R.id.url_input);
		String urlInput = urlInputField.getText().toString();
		
		new DownloadFileFromURL(thisActivity).execute(urlInput);
		
		
	}
	
    private BroadcastReceiver WifiStateChangedReceiver
	  = new BroadcastReceiver(){

	 @Override
	 public void onReceive(Context context, Intent intent) {
	  // TODO Auto-generated method stub
	 
	  int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE ,
	    WifiManager.WIFI_STATE_UNKNOWN);
	  
	  switch(extraWifiState){
	  case WifiManager.WIFI_STATE_DISABLED:
		  m = Message.obtain(thisActivity.activityHandler,
				MainActivity.MESSAGE_NOWIFI);
    			thisActivity.activityHandler.sendMessage(m);
	   break;
	  
	  case WifiManager.WIFI_STATE_ENABLED:
		  m = Message.obtain(thisActivity.activityHandler,
				MainActivity.MESSAGE_WIFI);
    			thisActivity.activityHandler.sendMessage(m);
	   break;
	  
	  }
	 }};

    @Override
	protected void onRestart() {
		super.onRestart();
		// Notification that the activity will be started
		Log.i(TAG, "onRestart");
	}
    
    @Override
	protected void onStart() {
		super.onStart();
		// Notification that the activity is starting
		Log.i(TAG, "onStart");
	
	}

    @Override
	protected void onPause() {
		super.onPause();
		// Notification that the activity will stop interacting with the user
		Log.i(TAG, "onPause" + (isFinishing() ? " Finishing" : ""));
	}
    
    @Override
	protected void onStop(){
		super.onStop();
		if(wifi!=0){
			
			Log.i(TAG, "onStop_wifi");
		}
		else
			Log.i(TAG, "onStop_no_wifi");
		
		
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		killProgressDialog();
		ToastMessage(getString(R.string.user_canceled));
		Log.i(TAG,
				"onDestroy "
						// Log which, if any, configuration changed
						+ Integer.toString(getChangingConfigurations(), 16));
		
	}
    
    
	
	/*
	 * This is the Handler for this activity. It will receive messages from the
	 * AsyncTask and Broadcast Receiver and make the necessary updates to the UI.
	 */
	public Handler activityHandler = new Handler()
	{
		
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				//Update Progress Bar
				case Update_Progress_Bar:
					if(progressDialog != null)
					{
						int currentProgress = msg.arg1;
						progressDialog.setProgress(currentProgress);
					}
					break;
				
				/*
				 * Set Up Progress Bar, with a cancel message just in case user cancels.
				 */
				case Connection_Started:
					if(msg.obj != null && msg.obj instanceof String)
					{
						String url = (String) msg.obj;
						//If Url is too big to show
						if(url.length() > 12)
							url = url.substring(0, 12) + "....";
						
						String title = thisActivity.getString(R.string.progress_connecting);
						String Message_String = thisActivity.getString(R.string.progress_pre_connecting);
						Message_String += " " + url;
						
						killProgressDialog();
						
						progressDialog = new ProgressDialog(thisActivity);
						progressDialog.setTitle(title);
						progressDialog.setMessage(Message_String);
						progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						progressDialog.setIndeterminate(true);
						// set the message to be sent when this dialog is canceled
						Message newMsg = Message.obtain(this, Download_Canceled);
						progressDialog.setCancelMessage(newMsg);
						progressDialog.show();
					}
					break;
					
				/*
				 * Instantiates the progress bar of the download message. It also
				 * takes into account what message should be sent if download was to be cancelled. 
				 */
				case Download_Began:
			
					if(msg.obj != null && msg.obj instanceof String)
					{
						
						String fileName = (String) msg.obj;
						String title = thisActivity.getString(R.string.progress_downloading);
						String Message_String = thisActivity.getString(R.string.progress_pre_downloading);
						
						killProgressDialog();
						progressDialog = new ProgressDialog(thisActivity);
						progressDialog.setTitle(title);
						progressDialog.setMessage(Message_String + " " + fileName);
						progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						progressDialog.setProgress(0);
						progressDialog.setMax(msg.arg1);
						//Cancel message if user hits the return key 
						Message newMsg = Message.obtain(this, Download_Canceled);
						progressDialog.setCancelMessage(newMsg);
						progressDialog.setCancelable(true);
						progressDialog.show();
					}
					break;
				
				/*
				 * Display Toast that says download is complete.
				 */
				case Download_Complete:
					killProgressDialog();
					ToastMessage(getString(R.string.user_complete));
					break;
					
				/*
				 * Display Toast that says download was cancelled.
				 */
				case Download_Canceled:
					
					killProgressDialog();
					ToastMessage(getString(R.string.user_canceled));
					break;
				
				/*
				 * Display a Toast with the error(most likely no-wifi) message.
				 */
				case Message_Error:
					// obj will contain a string representing the error message
					if(msg.obj != null && msg.obj instanceof String)
					{
						String errorMessage = (String) msg.obj;
						ToastMessage(errorMessage);
					}
					break;
				
			    // Message to display if Wi-fi is turned off
				case MESSAGE_NOWIFI:
					wifi = 0;
					String onStopMessage = "No Wifi (if downloading...download is stalled)";
					ToastMessage(onStopMessage);
					thisActivity.onStop();
					break;
					
				// Message to display if there is Wifi
				case MESSAGE_WIFI:
					wifi = 1;
					String onRestartMessage = "Found WIFI(if downloading..download is resumed)";
					ToastMessage(onRestartMessage);
					thisActivity.onRestart();
					break;
					
				default:
					break;
			}
		}
	};
	
	/*
	 * If there is a progress dialog, dismiss it and set progressDialog to
	 * null.
	 */
	
	public void killProgressDialog()
	{
		if(progressDialog != null)
		{
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	/*
	 *Toast Message to User
	 */
	public void ToastMessage(String message)
	{
		if(message != null)
		{
			Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT).show();
		}
	}
	
}