package com.example.camera;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class ResultActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		String path = getIntent().getStringExtra("picPath");
		ImageView img = (ImageView) findViewById(R.id.pic);
//		img.setImageBitmap( BitmapFactory.decodeFile(path));
		
		try {
			FileInputStream fis = new FileInputStream(path);
			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			Matrix matrix = new Matrix();
			matrix.setRotate(90);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, 
					bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			img.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
