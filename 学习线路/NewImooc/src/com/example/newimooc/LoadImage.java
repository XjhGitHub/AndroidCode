package com.example.newimooc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

/*
 * 异步加载图片
 */
public class LoadImage {

	private ImageView mImageView;
	private String mUrl;
	private ListView mListView;
	private Set<NewsAsyncTask> mTask;//保存需要加载项
	
	/*
	 * 使用缓存
	 */
	private LruCache<String, Bitmap> mCache;//相当map

	public LoadImage(ListView listView) {
		mListView = listView;
		mTask = new HashSet<LoadImage.NewsAsyncTask>();
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 4;
		mCache = new LruCache<String, Bitmap>(cacheSize) {
			// 重写
			@SuppressLint("NewApi")
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// 在每次存入缓存的时候调用
				return value.getByteCount();
			}

		};
	}
	
	
	public void addBitmatToCache(String url, Bitmap bitmap) {
		if (getBitmapFromCache(url) == null)
		{
			mCache.put(url, bitmap);
		}
	}
	
	public Bitmap getBitmapFromCache(String url){
		return mCache.get(url);
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (mImageView.getTag().equals(mUrl))
				mImageView.setImageBitmap((Bitmap) msg.obj);
		}

	};
	
	
	public void showImageByThread(ImageView imageView, final String url)
	{
		mImageView = imageView;
		mUrl = url;
		new Thread(){

			@Override
			public void run() {
				super.run();
				Bitmap bitmap = getBitmapFormUrl(url);
				Message message = Message.obtain();
				message.obj = bitmap;
				handler.sendMessage(message);
			}


		}.start();
	}
	
	public Bitmap getBitmapFormUrl(String urlString)
	{
		Bitmap bitmap;
		InputStream is = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			is = new BufferedInputStream(connection.getInputStream());
			bitmap = BitmapFactory.decodeStream(is);
			connection.disconnect();
			Thread.sleep(300);
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public void loadImages(int start, int end)
	{
		for (int i = start; i < end; i++) {
			String url = NewsAdapter.URLS[i];
			Bitmap bitmap = getBitmapFromCache(url);
			//如果缓存中没有则获取
			if (bitmap == null)
			{
//				new NewsAsyncTask(imageView, url).execute(url);
				NewsAsyncTask task = new NewsAsyncTask(url);
				task.execute(url);
				mTask.add(task);
			}
			else
			{
				ImageView imageView = (ImageView) mListView.findViewWithTag(url);
				imageView.setImageBitmap(bitmap);
			}
		}
	}
	
	public void showImageByAsycnTask(ImageView imageView, String url)
	{
		Bitmap bitmap = getBitmapFromCache(url);
		//如果缓存中没有则获取
		if (bitmap == null)
		{
//			new NewsAsyncTask(url).execute(url);
			imageView.setImageResource(R.drawable.ic_launcher);
		}
		else
		{
			imageView.setImageBitmap(bitmap);
		}
	}
	private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap>
	{
//		private ImageView mImageView;
		private String mUrl;
		
		public NewsAsyncTask(String url)
		{
//			mImageView = imageView;
			mUrl = url;
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = getBitmapFormUrl(url);
			if (bitmap != null)
			{
				//不在缓存中的图片添加到缓存
				addBitmatToCache(url, bitmap);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
			if (imageView != null && bitmap != null)
			{
				imageView.setImageBitmap(bitmap);
			}
			mTask.remove(this);
//			if (mImageView.getTag().equals(mUrl))
//			{
//				mImageView.setImageBitmap(result);
//			}
		}
		
		
		
		
	}
	public void cancelAllTasks() {
		if (mTask != null)
		{
			for (NewsAsyncTask task : mTask) {
				task.cancel(false);
			}
		}
	}
}
