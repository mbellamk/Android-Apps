package com.example.hw02_tbbtquiz;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Assignment No: HomeWork02 File Name: QuizActivity.java
 * 
 * Team Members: 1. Manju Raghavendra Bellamkonda 2. Prem kumar Murugesan 3.
 * Madhavi bhat
 */
public class QuizActivity extends Activity {

	RadioGroup rg;
	ImageView iv;
	Button quit, next;
	TextView tvQuesNumber, tvTime, tvQuestion, loaderMsg;
	ArrayList<Question> questions;
	Integer currentQuestion = 0;
	ProgressBar loader;
	CountDownTimer countDownTimer = null;
	Integer totalQuestions, correctQuestions = 0;
	Question currentQuestionObj = null;
	static final String CORRECT_ANSWERS_KEY = "correct_answers";
	static final String TOTAL_QUESTIONS_KEY = "total_questions";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		iv = (ImageView) findViewById(R.id.imageView2);
		quit = (Button) findViewById(R.id.button3);
		next = (Button) findViewById(R.id.button4);
		tvQuesNumber = (TextView) findViewById(R.id.textView1);
		tvTime = (TextView) findViewById(R.id.textView2);
		loaderMsg = (TextView) findViewById(R.id.textView3);
		tvQuestion = (TextView) findViewById(R.id.textView4);
		loader = (ProgressBar) findViewById(R.id.progressBar2);
		questions = getIntent().getParcelableArrayListExtra(
				WelcomeActivity.QUESTIONS_KEY);
		populateQuestion();
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (rg.getCheckedRadioButtonId() != -1
						&& rg.getCheckedRadioButtonId() == currentQuestionObj
								.getAnswer()) {
					correctQuestions++;
				}
				populateQuestion();
			}
		});
		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						WelcomeActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});

	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	Intent intent=new Intent(getBaseContext(),WelcomeActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
            return true;
        }
        return false;
    }
	
	public void populateQuestion() {
		if (countDownTimer != null)
			countDownTimer.cancel();
		if (currentQuestion <= questions.size() - 1) {
			Integer questionNumber = currentQuestion + 1;
			Question question = questions.get(currentQuestion);
			currentQuestionObj = question;
			tvQuesNumber.setText("Q" + (questionNumber));
			tvQuestion.setText(question.getQuestion());
			populateRadioButtons(question.getMultipleChoices());
			new loadImageAsyncTask().execute(question.getUrl());

		} else {
			Intent intent = new Intent(getBaseContext(), ResultActivity.class);
			intent.putExtra(CORRECT_ANSWERS_KEY, correctQuestions);
			intent.putExtra(TOTAL_QUESTIONS_KEY, questions.size());
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.putParcelableArrayListExtra(WelcomeActivity.QUESTIONS_KEY,
					questions);
			startActivity(intent);
		}
	}

	public void populateRadioButtons(ArrayList<String> options) {
		rg.removeAllViews();
		rg.check(-1);
		for (String option : options) {
			RadioButton radioButton = new RadioButton(this);
			rg.addView(radioButton);
			radioButton.setId(options.indexOf(option));
			radioButton.setText(option);
		}

	}

	class loadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				if (!params[0].equals("")) {
					URL url = new URL(params[0]);
					HttpURLConnection con = (HttpURLConnection) url
							.openConnection();
					con.setRequestMethod("GET");
					con.connect();
					Bitmap image = BitmapFactory.decodeStream(con
							.getInputStream());
					return image;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {

			Integer newHeight = 650;

			newHeight = newHeight - ((tvQuestion.getLineCount()) * 37);
			if (result != null) {
				Bitmap resultCustomized = Bitmap.createScaledBitmap(result,
						1000, newHeight, true);
				iv.setImageBitmap(resultCustomized);
			} else {

				Bitmap bMap = BitmapFactory.decodeResource(getResources(),
						R.drawable.no_image_available);
				Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 1000,
						newHeight, true);
				iv.setImageBitmap(bMapScaled);
			}
			iv.setVisibility(View.VISIBLE);
			loader.setVisibility(View.INVISIBLE);
			loaderMsg.setVisibility(View.INVISIBLE);
			next.setEnabled(true);

			long startTime = 24 * 1000;
			long interval = 1 * 1000;
			countDownTimer = new MyCountDownTimer(startTime, interval);
			countDownTimer.start();
		}

		@Override
		protected void onPreExecute() {

			iv.setVisibility(View.INVISIBLE);
			loader.setVisibility(View.VISIBLE);
			loaderMsg.setVisibility(View.VISIBLE);
			next.setEnabled(false);
			currentQuestion++;
		}

	}

	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {

			if (rg.getCheckedRadioButtonId() != -1
					&& rg.getCheckedRadioButtonId() == currentQuestionObj
							.getAnswer()) {
				correctQuestions++;
			}

			populateQuestion();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			tvTime.setText(Long.toString(millisUntilFinished / 1000) + " secs");
		}
	}
}
