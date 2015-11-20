package com.example.slidertab;

import java.util.Random;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabWidget;
import android.widget.TextView;

public class SliderTab extends FragmentActivity {

	private TabWidget tabWidget = null;
	private ViewPager viewPager = null;
	
	private PagerAdapter mPagerAdapter = null;
	
	
	private String[] name = {"first", "second", "third"};
	private Button[] mBtTabs = new Button[name.length];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabWidget = (TabWidget) findViewById(R.id.tabWidget);
//		tabWidget.setStripEnabled(false);	//�������»���
		
		mBtTabs[0] = new Button(this);
		mBtTabs[0].setFocusable(true);
		mBtTabs[0].setText(name[0]);
		mBtTabs[0].setBackgroundResource(R.drawable.bt_selector);
		mBtTabs[0].setTextColor(android.graphics.Color.BLACK);
		tabWidget.addView(mBtTabs[0]);
		/*
		 * Listener������mTabWidget.addView()֮���ټ��룬���ڸ���Ĭ�ϵ�Listener
		 * mTabWidget.addView()��Ĭ�ϵ�Listenerû��NullPointer��⡣
		 */
		mBtTabs[0].setOnClickListener(mTabClickListener);
		
		mBtTabs[1] = new Button(this);
		mBtTabs[1].setFocusable(true);
		mBtTabs[1].setText(name[1]);
		mBtTabs[1].setTextColor(android.graphics.Color.BLACK);
		tabWidget.addView(mBtTabs[1]);
		mBtTabs[1].setOnClickListener(mTabClickListener);
		
		mBtTabs[2] = new Button(this);
		mBtTabs[2].setFocusable(true);
		mBtTabs[2].setText(name[2]);
		mBtTabs[2].setTextColor(android.graphics.Color.BLACK);
		tabWidget.addView(mBtTabs[2]);
		mBtTabs[2].setOnClickListener(mTabClickListener);
		
		
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		
		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mPagerAdapter);
		viewPager.setOnPageChangeListener(mPageChangeListener);
		viewPager.setCurrentItem(0);
	
		
	}


	private OnClickListener mTabClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO �Զ����ɵķ������
			if (v == mBtTabs[0])
			{
				viewPager.setCurrentItem(0);
			}
			else if (v == mBtTabs[1])
			{
				viewPager.setCurrentItem(1);
			}
			else if (v == mBtTabs[2])
			{
				viewPager.setCurrentItem(2);
			}
		}
	};
	
	private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int item) {
			// TODO Auto-generated method stub
			tabWidget.setCurrentTab(item);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	}; 
	
	private class MyPagerAdapter extends FragmentStatePagerAdapter
	{

		public MyPagerAdapter(FragmentManager fm) {
			// TODO �Զ����ɵĹ��캯�����
			super(fm);
		}
		
		@Override
		public Fragment getItem(int item) {
			// TODO �Զ����ɵķ������
			return MyFragment.creat(name[item]);
		}

		@Override
		public int getCount() {
			// TODO �Զ����ɵķ������
			return name.length;
		}
		
	};
	
	public static class MyFragment extends Fragment
	{
		public static MyFragment creat(String name)
		{
			MyFragment f = new MyFragment();
			Bundle b = new Bundle();
			b.putString("name", name);
			f.setArguments(b);
			return f;
		}
		
		@SuppressLint("NewApi")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO �Զ����ɵķ������
			Random r = new Random(System.currentTimeMillis());
			Bundle b = getArguments();
			
			View view = inflater.inflate(R.layout.fragment_viewpager, null);
			view.setBackgroundColor(r.nextInt()>>8 | 0xFF << 24);
			
			TextView tv = (TextView) view.findViewById(R.id.tv_pager);
			tv.setTextColor(r.nextInt()>>8 | 0xFF << 24);
			tv.setBackgroundColor(r.nextInt()>>8 | 0xFF << 24);
			tv.setText(b.getString("name", ""));
			return view;
		}
	};
}
