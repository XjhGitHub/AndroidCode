package com.example.testdemo.first.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

/*
 * 1、可静态加载（xml加载）
 * 2、可动态加载（代码加载）
 */
public class ViewFlipperActivity extends Activity {

	private ViewFlipper mViewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);
	}
}
