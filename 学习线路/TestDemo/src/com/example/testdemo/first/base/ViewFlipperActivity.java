package com.example.testdemo.first.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

/*
 * 1���ɾ�̬���أ�xml���أ�
 * 2���ɶ�̬���أ�������أ�
 */
public class ViewFlipperActivity extends Activity {

	private ViewFlipper mViewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);
	}
}
