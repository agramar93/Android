package com.example.finalproject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


class DownloadFileFromURL extends AsyncTask<String, Void, String> {
	private static final int Buffer_Size = 3000;
	
	private final String TAG = this.getClass().getSimpleName();

	
	/*
	 * Downloading file in background
	 * */
	
	@Override
	public String doInBackground(String... downloadUrl) {
		
			URL url;
			URLConnection conn;
			int fileSize;
			String file_name = null;
			BufferedInputStream inStream;
			BufferedOutputStream outStream;
			File outFile;
			FileOutputStream fileStream;
	
			
			
			
			try
			{
				url = new URL(downloadUrl[0]);
				conn = url.openConnection();
				conn.setUseCaches(false);
				fileSize = conn.getContentLength();
				
				/*
				 File rootDirectory = new File(Environment.getExternalStoragePublicDirectory(
	    					Environment.DIRECTORY_PICTURES), "My Pictures");
	    			if(!rootDirectory.exists()){
	    				rootDirectory.mkdirs();
	    			}
	    			
	    			
	    					//URLUtil.guessFileName(downloadUrl[0], null, MimeTypeMap.getFileExtensionFromUrl(downloadUrl[0]));
	    			
	    			outFile = new File(rootDirectory, file_name);
	    			outFile.createNewFile();
				
		
				*/
				file_name = "mickey_2015.jpg";
				// start download
				inStream = new BufferedInputStream(conn.getInputStream());
				outFile = new File(Environment.getExternalStorageDirectory() + "/" + file_name);
				fileStream = new FileOutputStream(outFile);
				outStream = new BufferedOutputStream(fileStream, Buffer_Size);
				byte[] data = new byte[Buffer_Size];
				int bytesRead = 0;
				while((bytesRead = inStream.read(data, 0, data.length)) >= 0)
				{
					outStream.write(data, 0, bytesRead);
					
				}
				
				//Close Everything 
				outStream.close();
				fileStream.close();
				inStream.close();				
				
					
				
			}
			
			
			catch(Exception e)
			{
				Log.i("DOWNLOAD_FAILURE","DOWNNLOAD_FAILURE ");
		 		Log.i(TAG, "DOWNLOAD_FAILURE");
			}
			return "mickey";
		}
	
		
}