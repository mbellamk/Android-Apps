package com.example.hw02_tbbtquiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Assignment No:  HomeWork02 
 * File Name: ResultActivity.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class ResultActivity extends Activity {

	SeekBar sb;
	TextView tv;
	ProgressBar pb;
	Button quit, tryAgain;
	ArrayList<Question> questions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Integer totalQuestions = getIntent().getExtras().getInt(
				QuizActivity.TOTAL_QUESTIONS_KEY);
		Integer correctAnswers = getIntent().getExtras().getInt(
				QuizActivity.CORRECT_ANSWERS_KEY);
		tv = (TextView) findViewById(R.id.textView3);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		quit = (Button) findViewById(R.id.button1);
		tryAgain = (Button) findViewById(R.id.button2);
		Integer percentage = (Integer) ((correctAnswers * 100) / totalQuestions);
		pb.setProgress(percentage);
		tv.setText(percentage + "%");
		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						WelcomeActivity.class);
				startActivity(intent);

			}
		});
		tryAgain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getBaseContext(),QuizActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.putParcelableArrayListExtra(WelcomeActivity.QUESTIONS_KEY, getIntent().getParcelableArrayListExtra(WelcomeActivity.QUESTIONS_KEY));
				startActivity(intent);

			}
		});
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	Intent intent=new Intent(getBaseContext(),QuizActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.putParcelableArrayListExtra(WelcomeActivity.QUESTIONS_KEY, getIntent().getParcelableArrayListExtra(WelcomeActivity.QUESTIONS_KEY));
			startActivity(intent);
            return true;
        }
        return false;
    }
}
