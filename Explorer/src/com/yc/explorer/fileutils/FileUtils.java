package com.yc.explorer.fileutils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;

/**
 *@Author Administrator
 *@Time 2016-2-29 下午10:04:22
 */
public class FileUtils {

	private static final String TAG = "FileMainActivity";
	/**
	 * 获取指定文件夹下的文件
	 * @param path
	 * @param bCheckHiden
	 * @return
	 */
	public List<String> getFileFromPath(String path, boolean bCheckHiden)
	{
		List<String> list = new ArrayList<String>();
		List<String> dirList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		File fileroot = new File(path);
		LOG("list fileroot = " + fileroot);
		File[] files = fileroot.listFiles();
		
		for (File file : files) {
			if (bCheckHiden)
			{
				if (file.isHidden())
				{
					continue;
				}
			}
			if (file.isDirectory())
			{
//				dirList.add(file.getPath());
				dirList.add(file.getName());
			}
			else
			{
//				fileList.add(file.getPath());
				fileList.add(file.getName());
			}
		}
		Collections.sort(dirList);
		Collections.sort(fileList);
		for (String dir : dirList)
		{
			list.add(dir);
		}
		for (String file : fileList)
		{
			list.add(file);
		}
		LOG("FileSize = " + list.size());
		return list;
	}
	
	/**
	 * 获取指定文件夹下的所有文件
	 * @param path
	 * @param bCheckHiden
	 * @return
	 */
	public List<String> getAllFileFromPath(String path, boolean bCheckHiden)
	{
		List<String> list = new ArrayList<String>();
		List<String> rootList = new ArrayList<String>();
		rootList = getFileFromPath(path, bCheckHiden);
		return list;
	}
	
	private void LOG(String string)
	{
		Log.i(TAG, string);
	}
}
