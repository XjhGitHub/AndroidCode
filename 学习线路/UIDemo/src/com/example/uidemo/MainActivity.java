package com.example.uidemo;

import com.example.uidemo.dialog.DialogActivity;
import com.example.uidemo.menu.MenuActivity;
import com.example.uidemo.notifycation.NotifyActivity;
import com.example.uidemo.toast.ToastActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViewz();
	}

	private void initViewz() {
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);

		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			startActivity(new Intent(this, ToastActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(this, DialogActivity.class));
			break;
		case R.id.button3:
			startActivity(new Intent(this, NotifyActivity.class));
			break;
		case R.id.button4:
			startActivity(new Intent(this, MenuActivity.class));
			break;
		default:
			break;
		}
	}

}
