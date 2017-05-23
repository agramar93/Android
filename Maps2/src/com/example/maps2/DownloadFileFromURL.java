package com.example.maps2;

import java.io.BufferedInputStream;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Environment;
import android.os.Message;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

class DownloadFileFromURL extends AsyncTask<String, Void, String> {
	private static final int Buffer_Size = 3000;
	MainActivity p_activity;
	public DownloadFileFromURL(MainActivity a){
		p_activity = a;
	}
	
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
			Message msg;
			
			
			try
			{
				url = new URL(downloadUrl[0]);
				conn = url.openConnection();
				conn.setUseCaches(false);
				fileSize = conn.getContentLength();
				
					
				 File rootDirectory = new File(Environment.getExternalStoragePublicDirectory(
	    					Environment.DIRECTORY_PICTURES), "My Pictures");
	    			if(!rootDirectory.exists()){
	    				rootDirectory.mkdirs();
	    			}
	    			
	    			file_name = URLUtil.guessFileName(downloadUrl[0], null, MimeTypeMap.getFileExtensionFromUrl(downloadUrl[0]));
	    			
	    			outFile = new File(rootDirectory, file_name);
	    			outFile.createNewFile();
				
				// notify the Start of Download
				int fileSizeInKB = fileSize / 1024;
				
				
				// start download
				inStream = new BufferedInputStream(conn.getInputStream());
				//outFile = new File(Environment.getExternalStorageDirectory() + "/" + file_name);
				fileStream = new FileOutputStream(outFile);
				outStream = new BufferedOutputStream(fileStream, Buffer_Size);
				byte[] data = new byte[Buffer_Size];
				int bytesRead = 0, totalRead = 0;
				while((bytesRead = inStream.read(data, 0, data.length)) >= 0)
				{
					outStream.write(data, 0, bytesRead);
					
					// update progress bar
					totalRead += bytesRead;
					int total_bytes = totalRead / 1024;
					
				
				}
				
				//Close Everything 
				outStream.close();
				fileStream.close();
				inStream.close();
				
				
				
					
				
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(MalformedURLException e)
			{
				e.printStackTrace();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}