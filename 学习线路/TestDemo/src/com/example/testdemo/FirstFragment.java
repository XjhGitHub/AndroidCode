package com.example.testdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testdemo.first.base.SpinnerActivity;
import com.example.testdemo.first.gridview.GridViewActivity;
import com.example.testdemo.first.listview.ListViewActivity;
import com.example.testdemo.first.picker.PickerActivity;

public class FirstFragment extends Fragment implements OnClickListener{

	private View mView;
	
	private Button fir_button1;
	private Button fir_button2;
	private Button fir_button3;
	private Button fir_button4;
	
	private Context mContext;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.first_main, null);
		mContext = mView.getContext();
		initView();
		return mView;
	}

	private void initView() {
		fir_button1 = (Button) mView.findViewById(R.id.fir_button1);
		fir_button1.setOnClickListener(this);

		fir_button2 = (Button) mView.findViewById(R.id.fir_button2);
		fir_button2.setOnClickListener(this);
		
		fir_button3 = (Button) mView.findViewById(R.id.fir_button3);
		fir_button3.setOnClickListener(this);

		fir_button4 = (Button) mView.findViewById(R.id.fir_button4);
		fir_button4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fir_button1:
			startActivity(new Intent(mContext, ListViewActivity.class));
			break;
		case R.id.fir_button2:
			startActivity(new Intent(mContext, PickerActivity.class));
			break;
		case R.id.fir_button3:
			startActivity(new Intent(mContext, GridViewActivity.class));
			break;
		case R.id.fir_button4:
			startActivity(new Intent(mContext, SpinnerActivity.class));
			break;
		default:
			break;
		}
	}
}
