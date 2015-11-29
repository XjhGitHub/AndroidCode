package com.example.newwidget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyAppWidgetService extends Service {

	private static Intent UPDATE_PIC = 
			new Intent("com.example.newwidget.UPDATE_PIC");
	private static final int THREAD_TIME = 5*1000;
	private Context mContext;
	private UpdateThread myUpdateThread;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		myUpdateThread = new UpdateThread();
		myUpdateThread.start();
		mContext = this.getApplicationContext();
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//中断线程相当停止线程
		if (myUpdateThread != null)
		{
			myUpdateThread.interrupt();
		}
		super.onDestroy();
	}
	
	private class UpdateThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while (true)
			{
				mContext.sendBroadcast(UPDATE_PIC);
				try {
					Thread.sleep(THREAD_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
