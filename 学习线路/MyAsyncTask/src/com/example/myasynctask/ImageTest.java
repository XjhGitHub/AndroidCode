package com.example.myasynctask;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageTest extends Activity {

	private ImageView image;
	private ProgressBar progressBar;
	private final String URL = 
			"http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_test);
		image = (ImageView) findViewById(R.id.img_test);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		new MyAsyncTask().execute(URL);
	}
	
	class MyAsyncTask extends AsyncTask<String, Void, Bitmap>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url = params[0];
			Bitmap bitmap = null;
			URLConnection connection;
			InputStream is;
			try {
				connection = new URL(url).openConnection();
				is = connection.getInputStream();
				Thread.sleep(3000);
				//Ω‚ Õ ‰»Î¡˜
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			image.setImageBitmap(result);
			progressBar.setVisibility(View.GONE);
		}
		
	}
}
