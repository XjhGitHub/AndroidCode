package com.example.myasynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new MyAsyncTask().execute();
	}
	
	public void loadImg(View view)
	{
		startActivity(new Intent(this, ImageTest.class));
	}
	
	public void progress(View view)
	{
		startActivity(new Intent(this, ProgressBarTest.class));
	}


}
