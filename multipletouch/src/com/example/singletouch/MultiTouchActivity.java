package com.example.singletouch;

import android.app.Activity;
import android.os.Bundle;

public class MultiTouchActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MultiTouchEventView(this, null));
	}
}