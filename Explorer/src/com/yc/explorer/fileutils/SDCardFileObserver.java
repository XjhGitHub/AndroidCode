package com.yc.explorer.fileutils;

import android.os.FileObserver;
import android.util.Log;

/**
 * @Author Administrator
 * @Time 2016-2-29 下午11:33:43
 */
public class SDCardFileObserver extends FileObserver {

	private static final String TAG = "SDCardFileObserver";

	public SDCardFileObserver(String path) {
		super(path);
	}

	@Override
	public void onEvent(int event, String path) {
		LOG("path = " + path + ", event = " + event);
		switch (event) {
		case FileObserver.CREATE:

			break;
		case FileObserver.DELETE:

			break;

		default:
			break;
		}
	}

	private void LOG(String string)
	{
		Log.i(TAG, string);
	}
}
