package com.example.testdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment implements OnClickListener{

	private View mView;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.first_main, null);
		initView();
		return mView;
	}

	private void initView() {
		button1 = (Button) mView.findViewById(R.id.button1);
		button1.setOnClickListener(this);

		button2 = (Button) mView.findViewById(R.id.button2);
		button2.setOnClickListener(this);
		
		button3 = (Button) mView.findViewById(R.id.button3);
		button3.setOnClickListener(this);

		button4 = (Button) mView.findViewById(R.id.button4);
		button4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
//			startActivity(new Intent(this, ToastActivity.class));
			break;
		case R.id.button2:
//			startActivity(new Intent(this, DialogActivity.class));
			break;
		case R.id.button3:
//			startActivity(new Intent(this, NotifyActivity.class));
			break;
		case R.id.button4:
//			startActivity(new Intent(this, MenuActivity.class));
			break;
		default:
			break;
		}
	}
}
