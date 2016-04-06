package com.example.inclass06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Assignment No: InClass06 File Name: GalleryActivity.java
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan 
 * 3. Madhavi bhat
 */
public class GalleryActivity extends Activity {

	ListView listView;
	ArrayAdapter<Photo> adapter;
	ArrayList<Photo> photos;
	ProgressBar pb;
	TextView loader;

	static final String KEY_PHOTO_OBJECT = "photo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		loadAllViews();
		
		String searchTerm= getIntent().getExtras().getString(MainActivity.KEY_SEARCH_TERM).trim();
		
		try {
			searchTerm=URLEncoder.encode(searchTerm,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url="https://api.500px.com/v1/photos/search?consumer_key=2NyHEEgw4m73nQZI7YedB27yYvO9LjxXtV0BHl6P&image_size=4&rpp=50&term="+searchTerm;
		Log.d("InClass06", url);
		new loadPhotoSearchAsyncTask()
		.execute(url);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getBaseContext(),
						DetailsActivity.class);
				intent.putExtra(KEY_PHOTO_OBJECT, photos.get(arg2));
				intent.putExtra(MainActivity.KEY_SEARCH_TERM, getIntent()
						.getExtras().getString(MainActivity.KEY_SEARCH_TERM));
				startActivity(intent);

			}
		});

	}

	private void loadAllViews() {
		listView = (ListView) findViewById(R.id.listView1);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		loader = (TextView) findViewById(R.id.textView1);
	}

/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivity(intent);
			return false;
		}
		return true;
	}*/

	public class loadPhotoSearchAsyncTask extends
			AsyncTask<String, Void, ArrayList<Photo>> {
		@Override
		protected ArrayList<Photo> doInBackground(String... params) {

			URL url;
			try {
				//params[0]=URLEncoder.encode(params[0], "UTF-8");
				Log.d("InClass06@", params[0]);
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					StringBuilder jsonData = new StringBuilder();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));
					String line = br.readLine();
					while (line != null) {
						jsonData.append(line);
						line = br.readLine();
					}
					Log.d("InClass06", jsonData.toString());
					return Json500pxUtil.JsonParser.parseJson(jsonData
							.toString());
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Photo> result) {
			super.onPostExecute(result);

			if (result == null || result.size() <= 0) {
				pb.setVisibility(View.INVISIBLE);
				loader.setTypeface(null, Typeface.BOLD);
				loader.setText("No Images Found for the search term: "
						+ getIntent().getExtras().getString(
								MainActivity.KEY_SEARCH_TERM));
			} else {
				pb.setVisibility(View.GONE);
				loader.setVisibility(View.GONE);
				photos = result;
				adapter = new ArrayAdapter<Photo>(getBaseContext(),
						android.R.layout.simple_list_item_1, result);
				listView.setAdapter(adapter);
				Log.d("InClass06", result.toString());
			}

		}
	}

}
