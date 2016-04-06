package com.example.hw02_tbbtquiz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Assignment No:  HomeWork02 
 * File Name: SplashScreenActivity.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
		mediaPlayer.start();
		mediaPlayer
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						finish();
						Intent intent = new Intent(getBaseContext(),
								WelcomeActivity.class);
						startActivity(intent);
						mediaPlayer.release();

					}
				});
	}
}
