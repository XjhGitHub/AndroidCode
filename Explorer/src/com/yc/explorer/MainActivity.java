package com.yc.explorer;

import java.util.List;

import com.yc.explorer.fileutils.FileUtils;
import com.yc.explorer.fileutils.SDCardFileObserver;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String TAG = "FileMainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FileUtils fileUtils = new FileUtils();
		List<String> list = fileUtils.getFileFromPath("/mnt/sdcard", true);
		new SDCardFileObserver("/mnt/sdcard").startWatching();
		for (String file: list)
		{
			LOG(file);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void LOG(String string)
	{
		Log.i(TAG, string);
	}
	
}
