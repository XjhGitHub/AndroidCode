package com.example.testhandle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperActivity extends Activity {

	private static final String TAG = "LooperActivity";
	Handler mHandler/* = new Handler()*/;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//threadlocal set get
		
		//ActivityThread->mainThread->Looper-->messagequeue
		
//		mHandler.sendEmptyMessage(1);
		MyThread myThread = new MyThread();
		myThread.start();
//		try {
//			Thread.sleep(1000);
//			myThread.handle.sendEmptyMessage(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		mHandler = new Handler(myThread.looper){//多线程looper会空，改用HandleThread可以
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
			}
		};
	}
	
	class MyThread extends Thread{
		public Handler handle;//线程中创建handle
		public Looper looper;
		@Override
		public void run() {
			Looper.prepare();
			looper = Looper.myLooper();
			handle = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					LOG("currentThread" + Thread.currentThread());
				}
			};
			Looper.loop();
		}
	}
	
	private void LOG(String string)
	{
		Log.i(TAG, string);
	}
}
