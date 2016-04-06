package com.example.inclass03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Assignment No: InClass Assignment03  
 * File Name: DisplayActivity.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class DisplayActivity extends Activity implements View.OnClickListener {

	TextView nameTV, emailTV, PLTV, accTV, moodTV;
	static final String SEARCHABLE_KEY = "Searchable";
	static final String UNSEARCHABLE_KEY = "UnSearchable";
	static final String EDIT_TYPE_KEY = "EDIT_TYPE";
	Boolean acc = false;
	ImageView iv1, iv2, iv3, iv4, iv5;
	Integer mood;
	Integer REQ_CODE = 101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		iv1 = (ImageView) findViewById(R.id.imageView1);
		iv1.setOnClickListener(this);
		iv2 = (ImageView) findViewById(R.id.imageView2);
		iv2.setOnClickListener(this);
		iv3 = (ImageView) findViewById(R.id.imageView3);
		iv3.setOnClickListener(this);
		iv4 = (ImageView) findViewById(R.id.imageView4);
		iv4.setOnClickListener(this);
		iv5 = (ImageView) findViewById(R.id.imageView5);
		iv5.setOnClickListener(this);
		nameTV = (TextView) findViewById(R.id.textView2);
		emailTV = (TextView) findViewById(R.id.textView4);
		PLTV = (TextView) findViewById(R.id.textView6);
		accTV = (TextView) findViewById(R.id.textView8);
		moodTV = (TextView) findViewById(R.id.textView10);
		//nameTV.setText(getIntent().getExtras().getString(MainActivity.NAME_KEY));
		nameTV.setText(((Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY)).getName());
		emailTV.setText(((Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY)).getEmailAddress());
		PLTV.setText(((Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY)).getProgrammingLanguage());
		//emailTV.setText(getIntent().getExtras().getString(
		//		MainActivity.EMAIL_KEY));
		//PLTV.setText(getIntent().getExtras().getString(MainActivity.PL_KEY));
		//acc = getIntent().getExtras().getBoolean(MainActivity.ACCOUNT_KEY);
		if (((Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY)).getAccountState())
			accTV.setText(SEARCHABLE_KEY);
		else {
			accTV.setText(UNSEARCHABLE_KEY);
		}
		/*mood = getIntent().getExtras().getInt(MainActivity.MOOD_KEY);
		moodTV.setText(Integer.toString(mood) + " % Positive");*/
		acc=((Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY)).getAccountState();
		mood = ((Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY)).getMood();
		moodTV.setText(Integer.toString(mood) + " % Positive");
	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent("com.example.inclass03.intent.action.EDIT");
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		if (v.getId() == R.id.imageView1) {
			intent.putExtra(EDIT_TYPE_KEY, MainActivity.NAME_KEY);
			intent.putExtra(MainActivity.NAME_KEY, nameTV.getText().toString());
		} else if (v.getId() == R.id.imageView2) {
			intent.putExtra(EDIT_TYPE_KEY, MainActivity.EMAIL_KEY);
			intent.putExtra(MainActivity.EMAIL_KEY, emailTV.getText()
					.toString());
		} else if (v.getId() == R.id.imageView3) {
			intent.putExtra(EDIT_TYPE_KEY, MainActivity.PL_KEY);
			intent.putExtra(MainActivity.PL_KEY, PLTV.getText().toString());
		} else if (v.getId() == R.id.imageView4) {
			intent.putExtra(EDIT_TYPE_KEY, MainActivity.ACCOUNT_KEY);
			intent.putExtra(MainActivity.ACCOUNT_KEY, acc);
		} else if (v.getId() == R.id.imageView5) {
			intent.putExtra(EDIT_TYPE_KEY, MainActivity.MOOD_KEY);
			intent.putExtra(MainActivity.MOOD_KEY, mood);
		}
		startActivityForResult(intent, REQ_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			if (requestCode == REQ_CODE) {
				if (data.getExtras().getString(EDIT_TYPE_KEY)
						.equals(MainActivity.NAME_KEY)) {
					nameTV.setText(data.getExtras().getString(
							MainActivity.NAME_KEY));
				}else if (data.getExtras().getString(EDIT_TYPE_KEY)
						.equals(MainActivity.EMAIL_KEY)) {
					emailTV.setText(data.getExtras().getString(
							MainActivity.EMAIL_KEY));
				}else if (data.getExtras().getString(EDIT_TYPE_KEY)
						.equals(MainActivity.PL_KEY)) {
					PLTV.setText(data.getExtras().getString(
							MainActivity.PL_KEY));
				}else if (data.getExtras().getString(EDIT_TYPE_KEY)
						.equals(MainActivity.ACCOUNT_KEY)) {
					acc = data.getExtras().getBoolean(MainActivity.ACCOUNT_KEY);
					if (acc)
						accTV.setText(SEARCHABLE_KEY);
					else {
						accTV.setText(UNSEARCHABLE_KEY);
					}
				}else if (data.getExtras().getString(EDIT_TYPE_KEY)
						.equals(MainActivity.MOOD_KEY)) {
					mood=data.getExtras().getInt(MainActivity.MOOD_KEY);
					moodTV.setText(mood+" % Positive");
				}
			}
		}
	}
}
