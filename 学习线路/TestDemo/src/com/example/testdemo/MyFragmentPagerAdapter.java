package com.example.testdemo;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> mFragments;
	private List<String> mTitle;
	public MyFragmentPagerAdapter(FragmentManager fm, 
			List<Fragment> fragments,
			List<String> title) {
		super(fm);
		mFragments = fragments;
		mTitle = title;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}
	
	/*
	 * ÃÌº”±ÍÃ‚(non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
	 */
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return mTitle.get(position);
	}

}
