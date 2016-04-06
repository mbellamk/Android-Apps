package com.example.hw02_tbbtquiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Assignment No:  HomeWork02 
 * File Name: WelcomeActivity.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class WelcomeActivity extends Activity {

	Button exitButton, quizButton;
	ImageView logo;
	TextView loaderTv;
	ProgressBar progressBar;
	ArrayList<Question> questions;
	static final String QUESTIONS_KEY = "questions";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		loaderTv = (TextView) findViewById(R.id.textView2);
		exitButton = (Button) findViewById(R.id.button1);
		quizButton = (Button) findViewById(R.id.button2);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		logo = (ImageView) findViewById(R.id.imageView1);
		quizButton.setEnabled(false);
		logo.setVisibility(View.INVISIBLE);
		progressBar.setVisibility(View.VISIBLE);
		try {
			URL url = new URL(
					"http://dev.theappsdr.com/lectures/trivia/index.php");
			new loadImages().execute(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		quizButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), QuizActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.putParcelableArrayListExtra(QUESTIONS_KEY, questions);
				startActivity(intent);

			}
		});
		
		exitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startMain = new Intent(Intent.ACTION_MAIN); 
				startMain.addCategory(Intent.CATEGORY_HOME); 
				startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				startActivity(startMain); 
				finish();
				
			}
		});

	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	Intent startMain = new Intent(Intent.ACTION_MAIN); 
			startMain.addCategory(Intent.CATEGORY_HOME); 
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			startActivity(startMain); 
			finish();
            return true;
        }
        return false;
    }
	
	class loadImages extends AsyncTask<URL, Void, ArrayList<Question>> {

		@Override
		protected ArrayList<Question> doInBackground(URL... params) {
			URL url = params[0];
			try {
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String line = "";
				ArrayList<Question> questionsObj = new ArrayList<Question>();
				
				while ((line = reader.readLine()) != null) {

					String question;
					String imageUrl;
					ArrayList<String> multipleChoices = new ArrayList<String>();
					Integer answer;
					String[] questionString = line.split(";");
					question = questionString[0];
					imageUrl = questionString[1];
					for (int i = 2; i < questionString.length - 1; i++) {
						multipleChoices.add(questionString[i]);
					}
					answer = Integer
							.parseInt(questionString[questionString.length - 1]);
					Question questionObj = new Question(question, imageUrl,
							multipleChoices, answer);
					questionsObj.add(questionObj);
				}
				return questionsObj;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Question> result) {
			questions = result;
			quizButton.setEnabled(true);
			loaderTv.setVisibility(View.INVISIBLE);
			logo.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.INVISIBLE);
		}

		@Override
		protected void onPreExecute() {
			logo.setVisibility(View.INVISIBLE);
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

	}
}
