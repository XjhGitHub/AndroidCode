package com.example.newimooc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final static String TAG = "NewsImooc";
	private final static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
	private ListView mListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.lv_main);
		
		new NewAsyncTask().execute(URL);
	}

	//½«JSON×ª»¯
	private List<NewsBean> getJsonData(String url) {
		// TODO Auto-generated method stub
		List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
		try {
			String jsonString = readStream(new URL(url).openStream());
//			LOG(jsonString);
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				NewsBean newsBean = new NewsBean();
				newsBean.newsIconUrl = jsonObject.getString("picSmall");
				newsBean.newsTitle = jsonObject.getString("name");
				newsBean.newsContent = jsonObject.getString("description");
				newsBeanList.add(newsBean);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG(newsBeanList.toString());
		
		return newsBeanList;
	}

	private String readStream(InputStream is)
	{
		String result = "";
		InputStreamReader isr;
		try {
			String line = "";
			isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	class NewAsyncTask extends AsyncTask<String, Void, List<NewsBean>>
	{

		@Override
		protected List<NewsBean> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return getJsonData(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<NewsBean> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			NewsAdapter adapter = new NewsAdapter(MainActivity.this, result, mListView);
			mListView.setAdapter(adapter);
		}

		
	}
	
	private void LOG(String string)
	{
		Log.i(TAG, string);
	}
}
