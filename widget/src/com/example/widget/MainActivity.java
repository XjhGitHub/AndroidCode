package com.example.widget;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*
 * 一、绘制widget布局-->widget.xml
 * 二、配置widget基本属性-->widgetconfig.xml
 * 三、定义AppWidgetProvider-->widgetProvider
 * 四、提供ConfigarationActivity
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	

}
