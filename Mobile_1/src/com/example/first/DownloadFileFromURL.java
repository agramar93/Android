package com.example.first;

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
			
			// we're going to connect now
			msg = Message.obtain(p_activity.activityHandler,
					MainActivity.Connection_Started,
					0, 0, downloadUrl);
			p_activity.activityHandler.sendMessage(msg);
			
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
				msg = Message.obtain(p_activity.activityHandler,
						MainActivity.Download_Began,
						fileSizeInKB, 0, file_name);
				p_activity.activityHandler.sendMessage(msg);
				
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
					
					msg = Message.obtain(p_activity.activityHandler,
							MainActivity.Update_Progress_Bar,
							total_bytes, 0);
					p_activity.activityHandler.sendMessage(msg);
				}
				
				//Close Everything 
				outStream.close();
				fileStream.close();
				inStream.close();
				
				
				
					// File Downloaded
					msg = Message.obtain(p_activity.activityHandler,
							MainActivity.Download_Complete);
					p_activity.activityHandler.sendMessage(msg);
				
			}
			catch(FileNotFoundException e)
			{
				String errMsg = p_activity.getString(R.string.error_message_file_not_found);
				msg = Message.obtain(p_activity.activityHandler,
						MainActivity.Message_Error,
						0, 0, errMsg);
				p_activity.activityHandler.sendMessage(msg); 
			}
			catch(MalformedURLException e)
			{
				String errMsg = p_activity.getString(R.string.error_message_bad_url);
				msg = Message.obtain(p_activity.activityHandler,
						MainActivity.Message_Error,
						0, 0, errMsg);
				p_activity.activityHandler.sendMessage(msg);
			}
			
			catch(Exception e)
			{
				String errMsg = p_activity.getString(R.string.error_message_general);
				msg = Message.obtain(p_activity.activityHandler,
						MainActivity.Message_Error,
						0, 0, errMsg);
				p_activity.activityHandler.sendMessage(msg); 
			}
			return null;
		}
	}