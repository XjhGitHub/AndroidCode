package com.example.myasynctask;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarTest extends Activity {

	private ProgressBar progressBar;
	private MyAsyncTask myAsyncTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_bar);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		myAsyncTask = new MyAsyncTask();
		myAsyncTask.execute();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (myAsyncTask != null && myAsyncTask.getStatus() == AsyncTask.Status.RUNNING)
		{
			myAsyncTask.cancel(true);
		}
	}
	
	class MyAsyncTask extends AsyncTask<Void, Integer, Void>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressBar.setMax(50);
		}


		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 50; i++) {
				if (isCancelled())
				{
					break;
				}
				publishProgress(i);
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			if (isCancelled())
			{
				return;
			}
			progressBar.setProgress(values[0]);
		}
		
	}
}
