package com.example.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class CustomCamera extends Activity implements SurfaceHolder.Callback{

	private Camera mCamera;
	private SurfaceView mPreView;
	private SurfaceHolder mHolder;
	
	
	private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			SimpleDateFormat sDataFormat = new SimpleDateFormat("yyyyMMdd_hhmmss") ;
			String path = "/sdcard/IMG_" + sDataFormat.format(new Date(System.currentTimeMillis())) + ".png";
			File tempFile = new File(path);
//			File tempFile = new File("/sdcard/temp.png");
			try {
				FileOutputStream fos = new FileOutputStream(tempFile);
					fos.write(data);
					fos.close();
					
					Intent intent = new Intent(CustomCamera.this, ResultActivity.class);
					intent.putExtra("picPath", tempFile.getAbsolutePath());
					startActivity(intent);
					CustomCamera.this.finish();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom);
		mPreView = (SurfaceView) findViewById(R.id.preview);
		mHolder = mPreView.getHolder();
		mHolder.addCallback(this);
		mPreView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCamera.autoFocus(null);
			}
		});
	}
	
	public void capture(View view)
	{
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPictureFormat(ImageFormat.JPEG);
		parameters.setPreviewSize(800, 400);
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		//回调
		mCamera.autoFocus(new Camera.AutoFocusCallback() {
			
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				// TODO Auto-generated method stub
				if (success)
				{
					mCamera.takePicture(null, null, mPictureCallback);
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mCamera == null)
		{
			mCamera = getCamera();
			if (mHolder != null)
			{
				setStartPreview(mCamera, mHolder);
			}
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		releaseCamera();
	}
	
	
	/*
	 * 获取Camera
	 */
	private Camera getCamera()
	{
		Camera camera;
		try {
			camera = Camera.open();
		} catch (Exception e) {
			// TODO: handle exception
			camera = null;
		}
		return camera;
		
	}
	
	/*
	 * 开始预览相机内容
	 */
	private void setStartPreview(Camera camera, SurfaceHolder holder)
	{
		try {
			camera.setPreviewDisplay(holder);
			//将系统Camera预览角度
			camera.setDisplayOrientation(90);
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 释放Camera
	 */
	
	private void releaseCamera()
	{
		if (mCamera != null)
		{
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
			
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		setStartPreview(mCamera, mHolder);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		setStartPreview(mCamera, mHolder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		releaseCamera();
	}
	
	
}
