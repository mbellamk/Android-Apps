package com.example.inclass05;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Assignment No: InClass05 File Name: MainActivity.java
 * 
 * Team Members: 1. Manju Raghavendra Bellamkonda 2. Prem kumar Murugesan 3.
 * Madhavi bhat
 */
public class MainActivity extends Activity {

	EditText et;
	Button go;
	ImageView image, leftArrow, rightArrow;
	Integer currentImageIndex = 0;
	ArrayList<String> imageUrlList;
	Switch parserType;
	static final String PARSE_TYPE_SAX = "sax";
	static final String PARSE_TYPE_XML_PULL = "xml_pull";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeViews();
		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				StringBuilder url = new StringBuilder();
				url.append("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=d64fe0b98ed916ac9ba847e8b1df3804&extras=url_m&format=rest&per_page=20");
				if (et.getText() != null) {
					url.append("&text=" + et.getText().toString());
					if (isNetworkAvailable()) {
						if (parserType.isChecked()) {
							new getXMLData().execute(url.toString(),
									PARSE_TYPE_SAX);
						} else {
							new getXMLData().execute(url.toString(),
									PARSE_TYPE_XML_PULL);
						}
					} else {
						Toast.makeText(
								MainActivity.this,
								"Please check your internet connection and try agian..",
								Toast.LENGTH_LONG).show();
						;
					}
				} else {
					et.setError("Enter some keyword");
				}
			}
		});

		leftArrow.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentImageIndex == 0)
					currentImageIndex = imageUrlList.size() - 1;
				else
					--currentImageIndex;
				loadImageInView(currentImageIndex);

			}
		});

		rightArrow.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentImageIndex == imageUrlList.size())
					currentImageIndex = 0;
				else
					currentImageIndex++;
				loadImageInView(currentImageIndex);
			}
		});
	}

	public void loadImageInView(Integer curentIndex) {
		if (isNetworkAvailable()) {
			if (imageUrlList!=null && curentIndex < imageUrlList.size())
				new loadImage().execute(imageUrlList.get(curentIndex));
		} else {
			Toast.makeText(MainActivity.this,
					"Please check your internet connection and try agian..",
					Toast.LENGTH_LONG).show();
			;
		}
	}

	public void initializeViews() {
		et = (EditText) findViewById(R.id.editText1);
		go = (Button) findViewById(R.id.button1);
		image = (ImageView) findViewById(R.id.imageView1);
		leftArrow = (ImageView) findViewById(R.id.imageView2);
		rightArrow = (ImageView) findViewById(R.id.imageView3);
		parserType = (Switch) findViewById(R.id.switch1);
		image.setVisibility(View.INVISIBLE);
		leftArrow.setEnabled(false);
		rightArrow.setEnabled(false);
	}

	public class loadImage extends AsyncTask<String, Void, Bitmap> {
		ProgressDialog progressDialog;

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				Thread.sleep(1000);
				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();
				Bitmap image = BitmapFactory.decodeStream(con.getInputStream());
				return image;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			progressDialog.dismiss();
			leftArrow.setEnabled(true);
			rightArrow.setEnabled(true);
			image.setImageBitmap(result);
			image.setVisibility(View.VISIBLE);

		}

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setMessage("Loading Photo...");
			progressDialog.show();
		}

	}

	public class getXMLData extends AsyncTask<String, Void, ArrayList<String>> {

		ProgressDialog progressDialog;

		@Override
		protected ArrayList<String> doInBackground(String... params) {
			URL url;
			try {
				Thread.sleep(1000);
				String chosenParser = params[1];
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();

				if (chosenParser.equals(PARSE_TYPE_SAX))
					return SAXParser.MyImageSAXParser.parseFlickrData(con
							.getInputStream());
				else
					return MyXmlPullParser.MyFlickrXmlPullParser
							.parseFlickrData(con.getInputStream());
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {
			progressDialog.dismiss();
			super.onPostExecute(result);
			imageUrlList = result;
			loadImageInView(0);

		}

		@Override
		protected void onPreExecute() {
			image.setVisibility(View.INVISIBLE);
			leftArrow.setEnabled(false);
			rightArrow.setEnabled(false);
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setMessage("Loading Photo...");
			progressDialog.show();
		}

	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}
}
