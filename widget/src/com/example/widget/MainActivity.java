package com.example.widget;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*
 * һ������widget����-->widget.xml
 * ��������widget��������-->widgetconfig.xml
 * ��������AppWidgetProvider-->widgetProvider
 * �ġ��ṩConfigarationActivity
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	

}
