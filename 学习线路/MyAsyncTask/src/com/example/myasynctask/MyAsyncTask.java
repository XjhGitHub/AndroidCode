package com.example.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

	private static final String TAG = "MyAsyncTask";
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		LOG("doInBackground");
		publishProgress();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		LOG("onPostExecute");
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		LOG("onPreExecute");
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		LOG("onProgressUpdate");
	}

	private void LOG(String string)
	{
		Log.i(TAG, string);
	}
}
