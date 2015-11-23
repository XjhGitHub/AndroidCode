package com.example.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class widgetProvider extends AppWidgetProvider {

	//widget������Ļ�Ƴ�
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}
	
	//���һ��widget����Ļ�Ƴ�����
	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		context.stopService(new Intent(context, TimerServer.class));
	}
	
	//��һ��widget��ӵ���Ļ�ϵ���
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		
		context.startService(new Intent(context, TimerServer.class));
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}
	
	//ˢ��ʱ����
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		//ͨ��remoteView��AppWidgetManager
	}
}
