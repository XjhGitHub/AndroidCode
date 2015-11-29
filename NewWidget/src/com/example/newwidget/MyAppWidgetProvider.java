package com.example.newwidget;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyAppWidgetProvider extends AppWidgetProvider {

	private static final String TAG = "MyAppWidget";
	
	private static final boolean DEBUG = true;
	
	private static Intent MY_WIDGET_SERVICE = 
			new Intent("com.example.newwidget.MY_WIDGET_SERVICE");
	private static Intent UPDATE_PIC = 
			new Intent("com.example.newwidget.UPDATE_PIC");
	private static final String STR_UPDATE_PIC = "com.example.newwidget.UPDATE_PIC";
	
	private static final int BUTTON_SHOW = 1;
	 private static final int[] ARR_IMAGES = { 
	        R.drawable.sample_0, 
	        R.drawable.sample_1, 
	        R.drawable.sample_2, 
	        R.drawable.sample_3, 
	        R.drawable.sample_4, 
	        R.drawable.sample_5,
	        R.drawable.sample_6,
	        R.drawable.sample_7,
	    };
	 
	// ���� widget ��id��HashSet��ÿ�½�һ�� widget ����Ϊ�� widget ����һ�� id��
	 private static Set idsSet = new HashSet();
	 
	 //ÿ�����widgetʱ����
	 @Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		LOG("onUpdate appWidgetIds.length = "+ appWidgetIds.length);
		for (int appWidgetId: appWidgetIds)
		{
			idsSet.add(Integer.valueOf(appWidgetId));
		}
		
		printSet();
		
	}
	 
	

	



	//�� widget ��������� ���� �� widget �Ĵ�С���ı�ʱ�������� 
	 @SuppressLint("NewApi")
	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		// TODO Auto-generated method stub
		 LOG("onAppWidgetOptionsChanged appWidgetId = " + appWidgetId);
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
	}
	 
	// ��һ��widget������ʱ����  
	 @Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		LOG("onEnable");
		context.startService(MY_WIDGET_SERVICE);
	}
	 
	// ���һ��widget��ɾ��ʱ����
	 @Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		LOG("onDisable");
		context.stopService(MY_WIDGET_SERVICE);
	}
	 
	// widget��ɾ��ʱ����
	 @Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		LOG("onDeleted appWidgetIds.length="+appWidgetIds.length);
		for(int appWidgetId: appWidgetIds)
		{
			idsSet.remove(Integer.valueOf(appWidgetId));
		}
		printSet();
	}
	 
	// ���չ㲥�Ļص�����
	 @Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		final String action = intent.getAction();
		LOG("onReceive " + action);
		if (STR_UPDATE_PIC.equals(action))
		{
			//���¹㲥
			updateAllWidgets(context, AppWidgetManager.getInstance(context), idsSet);
		}
		else if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE))
			{
				//��������㲥
				Uri data = intent.getData();
				int buttonid = Integer.parseInt(data.getSchemeSpecificPart());
				if (buttonid == BUTTON_SHOW)
				{
					LOG("Button wifi click!");
					Toast.makeText(context, "Button wifi click", Toast.LENGTH_LONG)
					.show();
				}
			}
		super.onReceive(context, intent);

	}
	 
	 private void updateAllWidgets(Context context, AppWidgetManager appWidgetManager,
			Set set) {
		// TODO Auto-generated method stub
		LOG("updateAllWidgets");
		int appID;
		Iterator it = set.iterator();
		while (it.hasNext())
		{
			appID = ((Integer)it.next()).intValue();
			int index = new java.util.Random().nextInt(ARR_IMAGES.length);
			if (DEBUG)
			{
				LOG("updateAllWidgets:index = " + index);
			}
			
			RemoteViews remoteview = new RemoteViews(context.getPackageName(), R.layout.desktop_widget);
			remoteview.setImageViewResource(R.id.iv_show, ARR_IMAGES[index]);
			
			//���õ��������Ӧ��PendingIntent�㲥
			remoteview.setOnClickPendingIntent(R.id.btn_show, getPendingIntent(context, BUTTON_SHOW));
		
			appWidgetManager.updateAppWidget(appID, remoteview);
		}
	}


	private PendingIntent getPendingIntent(Context context, int buttonID) {
		// TODO Auto-generated method stub
		LOG("OnClick Button");
		Intent intent = new Intent();
		intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
		intent.setClass(context, MyAppWidgetProvider.class);
		intent.setData(Uri.parse("custom:"+buttonID));
		//���ʱ���������͹㲥
		PendingIntent pdIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		return pdIntent;
	}







	private void LOG(String string) {
			// TODO Auto-generated method stub
			Log.d(TAG, string);
		}
	 
	 private void printSet() {
			// TODO Auto-generated method stub
			if (DEBUG)
			{
				int index = 0;
				int size = idsSet.size();
				LOG("total size is = " + size);
				Iterator it = idsSet.iterator();
				while (it.hasNext())
				{
					LOG("" + index + "--" + ((Integer)it.next()).intValue());
					index++;
					
				}
			}
		}
	 
}
