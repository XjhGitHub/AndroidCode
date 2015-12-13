package com.example.testhandle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//handle用于主线程更新UI或消息处理
public class MainActivity extends Activity {

	private TextView textView;
	private Button button1;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			textView.setText("Update Text!" + msg.arg1);
			button1 = (Button) findViewById(R.id.button1);
		};
	};
	
	private Handler mCallbackHandler = new Handler(new Callback() {
		
		/*
		 * 截获消息
		 */
		@Override
		public boolean handleMessage(Message msg) {
			Toast.makeText(MainActivity.this, "handleMessage 1", Toast.LENGTH_SHORT).show();
			return false;
		}
	}){
		public void handleMessage(Message msg) {
			Toast.makeText(MainActivity.this, "handleMessage 2", Toast.LENGTH_SHORT).show();
		};
	};
	
	public void stopHandle(View view)
	{
//		mHandler.removeCallbacks(mRunable);
		mCallbackHandler.sendEmptyMessage(1);
	}
	
	private MyRunable mRunable = new MyRunable();
	private int index = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textview);
//		mHandler.postDelayed(mRunable, 1000);
		/*
		 * 1、post方法
		 */
		/*new Thread(){
			public void run() {
				try {
					Thread.sleep(1000);
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							textView.setText("Update Text!");
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();*/
		
		/*
		 * 2、sendMessage方法
		 */
		/*new Thread(){
			public void run() {
				try {
					Thread.sleep(1000);
					Message message = new Message();
					message.arg1 = 88;
					mHandler.sendMessage(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();*/
	}
private class MyRunable implements Runnable{
	@Override
	public void run() {
		index ++;
		index %= 10;
		textView.setText("Update Text!" + index);
		mHandler.postDelayed(mRunable, 1000);
	}
}


}
