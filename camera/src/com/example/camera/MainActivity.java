package com.example.camera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final int REQ_1 = 1;
	private static final int REQ_2 = 2;
	
	private String mFilePath = null;
	
	private ImageView img = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView) findViewById(R.id.img_get);
		
		mFilePath = Environment.getExternalStorageDirectory().getPath();
		mFilePath = mFilePath + "/tmp.png";
		
	}

	public void startCamera1(View view)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQ_1);
	}
	
	public void startCamera2(View view)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri photoUri = Uri.fromFile(new File(mFilePath));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		startActivityForResult(intent, REQ_2);
	}
	
	/*
	 * 定制的Camera
	 */
	public void customCamera(View view)
	{
		startActivity(new Intent(MainActivity.this, CustomCamera.class));
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)
		{
			if (requestCode == REQ_1)
			{
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");//返回缩略图
				img.setImageBitmap(bitmap);
			}
			else if (requestCode == REQ_2)
			{
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(mFilePath);
					Bitmap bitmap = BitmapFactory.decodeStream(fis);
					img.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
