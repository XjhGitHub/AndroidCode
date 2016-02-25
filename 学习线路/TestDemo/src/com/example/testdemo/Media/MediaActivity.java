package com.example.testdemo.Media;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

import com.example.testdemo.R;

/**
 * @Author Administrator
 * @Time 2016-2-25 ÏÂÎç10:36:40
 */
public class MediaActivity extends Activity {

	private SoundPool mSoundPool;
	private int mSoundID;

	private MediaPlayer mPlayer = null;
	private SurfaceView mSurfaceView;
	private SurfaceHolder mHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_test);
		mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		mSoundID = mSoundPool.load(this, R.raw.sound1, 1);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		mHolder = mSurfaceView.getHolder();
		mHolder.addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (mPlayer != null)
				{
					mPlayer.release();
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				mPlayer = MediaPlayer.create(MediaActivity.this, R.raw.video);
				try {
					mPlayer.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mPlayer.setDisplay(mHolder);
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				
			}
		});
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.soundpool_play_sound:
			mSoundPool.play(mSoundID, 1.0f, 1.0f, 0, 0, 1.0f);
			break;
		case R.id.mediaplayer_play_sound:
			mPlayer = MediaPlayer.create(this, R.raw.sound);
			try {
				mPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (mPlayer != null)
			{
				mPlayer.start();
			}
			break;
		case R.id.mediaplayer_pause_sound:
			if (mPlayer != null)
			{
				mPlayer.release();
			}
			break;
		case R.id.mediaplayer_play_video:
			if (mPlayer != null)
			{
				mPlayer.start();
			}
			break;
		case R.id.mediaplayer_pause_video:
			if (mPlayer != null)
			{
				mPlayer.pause();
			}
			break;
		case R.id.videoview_play_video:
			startActivity(new Intent(this, VideoViewActivity.class));
			break;
		default:
			break;
		}
	}
}
