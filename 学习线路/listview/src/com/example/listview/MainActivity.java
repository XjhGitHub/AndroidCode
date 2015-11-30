package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<ItemBean> list = new ArrayList<ItemBean>();
		for (int i = 0; i < 20; i++)
		{
			list.add(new ItemBean(R.drawable.ic_launcher,  "我是标题" + i,  "我是内容" + i));
		}
		
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(new MyBaseAdapter(this, list));
	}


}
