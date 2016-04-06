package com.example.inclass06;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Assignment No: InClass06 File Name: MainActivity.java
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan 
 * 3. Madhavi bhat
 */
public class MainActivity extends Activity {

	EditText searchTerm;
	Button submit;
	static final String KEY_SEARCH_TERM = "search_term";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadAllViews();

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (isNetworkAvailable()) {

					if (!searchTerm.getEditableText().toString().equals("")) {
						Intent intent = new Intent(getBaseContext(),
								GalleryActivity.class);
						intent.putExtra(KEY_SEARCH_TERM, searchTerm
								.getEditableText().toString());
						startActivity(intent);
					} else {
						Toast.makeText(getBaseContext(),
								"Please enter the search term",
								Toast.LENGTH_LONG).show();
					}

				} else {
					Toast.makeText(
							MainActivity.this,
							"Please check your internet connection and try agian..",
							Toast.LENGTH_LONG).show();

				}
			}
		});
	}

	public void loadAllViews() {
		searchTerm = (EditText) findViewById(R.id.editText1);
		submit = (Button) findViewById(R.id.button1);
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}
}
